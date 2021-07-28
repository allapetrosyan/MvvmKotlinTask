package space.lobanov.mvvmkotlintask.local_db

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import space.lobanov.mvvmkotlintask.local_db.DmModule.Companion.apiModule
import space.lobanov.mvvmkotlintask.local_db.DmModule.Companion.databaseModule
import space.lobanov.mvvmkotlintask.local_db.DmModule.Companion.networkModule
import space.lobanov.mvvmkotlintask.local_db.DmModule.Companion.repositoryModule
import space.lobanov.mvvmkotlintask.local_db.DmModule.Companion.viewModelModule

class ImageApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ImageApplication)
            modules(
                apiModule,
                viewModelModule,
                repositoryModule,
                networkModule,
                databaseModule
            )
        }
    }
}
