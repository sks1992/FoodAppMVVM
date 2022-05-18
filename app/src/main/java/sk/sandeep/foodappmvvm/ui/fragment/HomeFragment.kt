package sk.sandeep.foodappmvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import sk.sandeep.foodappmvvm.R
import sk.sandeep.foodappmvvm.databinding.FragmentHomeBinding
import sk.sandeep.foodappmvvm.models.Meal
import sk.sandeep.foodappmvvm.util_or_constants.Resource
import sk.sandeep.foodappmvvm.view_model.HomeViewModel


/**
 * A simple [Fragment] subclass for showing RandomMeal List
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val model: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var randomMeal: Meal


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        /**
         * Sets the LifecycleOwner that should be used for observing changes of LiveData
         * in this binding. If a LiveData is in one of the binding expressions and no
         * LifecycleOwner is set, the LiveData will not be observed and updates to it
         * will not be propagated to the UI.*/
        binding.lifecycleOwner = this

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getRandomMealList()

        model.randomMealList.observe(viewLifecycleOwner) { response ->

            when (response) {
                is Resource.Error -> {
                    model.randomMealLiveData.observe(viewLifecycleOwner) {
                        randomMeal = it
                    }

                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }

                }
                is Resource.Loading -> {
                    model.randomMealLiveData.observe(viewLifecycleOwner) {
                        randomMeal = it
                    }
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    model.randomMealLiveData.observe(viewLifecycleOwner) {
                        randomMeal = it
                    }
                    response.data!!.meals[0].strMealThumb.let {
                        val imgUri = response.data.meals[0].strMealThumb.toUri().buildUpon()
                            .scheme("https").build()
                        binding.imgRandomMeal.load(imgUri) {
                            placeholder(R.drawable.loading_animation)
                            error(R.drawable.ic_broken_image)
                        }
                    }
                    /*Glide.with(this).load(response.data!!.meals[0].strMealThumb)
                        .into(binding.imgRandomMeal)*/
                }
            }
        }
        onRandomImageCardClicked()
    }

    private fun onRandomImageCardClicked() {
        binding.randomMealCard.setOnClickListener { view: View ->
            view.findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToMealDetailFragment(
                    id = randomMeal.strMealThumb,
                    name = randomMeal.strMeal,
                    thumb = randomMeal.strMealThumb
                )
            )
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