package hu.jcp.androidchallange.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.jcp.androidchallange.BuildConfig
import hu.jcp.androidchallange.data.MovieApiHelper
import hu.jcp.androidchallange.data.MovieApiHelperImpl
import hu.jcp.androidchallange.data.MovieApiService
import hu.jcp.androidchallange.db.data.MovieDbDatabase
import hu.jcp.androidchallange.db.data.MovieDbRepository
import hu.jcp.androidchallange.db.data.MovieDbRepositoryImpl
import hu.jcp.androidchallange.util.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(MovieApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: MovieApiHelperImpl): MovieApiHelper = apiHelper


    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): MovieDbDatabase {
        return Room.databaseBuilder(
            app,
            MovieDbDatabase::class.java,
            "movieDb_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: MovieDbDatabase): MovieDbRepository {
        return MovieDbRepositoryImpl(db.dao)
    }

}
