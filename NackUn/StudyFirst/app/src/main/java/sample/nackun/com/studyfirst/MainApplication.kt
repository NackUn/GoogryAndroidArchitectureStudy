package sample.nackun.com.studyfirst

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import sample.nackun.com.studyfirst.di.networkModule
import sample.nackun.com.studyfirst.di.remoteModule
import sample.nackun.com.studyfirst.di.repositoryModule
import sample.nackun.com.studyfirst.di.viewModelModule

@Suppress("unused")
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    remoteModule,
                    viewModelModule
                )
            )
        }
    }
}