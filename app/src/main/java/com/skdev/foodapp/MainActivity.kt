package com.skdev.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.skdev.foodapp.screens.dashboard.MainScreen
import com.skdev.foodapp.screens.detailfood.DetailScreen
import com.skdev.foodapp.screens.itemslist.ItemListScreen
import com.skdev.foodapp.ui.navigation.Screen
import com.skdev.foodapp.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
          AppNavHost()
        }
    }
}

@Composable
fun AppNavHost(){
    val navController = rememberNavController()
    val vm: MainViewModel = viewModel()

    NavHost(navController = navController, startDestination =  Screen.Home.route){

        composable(Screen.Home.route){
            MainScreen(
                viewModel = vm,
                onOpenItems = { id,title ->
                navController.navigate(Screen.Items.path(id, title))
                },
                onOpenDetail = { food ->
                    vm.selectedFood(food)
                    navController.navigate(Screen.Detail.route)
                }
            )
        }

        composable(Screen.Items.route,
            arguments = listOf(
                navArgument("id"){ type = NavType.StringType },
                navArgument("title"){ type = NavType.StringType }
            )
        ){backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val title = backStackEntry.arguments?.getString("title") ?: ""

            ItemListScreen(
                viewModel = vm,
                id = id,
                title = title,
                onBackClick = { navController.navigateUp() },
                onOpenDetail = { foodModel ->
                    vm.selectedFood(foodModel)
                    navController.navigate(Screen.Detail.route)

                }
            )
        }

        composable( Screen.Detail.route){
            val food by vm.selectedFood.collectAsState()
            if (food==null){
                return@composable
            }

            DetailScreen(
                item = food!!,
                onBackClick = { navController.navigateUp() },
                onAddToCartClick = {},
                viewModel = vm,
                onOpenDetail = { next ->
                    vm.selectedFood(foodModel = next)
                    navController.navigate(Screen.Detail.route) {
                        launchSingleTop = true
                    }
                }
            )
        }


    }

}

//todo สร้าง UI หลักของหน้า “Main” หรือ “Home”
//todo โดยมีโครงสร้างแบบนี้:
//todo ใช้ Scaffold เพื่อวางโครงสร้างหน้า (เช่น TopBar, BottomBar, เนื้อหา)
//todo แสดงรายการของเนื้อหาในรูปแบบ Grid 2 คอลัมน์ (LazyVerticalGrid)
//todo มี TopBar ด้านบน และ BottomBar ด้านล่าง (MyComposable)

//---------------------------
//|        TopBar()         | ← (เต็มแถว)
//|--------------------------|
//| Item 1 | Item 2         |
//| Item 3 | Item 4         |
//|  ...   |  ...            |
//----------------------------
//|     MyComposable()      | ← Bottom Navigation Bar
//----------------------------

