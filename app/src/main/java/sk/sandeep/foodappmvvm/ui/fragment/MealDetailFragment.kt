package sk.sandeep.foodappmvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sk.sandeep.foodappmvvm.R


class MealDetailFragment : Fragment() {

    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            mealId = it.getString(mealId).toString()
            mealName = it.getString(mealName).toString()
            mealThumb = it.getString(mealThumb).toString()
        }

        return inflater.inflate(R.layout.fragment_meal_detail, container, false)
    }
}