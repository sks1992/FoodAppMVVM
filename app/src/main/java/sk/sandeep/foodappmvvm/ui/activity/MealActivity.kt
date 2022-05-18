package sk.sandeep.foodappmvvm.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import sk.sandeep.foodappmvvm.R
import sk.sandeep.foodappmvvm.databinding.ActivityMealBinding

/**  Main Activity and entry point for the Meal app. */
@AndroidEntryPoint
class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** setContentView using DataBinding */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_meal)
        //setContentView(R.layout.activity_meal)

        // Get the navigation host fragment from this Activity
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.findNavController()
        /** setting bottomNavigation*/
        binding.bottomNavigationView.setupWithNavController(navController)

        /*  Without DataBinding

         val bottomNavigation =findViewById<BottomNavigationView>(R.id.bottomNavigationView)
         val navController1 =Navigation.findNavController(this,R.id.fragmentContainerView)
         NavigationUI.setupWithNavController(bottomNavigation,navController1)
         */

        // Make sure actions in the ActionBar get propagated to the NavController
        //setupActionBarWithNavController(navController)
    }
//
//    /**
//     * Enables back button support. Simply navigates one element up on the stack.
//     */
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}

/* for get random image after some time inter-well

   val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                viewModel.getRandomMeal()
                mainHandler.postDelayed(this, 5000)
            }
        })
* */