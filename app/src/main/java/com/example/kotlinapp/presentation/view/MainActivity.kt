package com.example.kotlinapp.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinapp.R
import com.example.kotlinapp.databinding.ActivityMainBinding
import com.example.kotlinapp.presentation.view.home.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by viewModels()

    lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel.checkUserExists()

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        viewModel.nav.observe(this) {
            navController.graph = getNavGraph()
        }

        navController.addOnDestinationChangedListener(this) // добавление слушателя navigation bottom

        binding.bottomNavigation.setupWithNavController(navController)

        val btnav = AppBarConfiguration(
            setOf(R.id.onBoardingFragment2, R.id.itemsFragment)
        )

        NavigationUI.setupActionBarWithNavController(this, navController, btnav)

        viewModel.visibility.observe(this) {
            binding.bottomNavigation.visibility = it
        }
    }

    private fun getNavGraph(): NavGraph {
        val navGraph =
            navHostFragment.navController.navInflater.inflate( //выбор экрана загрузки, в зависимости от условия
                R.navigation.auth_graph
            )

        val random = (1..5).random()
        if (random == 1 || random == 2 || random == 3) {
            navGraph.startDestination = R.id.loginFragment
        } else {
            navGraph.startDestination = R.id.homeFragment
        }
        return navGraph
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        viewModel.destinationChanged(destination)
    }

    override fun onDestroy() { //удаление слушателя навигейшн баттон
        super.onDestroy()
        navController.removeOnDestinationChangedListener(this)
    }
}

