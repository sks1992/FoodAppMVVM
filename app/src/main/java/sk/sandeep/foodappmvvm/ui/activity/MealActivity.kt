package sk.sandeep.foodappmvvm.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import sk.sandeep.foodappmvvm.R
import sk.sandeep.foodappmvvm.databinding.ActivityMealBinding
/** Entry Point of Meal App */
@AndroidEntryPoint
class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** setContentView using DataBinding */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_meal)
        //setContentView(R.layout.activity_meal)

        /** Adding bottomNavigationView */
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)

        /*  Without DataBinding

         val bottomNavigation =findViewById<BottomNavigationView>(R.id.bottomNavigationView)
         val navController1 =Navigation.findNavController(this,R.id.fragmentContainerView)
         NavigationUI.setupWithNavController(bottomNavigation,navController1)
         */
    }
}