package sk.sandeep.foodappmvvm.network

import retrofit2.http.GET
import sk.sandeep.foodappmvvm.models.MealResponse

/**
 * A public interface that exposes the [getRandomMeal] method
 */
interface MealApi {
    @GET("random.php")
    fun getRandomMeal(): MealResponse
}