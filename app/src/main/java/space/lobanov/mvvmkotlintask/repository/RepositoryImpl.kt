package space.lobanov.mvvmkotlintask.repository


import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import space.lobanov.mvvmkotlintask.local_db.AppResult
import space.lobanov.mvvmkotlintask.local_db.ImageDao
import space.lobanov.mvvmkotlintask.local_db.handleApiError
import space.lobanov.mvvmkotlintask.local_db.handleSuccess
import space.lobanov.mvvmkotlintask.model.ApiInterface
import space.lobanov.mvvmkotlintask.model.Hits


class RepositoryImpl(
    private val api: ApiInterface,
    private val context: Context,
    private val dao: ImageDao
) : Repository2 {

    override suspend fun getAllImages(): AppResult<List<Hits>> {
        if (isOnline(context)) {
            return try {
                val response = api.getHitData()
                if (response.isSuccessful) {
                    response.body()?.let {
                        withContext(Dispatchers.IO) {
                            dao.insert(it.listofHits)
                        }
                        Log.d("dwd", "inserted to room")
                        handleSuccess(it.listofHits)
                    } ?: run {
                        handleApiError("error")
                    }
                } else {
                    handleApiError("error")
                }
            } catch (e: Exception) {
                Log.d("dwd", "error")
                AppResult.Error(e)
            }

        } else {
            val data = getImagesDataFromCache()
            return if (data.isNotEmpty()) {
                Log.d("dwd", "from db")
                AppResult.Success(data)
            } else {

                handleApiError("error from Room")
            }

        }

    }

    private suspend fun getImagesDataFromCache(): List<Hits> {
        return withContext(Dispatchers.IO) {
            dao.getAll()
        }
    }


    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}






