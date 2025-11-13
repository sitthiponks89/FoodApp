package com.skdev.foodapp.view.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.skdev.foodapp.R
import com.skdev.foodapp.domain.FoodModel

// TODO: แสดงการ์ดอาหารในรูปแบบ Grid พร้อมรูป, ชื่อ, ราคา, ดาว, และเวลา
@Composable
fun FoodItemCardGrid(
    item: FoodModel, // TODO: ข้อมูลของอาหารแต่ละรายการ
    onClick: () -> Unit // TODO: ฟังก์ชันเมื่อคลิกที่การ์ด
){
    // TODO: ใช้ Column สำหรับจัดเรียงองค์ประกอบในแนวตั้ง
    Column(
        modifier = Modifier
            .padding(8.dp) // TODO: ระยะห่างรอบการ์ด
            .fillMaxWidth() // TODO: ให้การ์ดเต็มความกว้างของคอลัมน์
            .background(
                colorResource(R.color.black2),
                shape = RoundedCornerShape(14.dp) // TODO: มุมโค้งของการ์ด
            )
            .clip(RoundedCornerShape(14.dp)) // TODO: ตัดขอบให้ตรงกับมุมโค้ง
            .clickable { onClick() }, // TODO: คลิกแล้วเรียก onClick
        horizontalAlignment = Alignment.CenterHorizontally // TODO: จัดตำแหน่งลูกในแนวนอนให้อยู่ตรงกลาง
    ) {
        // TODO: แสดงรูปภาพอาหารจาก URL โดยใช้ AsyncImage (Coil)
        AsyncImage(
            model = item.ImagePath,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .clip(RoundedCornerShape(10.dp)), // TODO: โค้งมุมของรูปภาพ
            contentScale = ContentScale.Crop // TODO: ครอบภาพให้เต็มพื้นที่แบบตัดส่วนเกิน
        )

        // TODO: แสดงชื่ออาหาร
        Text(
            text = item.Title,
            color = colorResource(R.color.white),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1, // TODO: จำกัดให้แสดงแค่ 1 บรรทัด
            overflow = TextOverflow.Ellipsis, // TODO: ถ้ายาวเกินให้ขึ้น "..."
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
        )

        // TODO: แถวแสดงดาว (คะแนน)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TODO: แสดงไอคอนรูปดาว
            Image(
                painter = painterResource(R.drawable.star),
                contentDescription = null,
                modifier = Modifier.size(15.dp)
            )

            // TODO: แสดงค่าคะแนนดาว
            Text(
                text = "${item.Star}",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 2.dp)
            )
        }

        // TODO: แถวแสดงราคาและเวลา
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 2.dp, start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // TODO: เว้นระยะระหว่างซ้าย-ขวา
        ) {
            // TODO: แสดงราคาอาหาร
            Text(
                text = "$${item.Price}",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            // TODO: แสดงเวลา (เช่น ใช้เวลา 20 นาที)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.time),
                    contentDescription = null,
                    modifier = Modifier.size(13.dp)
                )
                Text(
                    text = "${item.TimeValue} min",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 2.dp),
                    color = Color.White
                )
            }
        }
    }
}

// TODO: พรีวิวตัวอย่างการ์ดอาหาร (ใช้ตอนออกแบบ UI)
@Preview
@Composable
fun FoodItemCardGridPreview() {
    // TODO: สร้างตัวอย่างข้อมูลอาหาร
    val item = FoodModel(
        Title = "Pizza",
        Price = 9.99.toInt(),
        Star = 4.5,
        ImagePath = "https://via.placeholder.com/150"
    )

    // TODO: แสดงตัวอย่าง UI ของ FoodItemCardGrid
    FoodItemCardGrid(item = item, onClick = {})
}


