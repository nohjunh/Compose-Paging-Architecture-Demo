package com.example.compose_paging_demo.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.compose_paging_demo.data.database.dao.BeerDao
import com.example.compose_paging_demo.data.database.model.BeerEntity
import com.example.compose_paging_demo.data.network.datasource.BeerRemoteDataSource
import com.example.compose_paging_demo.data.network.model.toBeerEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Paging 3 라이브러리를 사용하여 원격 데이터 소스와 로컬 데이터 베이스를 적절하게 조화시키는 중간자 역할
 */
@OptIn(ExperimentalPagingApi::class)
class BeerMediator @Inject constructor(
    private val beerDao: BeerDao,
    private val beerRemoteDataSource: BeerRemoteDataSource
) : RemoteMediator<Int, BeerEntity>() {

    /**
     *  원격 데이터 소스에서 페이지를 로드할 때마다 호출
     */
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                /**
                 * 데이터를 처음 로드하거나 새로고침할 때 호출. 이 경우 첫 번째 페이지(1)를 로드
                 */
                LoadType.REFRESH -> 1
                /**
                 * 페이지의 시작 부분에 새로운 데이터를 추가하려고 할 때 호출.
                 * 본 데모에서는 데이터의 앞 부분에 새로운 항목을 추가하는 것을 지원하지 않기 때문에 즉시 MediatorResult.Success를 반환
                 */
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                /**
                 * 페이지의 끝에 새로운 데이터를 추가하려고 할 때 호출. 마지막 항목의 ID를 기반으로 다음 로드할 페이지 번호를 계산
                 */
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            /**
             * 원격 API를 사용하여 데이터를 가져온 후, 가져온 데이터를 로컬 데이터베이스에 저장.
             * 만약 로드 유형이 'REFRESH'라면, 기존의 모든 데이터를 삭제하고 새로운 데이터로 대체합니다.
             */
            val beers = beerRemoteDataSource.getBeers(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            beerDao.saveBeersAndDeleteIfRequired(
                beers = beers.map { it.toBeerEntity() },
                shouldDelete = loadType == LoadType.REFRESH
            )

            /**
             * 데이터가 더 이상 없으면 endOfPaginationReached를 true로 설정하여
             * 더 이상 데이터를 로드하지 않도록 함.
             */
            MediatorResult.Success(
                endOfPaginationReached = beers.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
