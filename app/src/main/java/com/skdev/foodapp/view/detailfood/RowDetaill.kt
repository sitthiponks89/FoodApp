package com.skdev.foodapp.view.detailfood

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skdev.foodapp.R
import com.skdev.foodapp.domain.FoodModel

// TODO: แสดงข้อมูลรายละเอียดหลักของอาหาร เช่น เวลา, คะแนนดาว, แคลอรี ในแถวเดียว
@Composable
fun RowDetail(item: FoodModel, modifier: Modifier = Modifier) {

    // TODO: Row จัดเรียงไอคอนและข้อความในแนวนอน
    Row(
        modifier = modifier
            .padding(16.dp) // TODO: เว้นระยะรอบ ๆ Row
            .height(45.dp) // TODO: กำหนดความสูงของ Row
            .fillMaxWidth() // TODO: กว้างเต็มหน้าจอ
            .background(
                color = colorResource(R.color.black3), // TODO: พื้นหลังสีดำเข้ม
                shape = RoundedCornerShape(50.dp) // TODO: มุมโค้ง
            ),
        verticalAlignment = Alignment.CenterVertically, // TODO: จัดแนวแนวตั้งตรงกลาง
        horizontalArrangement = Arrangement.Center // TODO: จัดแนวนอนตรงกลาง
    ) {

        // TODO: แสดงไอคอนเวลา
        Image(
            painter = painterResource(id = R.drawable.time_color),
            contentDescription = null
        )

        // TODO: แสดงข้อความเวลาที่ใช้ในการทำอาหาร
        Text(
            text = "${item.TimeValue} min",
            modifier = Modifier.padding(start = 8.dp), // TODO: เว้นระยะจากไอคอนเวลา
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.White
        )

        // TODO: Spacer เว้นระยะระหว่างกลุ่มเวลาและดาว
        Spacer(modifier = Modifier.width(48.dp))

        // TODO: แสดงไอคอนดาว (คะแนน)
        Image(painter = painterResource(id = R.drawable.star), contentDescription = null)

        // TODO: แสดงคะแนนดาว
        Text(
            text = "${item.Star} ",
            modifier = Modifier.padding(start = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.White
        )

        // TODO: Spacer เว้นระยะระหว่างดาวและแคลอรี
        Spacer(modifier = Modifier.width(48.dp))

        // TODO: แสดงไอคอนแคลอรี (ไฟ)
        Image(painter = painterResource(id = R.drawable.flame), contentDescription = null)

        // TODO: แสดงจำนวนแคลอรี
        Text(
            text = "${item.Calorie} ",
            modifier = Modifier.padding(start = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.White
        )
    }
}

// TODO: พรีวิว RowDetail ด้วยตัวอย่าง FoodModel
@Preview
@Composable
fun RowDetailPreview() {
    val item = FoodModel(
        BestFood = false,
        CategoryId = "",
        Description = "",
        Id = 0,
        ImagePath = "",
        Price = 0,
        Star = 0.0,
        Title = ""
    )
    RowDetail(item = item)
}
