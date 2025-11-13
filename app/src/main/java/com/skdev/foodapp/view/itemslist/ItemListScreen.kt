package com.skdev.foodapp.view.itemslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.CircularProgressIndicator
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skdev.foodapp.R
import com.skdev.foodapp.domain.FoodModel
import com.skdev.foodapp.viewmodel.MainViewModel

// TODO: แสดงรายการอาหารตามหมวดหมู่ (Category) ในรูปแบบหน้าจอใหม่
@Composable
fun ItemListScreen(
    title: String, // TODO: ชื่อหมวดหมู่จะแสดงบนแถบหัวเรื่อง
    id: String, // TODO: id ของหมวดหมู่เพื่อโหลดรายการอาหาร
    viewModel: MainViewModel, // TODO: ViewModel สำหรับโหลดข้อมูล
    onBackClick: () -> Unit, // TODO: Callback เมื่อกดปุ่มย้อนกลับ
    onOpenDetail: (FoodModel) -> Unit // TODO: Callback เมื่อกดรายการอาหารเพื่อเปิด Detail
) {

    // TODO: สังเกตรายการอาหารที่โหลดจาก ViewModel
    val items by viewModel.loadFiltered(id).observeAsState(emptyList())

    // TODO: สถานะ Loading
    var isLoading by remember { mutableStateOf(true) }

    // TODO: เรียกโหลดข้อมูลเมื่อ id เปลี่ยน
    LaunchedEffect(id) { viewModel.loadFiltered(id) }

    // TODO: อัปเดตสถานะ Loading เมื่อ items เปลี่ยน
    LaunchedEffect(items) { isLoading = items.isEmpty() }

    // TODO: Column ครอบทั้งหมด
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.black2)) // TODO: พื้นหลังสีเข้ม
    ) {
        // TODO: แถบหัวเรื่องพร้อมปุ่มย้อนกลับ
        ConstraintLayout(
            modifier = Modifier
                .padding(top = 36.dp, start = 16.dp, end = 16.dp)
        ) {
            val (blackBtn, cartText) = createRefs()

            // TODO: ชื่อหมวดหมู่ตรงกลาง
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(cartText) {
                        centerTo(parent)
                    },
                textAlign = TextAlign.Center,
                text = title,
                fontSize = 25.sp,
                color = colorResource(R.color.white),
                fontWeight = FontWeight.Bold
            )

            // TODO: ปุ่มย้อนกลับ
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onBackClick() } // TODO: กดเรียก callback
                    .constrainAs(blackBtn) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }

        // TODO: แสดง Loading หากข้อมูลยังไม่มาถึง
        if (isLoading) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = colorResource(R.color.orange) // TODO: สีส้ม
                )
            }
        } else {
            // TODO: แสดงรายการอาหารเมื่อโหลดเสร็จ
            ItemsList(
                items = items,
                onItemClick = { food -> onOpenDetail(food) } // TODO: คลิกเรียก Detail
            )
        }
    }
}

