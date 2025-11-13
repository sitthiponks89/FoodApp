package com.skdev.foodapp.view.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.CircularProgressIndicator
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.skdev.foodapp.R
import com.skdev.foodapp.domain.CategoryModel

@Composable
//todo แสดงหมวดหมู่เดียว มีภาพและชื่อ กดได้
fun CategoryItem(
    category: CategoryModel,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
//todo ใช้ Column (จัดวางแนวตั้ง)
//    เติมเต็มความกว้าง (fillMaxWidth())
//    ตั้งสีพื้นหลัง (สีดำเฉดที่ 3)
//    มุมโค้งมน (RoundedCornerShape(13.dp))
//    รองรับการคลิก (clickable)
//    เพิ่มระยะขอบใน (padding(8.dp))
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.black3),
                shape = RoundedCornerShape(13.dp)
            )
            .clickable(onClick = onItemClick)
            .padding(8.dp)
    ) {
       //todo โหลดภาพจาก URL โดยอัตโนมัติ
       //todo ใช้ Coil’s AsyncImage โหลดภาพจาก URL (ค่า category.ImagePath),ขนาดภาพ 80 dp
        AsyncImage(
            model = category.ImagePath,
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
       //todo แสดงชื่อหมวดหมู่ (category.Name), สีขาว ตัวหนา ขนาด 14sp, มีระยะห่างจากภาพด้านบน 8 dp
        Text(
            text = category.Name,
            color = colorResource(id = R.color.white),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
//todo แสดงหลายหมวดในรูปตาราง 3 คอลัมน์ พร้อมโหลดดิ้งขณะรอข้อมูล
//todo แสดง กลุ่มของหมวดหมู่ทั้งหมด (เช่น Pizza, Burger, Coffee, Dessert ฯลฯ) ถ้ายังโหลดข้อมูลจาก Firebase อยู่ จะแสดง Progress Indicator
fun CategorySection(
    categories: SnapshotStateList<CategoryModel>,
    showCategoryLoading: Boolean,
    onCategoryItemClick: (CategoryModel) -> Unit
) {
//todo ถ้า showCategoryLoading == true → แสดงวงกลมโหลด (สีส้ม) กล่องกว้างเต็มจอ สูง 350 dp และจัดให้อยู่กลาง
    if (showCategoryLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentAlignment = Alignment.Center

        ) { CircularProgressIndicator(color = colorResource(R.color.orange)) }

    } else {
        //todo แบ่ง categories ออกเป็น แถวละ 3 รายการ เช่น ถ้ามี 9 หมวด จะได้ 3 แถว
        val rows = categories.chunked(3)

//    todo ใช้ Column สำหรับรวมทุกแถวแนวตั้ง
//        ในแต่ละแถวใช้ Row วาง CategoryItem 3 ช่องเรียงแนวนอน
//        มีการเรียก onCategoryItemClick(categoryModel) เมื่อกดที่แต่ละช่อง
        Column(modifier = Modifier.fillMaxWidth()) {
            rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    row.forEach { categoryModel ->
                        CategoryItem(
                            category = categoryModel,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 12.dp),
                            //todo Callback ส่งหมวดหมู่ที่ผู้ใช้กดออกไปให้ ViewModel หรือ Screen ใช้ต่อ
                            onItemClick = { onCategoryItemClick(categoryModel) }
                        )
                    }
                    //todo ถ้าแถวนั้นมีน้อยกว่า 3 ช่อง (เช่น 2 ช่องสุดท้าย) จะเติม ช่องว่าง (Spacer) ให้ครบ 3 เพื่อให้จัดตำแหน่งเท่ากัน
                    if (row.size < 3) {
                        repeat(3 - row.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}
