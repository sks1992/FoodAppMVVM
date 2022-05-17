package sk.sandeep.foodappmvvm.repository

import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Response
import sk.sandeep.foodappmvvm.models.MealResponse
import sk.sandeep.foodappmvvm.network.MealApi
import javax.inject.Inject


/**
 * In MealRepository we define  function to communicate with MealApi
 * */
/**
 * Scope annotation for bindings that should exist for the life of an activity.
 * */
@ActivityScoped
class MealRepository @Inject constructor(
    private val api: MealApi
) {

    /**
     * getRandomMealList function provides List of Meal from api
     * */
    suspend fun getRandomMealList(): Response<MealResponse> {
        return api.getRandomMeal()
    }
}