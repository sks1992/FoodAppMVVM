package sk.sandeep.foodappmvvm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sk.sandeep.foodappmvvm.network.MealApi
import sk.sandeep.foodappmvvm.repository.MealRepository
import sk.sandeep.foodappmvvm.util_or_constants.Constants.BASE_URL
import javax.inject.Singleton

/**
 * Here we define function that will be provide for app by using [Inject constructor] annotation
 * */
@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideMealRepository(
        api: MealApi
    ) = MealRepository(api)

    /** create an instance of retrofit */
    @Singleton
    @Provides
    fun provideMealApi(): MealApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MealApi::class.java)
    }
}