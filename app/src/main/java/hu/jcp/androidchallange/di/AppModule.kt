package hu.jcp.androidchallange.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.jcp.androidchallange.repository.MovieRepository
import hu.jcp.androidchallange.data.MovieApi
import hu.jcp.androidchallange.util.Constant.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        api: MovieApi
    ) = MovieRepository(api)

    @Singleton
    @Provides
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MovieApi::class.java)
    }
}