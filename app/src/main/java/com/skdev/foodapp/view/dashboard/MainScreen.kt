package com.skdev.foodapp.view.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.CircularProgressIndicator
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skdev.foodapp.R
import com.skdev.foodapp.viewmodel.MainViewModel
import com.skdev.foodapp.domain.CategoryModel
import com.skdev.foodapp.domain.FoodModel

// TODO: หน้าหลักของแอป แสดงหมวดหมู่ (Category) และอาหารแนะนำ (Best Food)
@Composable
fun MainScreen(
    viewModel: MainViewModel, // TODO: ViewModel สำหรับโหลดข้อมูลจาก Repository
    onOpenItems: (id: String, title: String) -> Unit, // TODO: ฟังก์ชันเปิดหน้ารายการอาหารตามหมวดหมู่
    onOpenDetail: (FoodModel) -> Unit // TODO: ฟังก์ชันเปิดหน้ารายละเอียดอาหาร
) {
    // TODO: ScaffoldState สำหรับจัดการ Snackbar หรือ Drawer
    val scaffoldState = rememberScaffoldState()

    // TODO: สร้าง State สำหรับเก็บรายการหมวดหมู่และอาหารแนะนำ
    val categories = remember { mutableStateListOf<CategoryModel>() }
    val bestFood = remember { mutableStateListOf<FoodModel>() }

    // TODO: ตัวแปรสถานะโหลดข้อมูล (ใช้สำหรับแสดง Loading)
    var showCategoryLoading by remember { mutableStateOf(true) }
    var showBestFoodLoading by remember { mutableStateOf(true) }

    // TODO: โหลดข้อมูลหมวดหมู่จาก ViewModel เมื่อ Composable ถูกเรียกครั้งแรก
    LaunchedEffect(Unit) {
        viewModel.loadCategory().observeForever {
            categories.clear()
            categories.addAll(it)
            showCategoryLoading = false // TODO: ปิดสถานะ Loading หลังโหลดเสร็จ
        }
    }

    // TODO: โหลดข้อมูล Best Food จาก ViewModel เมื่อ Composable ถูกเรียกครั้งแรก
    LaunchedEffect(Unit) {
        viewModel.loadBestFood().observeForever {
            bestFood.clear()
            bestFood.addAll(it)
            showBestFoodLoading = false // TODO: ปิดสถานะ Loading หลังโหลดเสร็จ
        }
    }

    // TODO: Scaffold ใช้เป็นโครงหลักของหน้าจอ (รองรับ BottomBar และ Content)
    Scaffold(
        bottomBar = { MyComposable() }, // TODO: แสดง Bottom Navigation Bar
        scaffoldState = scaffoldState
    ) { paddingValues ->

        // TODO: ใช้ LazyVerticalGrid เพื่อแสดงรายการแบบกริด 2 คอลัมน์
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.black2))
                .padding(paddingValues), // TODO: เผื่อระยะ padding จาก Scaffold
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // TODO: ส่วนหัว (Top Bar) ใช้ GridItemSpan(2) เพื่อให้กินพื้นที่เต็มแถว
            item(span = { GridItemSpan(2) }) {
                TopBar()
            }

            // TODO: ส่วนหมวดหมู่ (Category Section)
            item(span = { GridItemSpan(2) }) {
                CategorySection(
                    categories = categories, // TODO: ข้อมูลหมวดหมู่
                    showCategoryLoading = showCategoryLoading, // TODO: สถานะกำลังโหลด
                    onCategoryItemClick = { category ->
                        // TODO: เมื่อคลิกที่หมวดหมู่ จะเรียก onOpenItems เพื่อเปิดหน้าใหม่
                        onOpenItems(category.Id.toString(), category.Name)
                    }
                )
            }

            // TODO: หัวข้อส่วนของอาหารแนะนำ
            item(span = { GridItemSpan(2) }) {
                Text(
                    text = "Foods for you",
                    color = colorResource(R.color.white),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
            }

            // TODO: ถ้ายังโหลดอาหารแนะนำไม่เสร็จ แสดง Loading Spinner
            if (showBestFoodLoading) {
                item(span = { GridItemSpan(2) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = colorResource(R.color.orange))
                    }
                }
            } else {
                // TODO: ถ้าโหลดข้อมูลสำเร็จแล้ว แสดงรายการอาหารในรูปแบบการ์ด
                items(bestFood.size) { index ->
                    FoodItemCardGrid(
                        item = bestFood[index],
                        onClick = { onOpenDetail(bestFood[index]) } // TODO: เมื่อคลิกเปิดรายละเอียดอาหาร
                    )
                }
            }
        }
    }
}


