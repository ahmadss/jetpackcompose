package com.plcoding.jetpackcomposepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.pokemonlist.PokemonListScreen
import com.plcoding.jetpackcomposepokedex.ui.theme.JetpackComposePokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController();
                androidx.navigation.compose.NavHost(
                    navController = navController,
                    startDestination = "pokemon_list_screen"
                ) {
                    composable("pokemon_list_screen") {
PokemonListScreen(navController = navController)
                    }
                    composable("pokemon_detail_screen/{dominantColor}/{pokemontName}",
                        arguments = listOf(navArgument("dominantColor") {
                            type = NavType.IntType
                        },
                            navArgument("pokemonName") {
                                type = NavType.StringType
                            }
                        )) {
                        val dominantcolor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let {
                                Color(it) ?: Color.White
                            }

                        }

                        val pokemonname = remember {
                            it.arguments?.getString("pokemonName")
                        }
                    }
                }
            }
        }
    }
}
