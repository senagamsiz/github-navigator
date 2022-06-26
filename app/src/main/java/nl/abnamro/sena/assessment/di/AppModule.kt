package nl.abnamro.sena.assessment.di

import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nl.abnamro.sena.assessment.BuildConfig
import nl.abnamro.sena.assessment.data.local.dao.RepoDao
import nl.abnamro.sena.assessment.data.local.database.AppDatabase
import nl.abnamro.sena.assessment.data.remote.api.RepoApi
import nl.abnamro.sena.assessment.data.repository.RepoRepository
import nl.abnamro.sena.assessment.data.repository.RepoRepositoryImpl
import nl.abnamro.sena.assessment.ui.viewmodel.ReposOverviewViewModel
import okhttp3.MediaType
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {

    factory { provideRepoApi(get()) }
    single { provideRetrofit() }
    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
    single<RepoRepository> { RepoRepositoryImpl(get(), get()) }
    viewModel { ReposOverviewViewModel(get()) }

}

//Networking
private val contentType: MediaType = MediaType.get("application/json")

private val json = Json {
    ignoreUnknownKeys = true
    explicitNulls = false
    isLenient = true
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL)
        .addConverterFactory(
            json.asConverterFactory(contentType)
        ).build()
}

private fun provideRepoApi(retrofit: Retrofit): RepoApi =
    retrofit.create(RepoApi::class.java)

//Local
private fun provideDatabase(application: Application): AppDatabase {
    val DATABASE_NAME = "repo_database"
    return Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideDao(database: AppDatabase): RepoDao {
    return database.repoDao()
}