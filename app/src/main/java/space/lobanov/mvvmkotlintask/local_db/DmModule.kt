package space.lobanov.mvvmkotlintask.local_db

import android.app.Application
import android.content.Context
import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.BuildConfig.DEBUG
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import space.lobanov.mvvmkotlintask.Constants.Companion.BASE_URL
import space.lobanov.mvvmkotlintask.model.ApiInterface
import space.lobanov.mvvmkotlintask.repository.Repository2
import space.lobanov.mvvmkotlintask.repository.RepositoryImpl
import space.lobanov.mvvmkotlintask.viewmodel.MyViewModel
import java.util.concurrent.TimeUnit


class DmModule {

    companion object {
        val apiModule = module {
            fun provideRetrofitApi(retrofit: Retrofit): ApiInterface {
                return retrofit.create(ApiInterface::class.java)
            }
            single { provideRetrofitApi(get()) }
        }

        val viewModelModule = module {
            viewModel {
                MyViewModel(repository2 = get())
            }
        }

        val repositoryModule = module {
            fun provideRepository(
                api: ApiInterface,
                context: Context,
                dao: ImageDao
            ): Repository2 {
                return RepositoryImpl(api,context, dao)
            }
            single { provideRepository(get(), androidContext(), get()) }
        }


        val networkModule = module {

            fun provideHttpClient(): OkHttpClient {
                val okHttpClientBuilder = OkHttpClient.Builder()

                if (DEBUG) {
                    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                    okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
                }
                okHttpClientBuilder.build()
                return okHttpClientBuilder.build()
            }

            fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
                return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                   // .client(client) //todo optimmal param
                    .build()
            }

            single { provideHttpClient() }
            single {
                val baseUrl = BASE_URL
                provideRetrofit(get(), baseUrl)
            }
        }

        val databaseModule = module {

            fun provideDatabase(application: Application): ImageDatabase {
                return Room.databaseBuilder(application, ImageDatabase::class.java, "imagesdb")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            fun provideImagesDao(database: ImageDatabase): ImageDao {
                return database.imageDao
            }

            single { provideDatabase(androidApplication()) }
            single { provideImagesDao(get()) }
        }


    }


}


