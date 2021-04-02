package com.example.testformobiledeveloper.data.retrofit

import com.example.testformobiledeveloper.data.model.WorkoutData
import com.example.testformobiledeveloper.data.remote.GetWorkoutListResponse
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * Execute API
 */
interface IRepository {

    /**
     * Api execution.
     */
    suspend fun <S : IResponse> execute(request: Deferred<S>): Response<S> {
        return try {
            when (val response = request.await()) {
                is ResponseBase -> {
                    return Response.Success(response)
                }
                else -> Response.Failure(ErrorResponse.createNetworkError())
            }
        } catch (t: Throwable) {
            when (t) {
                is SocketTimeoutException -> Response.Failure(ErrorResponse.createTimeoutError())
                is HttpException -> Response.Failure(ErrorResponse.createNotFoundError())
                else -> Response.Failure(ErrorResponse.createNetworkError())
            }
        }
    }

    suspend fun executeList(request : Deferred<GetWorkoutListResponse>) : List<WorkoutData>{
        return request.await().workouts
    }
}