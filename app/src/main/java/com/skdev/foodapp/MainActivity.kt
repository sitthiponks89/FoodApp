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
import com.skdev.foodapp.view.dashboard.MainScreen
import com.skdev.foodapp.view.detailfood.DetailScreen
import com.skdev.foodapp.view.itemslist.ItemListScreen
import com.skdev.foodapp.ui.navigation.Screen
import com.skdev.foodapp.viewmodel.MainViewModel

// TODO: Activity หลักของแอพ ใช้เป็น entry point ของ Compose
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // TODO: เปิดการแสดงผลแบบ edge-to-edge (เต็มหน้าจอ)

        setContent {
            // TODO: เรียก Composable สำหรับ NavHost
            AppNavHost()
        }
    }
}

// TODO: Composable สำหรับจัดการ Navigation ของแอพ
@Composable
fun AppNavHost() {
    val navController = rememberNavController() // TODO: สร้าง NavController
    val vm: MainViewModel = viewModel() // TODO: สร้าง/เรียกใช้งาน ViewModel

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route // TODO: หน้าจอเริ่มต้นเป็น Home
    ) {
        // TODO: หน้าจอ Home
        composable(Screen.Home.route) {
            MainScreen(
                viewModel = vm,
                onOpenItems = { id, title ->
                    // TODO: เมื่อเลือก Category ให้ navigate ไปหน้ารายการอาหาร
                    navController.navigate(Screen.Items.path(id, title))
                },
                onOpenDetail = { food ->
                    // TODO: เมื่อเลือกอาหาร ให้ set selectedFood และ navigate ไป Detail
                    vm.selectedFood(food)
                    navController.navigate(Screen.Detail.route)
                }
            )
        }

        // TODO: หน้าจอแสดงรายการอาหารตาม Category
        composable(
            Screen.Items.route,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }, // TODO: รับ id ของ Category
                navArgument("title") { type = NavType.StringType } // TODO: รับชื่อ Category
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "" // TODO: ดึง id
            val title = backStackEntry.arguments?.getString("title") ?: "" // TODO: ดึง title

            ItemListScreen(
                viewModel = vm,
                id = id,
                title = title,
                onBackClick = { navController.navigateUp() }, // TODO: ปุ่มย้อนกลับ
                onOpenDetail = { foodModel ->
                    // TODO: เมื่อเลือกอาหาร ให้ navigate ไป Detail
                    vm.selectedFood(foodModel)
                    navController.navigate(Screen.Detail.route)
                }
            )
        }

        // TODO: หน้าจอ Detail ของอาหาร
        composable(Screen.Detail.route) {
            val food by vm.selectedFood.collectAsState() // TODO: สังเกต selectedFood
            if (food == null) {
                return@composable // TODO: ถ้าไม่มีข้อมูลอาหาร ให้ไม่ render
            }

            DetailScreen(
                item = food!!, // TODO: ส่งข้อมูลอาหารให้ DetailScreen
                onBackClick = { navController.navigateUp() }, // TODO: ปุ่มย้อนกลับ
                onAddToCartClick = {}, // TODO: เพิ่มฟังก์ชันการใส่ตะกร้า
                viewModel = vm,
                onOpenDetail = { next ->
                    // TODO: เปิด Detail ของอาหารต่อไป
                    vm.selectedFood(foodModel = next)
                    navController.navigate(Screen.Detail.route) {
                        launchSingleTop = true // TODO: ป้องกัน stack ซ้ำ
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

