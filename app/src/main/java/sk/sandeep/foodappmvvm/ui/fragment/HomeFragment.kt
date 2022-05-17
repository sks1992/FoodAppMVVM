package sk.sandeep.foodappmvvm.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import sk.sandeep.foodappmvvm.databinding.FragmentHomeBinding
import sk.sandeep.foodappmvvm.util_or_constants.Resource
import sk.sandeep.foodappmvvm.view_model.HomeViewModel


/**
 * A simple [Fragment] subclass for showing RandomMeal List
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val model: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Home", "onCreate: called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Sets the LifecycleOwner that should be used for observing changes of LiveData
         * in this binding. If a LiveData is in one of the binding expressions and no
         * LifecycleOwner is set, the LiveData will not be observed and updates to it
         * will not be propagated to the UI.*/
        binding.lifecycleOwner = this

        model.getRandomMealList()
        model.randomMealList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }

                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    Glide.with(this).load(response.data!!.meals[0].strMealThumb)
                        .into(binding.imgRandomMeal)
                    model.mealResponse = null
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        binding.imgRandomMeal.visibility = View.VISIBLE

    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        binding.imgRandomMeal.visibility = View.INVISIBLE
    }
}