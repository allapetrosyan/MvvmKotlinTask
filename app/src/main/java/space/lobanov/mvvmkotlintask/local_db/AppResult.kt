package space.lobanov.mvvmkotlintask.local_db


sealed class AppResult<out T> {

    data class Success<out T>(val successData: T) : AppResult<T>()
    class Error(
        val exception: java.lang.Exception,
        val message: String = exception.localizedMessage
    ) : AppResult<Nothing>()
}

fun <T : Any> handleApiError(resp: T): AppResult.Error {
    return AppResult.Error(Exception("error from handleApiError"))
}

fun <T : Any> handleSuccess(response: List<T>): AppResult<List<T>> {
    response.let {
        return AppResult.Success(it)
    }
}