package com.example.cliovision.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cliovision.data.mock.MockTours
import com.example.cliovision.ui.screens.home.HomeScreen
import com.example.cliovision.ui.screens.live.LiveTourScreen
import com.example.cliovision.ui.screens.onboarding.OnboardingScreen
import com.example.cliovision.ui.screens.summary.TourSummaryScreen
import com.example.cliovision.ui.screens.tours.TourDetailScreen
import com.example.cliovision.ui.screens.tours.TourListScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.ONBOARDING
    ) {
        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                onGetStarted = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.ONBOARDING) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onStartTour = { navController.navigate(Routes.TOURS) }
            )
        }

        composable(Routes.TOURS) {
            TourListScreen(
                tours = MockTours.tours,
                onTourClick = { tourId ->
                    navController.navigate("${Routes.TOUR_DETAIL}/$tourId")
                }
            )
        }

        composable("${Routes.TOUR_DETAIL}/{tourId}") { backStackEntry ->
            val tourId = backStackEntry.arguments?.getString("tourId").orEmpty()
            val tour = MockTours.tours.firstOrNull { it.id == tourId }

            if (tour != null) {
                TourDetailScreen(
                    tour = tour,
                    onStartTour = {
                        navController.navigate("${Routes.LIVE_TOUR}/${tour.id}")
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable("${Routes.LIVE_TOUR}/{tourId}") { backStackEntry ->
            val tourId = backStackEntry.arguments?.getString("tourId").orEmpty()
            val tour = MockTours.tours.firstOrNull { it.id == tourId }

            if (tour != null) {
                LiveTourScreen(
                    tour = tour,
                    onEndTour = { navController.navigate(Routes.SUMMARY) }
                )
            }
        }

        composable(Routes.SUMMARY) {
            TourSummaryScreen(
                onBackHome = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            )
        }
    }
}