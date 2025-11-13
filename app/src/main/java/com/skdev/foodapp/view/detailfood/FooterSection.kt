package com.skdev.foodapp.view.detailfood

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skdev.foodapp.R

@Composable
fun FooterSection(
    onAddToCartClick: () -> Unit = {}, // TODO: ฟังก์ชันเมื่อกดเพิ่มลงตะกร้า
    totalPrice: Double = 24.99, // TODO: ราคารวมสินค้าในตะกร้า
    modifier: Modifier = Modifier // TODO: Modifier ภายนอกสำหรับปรับแต่ง
) {
    // TODO: Row ใช้จัดเรียงปุ่มและราคาตามแนวนอน
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(16.dp) // TODO: เว้นระยะรอบ
            .height(60.dp) // TODO: ความสูงของ Footer
            .fillMaxWidth() // TODO: กว้างเต็มหน้าจอ
            .clickable { onAddToCartClick() } // TODO: กดทั้งแถวเรียก onAddToCartClick
            .background(
                color = colorResource(R.color.green), // TODO: พื้นหลังสีเขียว
                shape = RoundedCornerShape(50.dp) // TODO: มุมโค้ง
            )
            .padding(horizontal = 4.dp) // TODO: padding ภายใน Row
    ) {

        // TODO: กล่องแสดงราคารวม
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp) // TODO: ขนาดกล่อง
                .background(
                    color = colorResource(R.color.white),
                    shape = RoundedCornerShape(100.dp) // TODO: กลมเต็มรูป
                )
        ) {
            // TODO: แสดงราคารวมภายในกล่อง
            Text(
                "$${totalPrice}",
                fontSize = 16.sp,
                color = colorResource(R.color.black),
                textAlign = TextAlign.Center
            )
        }

        // TODO: แสดงข้อความ "Add to Cart" อยู่ตรงกลาง Row
        Text(
            "Add to Cart",
            fontSize = 20.sp,
            modifier = Modifier.weight(1f), // TODO: ใช้ weight ให้ขยายเต็มที่เหลือ
            color = Color.White,
            textAlign = TextAlign.Center
        )

        // TODO: ไอคอนรถเข็นสินค้า (Cart)
        Image(
            painter = androidx.compose.ui.res.painterResource(id = R.drawable.cart),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp) // TODO: ขนาดไอคอน
                .background(
                    color = colorResource(R.color.white),
                    shape = RoundedCornerShape(100.dp) // TODO: กลม
                )
                .padding(12.dp) // TODO: padding ภายในกล่องไอคอน
        )
    }
}
