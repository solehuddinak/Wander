package dev.dizzy1021.core.data.source.remote

import dev.dizzy1021.core.data.source.remote.request.RequestAddReview
import dev.dizzy1021.core.data.source.remote.response.*
import dev.dizzy1021.core.data.source.remote.service.Services
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val services: Services) {

    suspend fun fetchHome(page: Int, user: String): Flow<ResourceWrapper<ResponseWrapper<ResponseHome>>> {
        return flow {
            services.callHome(
                page = page,
                user = user
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchWishlist(page: Int, user: String): Flow<ResourceWrapper<ResponseWrapper<ResponseWishlist>>> {
        return flow {
            services.callWishlist(
                page = page,
                user = user
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchReview(page: Int, user: String): Flow<ResourceWrapper<ResponseWrapper<ResponseReviews>>> {
        return flow {
            services.callReview(
                page = page,
                user = user
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun findPlaces(page: Int, user: String, q: String?, image: Any?): Flow<ResourceWrapper<ResponseWrapper<ResponseSearch>>> {
        return flow {
            services.searchPlaces(
                page = page,
                user = user,
                q = q,
                image = image
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun findPlaceByID(id: Int, user: String): Flow<ResourceWrapper<ResponseWrapper<ResponsePlace>>> {
        return flow {
            services.callPlaceById(
                id = id,
                user = user,
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchReviewPlace(id: Int, page: Int): Flow<ResourceWrapper<ResponseWrapper<ResponseReviewsPlace>>> {
        return flow {
            services.callReviewPlace(
                id = id,
                page = page,
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun addReview(id: Int, request: RequestAddReview): Flow<ResourceWrapper<ResponseWrapper<Any?>>> {
        return flow {
            services.createReview(
                id = id,
                formRequest = request
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

}
