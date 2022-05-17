package sk.sandeep.foodappmvvm.network

import retrofit2.Response
import retrofit2.http.GET
import sk.sandeep.foodappmvvm.models.MealResponse

/**
 * In MealApi we define function to get data from the meal api with url end point
 * and query parameters.
 * It is a public interface that exposes the [getRandomMeal] method
 * */
interface MealApi {
    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealResponse>
}