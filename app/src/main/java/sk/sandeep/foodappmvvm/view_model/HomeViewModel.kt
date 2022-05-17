package sk.sandeep.foodappmvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import sk.sandeep.foodappmvvm.models.MealResponse
import sk.sandeep.foodappmvvm.repository.MealRepository
import sk.sandeep.foodappmvvm.util_or_constants.Resource
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _randomMealList = MutableLiveData<Resource<MealResponse>>()

    // The external immutable LiveData for the request status
    val randomMealList: LiveData<Resource<MealResponse>> = _randomMealList

    var mealResponse: MealResponse? = null

    init {
        getRandomMealList()
    }

    fun getRandomMealList() = viewModelScope.launch {

        _randomMealList.postValue(Resource.Loading())

        try {
            val response = repository.getRandomMealList()
            delay(2000L)
            _randomMealList.postValue(responseToResource(response))
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _randomMealList.postValue(Resource.Error("Network Failure"))
                else -> _randomMealList.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    /**
    create a function to take input the response instance of type MealResponse
    returned from the api and  output a resource instance of type MealResponse
    of Resource type
     */
    private fun responseToResource(response: Response<MealResponse>):
            Resource<MealResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                if (mealResponse == null) {
                    mealResponse = result
                }
                return Resource.Success(mealResponse ?: result)
            }
        }
        return Resource.Error(response.message())
    }
}