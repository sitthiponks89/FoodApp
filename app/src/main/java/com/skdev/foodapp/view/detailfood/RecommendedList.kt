package com.skdev.foodapp.view.detailfood

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.skdev.foodapp.domain.FoodModel
import com.skdev.foodapp.viewmodel.MainViewModel



// TODO: แสดงรายการอาหารแนะนำในรูปแบบ horizontal scroll (LazyRow)
@Composable
fun RecommendedList(
    viewModel: MainViewModel, // TODO: ViewModel สำหรับโหลดข้อมูล Best Food
    onItemClick: (FoodModel) -> Unit // TODO: Callback เมื่อผู้ใช้คลิกอาหารแต่ละรายการ
) {
    // TODO: สังเกตค่า foods จาก LiveData ของ ViewModel
    val foods by viewModel.loadBestFood().observeAsState(emptyList())

    // TODO: กำหนดสถานะ Loading ถ้า list ว่าง
    val isLoading = foods.isEmpty()

    if (isLoading) {
        // TODO: แสดง CircularProgressIndicator ขณะกำลังโหลด
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.Yellow)
        }
    } else {
        // TODO: แสดง LazyRow สำหรับรายการอาหารแนะนำ
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp), // TODO: เว้นระยะแต่ละรายการ
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp) // TODO: padding รอบ LazyRow
        ) {

            // TODO: วนลูปแสดงรายการอาหารแต่ละตัว
            items(foods.size) { index ->
                val item = foods[index]

                // TODO: แสดงรูปอาหารแต่ละรายการ
                AsyncImage(
                    model = item.ImagePath, // TODO: URL ของรูป
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp) // TODO: ขนาดรูป
                        .clip(RoundedCornerShape(10.dp)) // TODO: มุมโค้ง
                        .clickable { onItemClick(item) }, // TODO: คลิกเรียก callback
                    contentScale = ContentScale.Crop // TODO: ครอบภาพเต็มพื้นที่
                )
            }
        }
    }
}
