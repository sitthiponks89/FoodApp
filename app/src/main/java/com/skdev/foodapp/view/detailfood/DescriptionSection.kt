package com.skdev.foodapp.view.detailfood

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skdev.foodapp.R

@Composable
fun DescriptionSection(description: String) {

    // TODO: ใช้ Column เพื่อจัดวางเนื้อหาในแนวตั้งภายในส่วนรายละเอียดสินค้า
    Column {

        // TODO: แสดงหัวข้อ "Details" เพื่อบอกว่าด้านล่างนี้คือรายละเอียดของสินค้า
        Text(
            text = "Details",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.orange),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // TODO: แสดงข้อความรายละเอียดของสินค้า (รับค่าจากพารามิเตอร์ description)
        Text(
            text = description,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )

        // TODO: แสดงข้อความโปรโมชัน “Buy 2 item for free delivery”
        // TODO: เพื่อกระตุ้นการซื้อเพิ่มเติม
        Text(
            text = "Buy 2 item for free delivery",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.orange),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

    }
}

