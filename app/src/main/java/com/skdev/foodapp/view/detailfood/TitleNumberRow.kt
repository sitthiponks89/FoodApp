package com.skdev.foodapp.view.detailfood

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skdev.foodapp.R
import com.skdev.foodapp.domain.FoodModel

// TODO: แสดงแถวชื่ออาหารพร้อมปุ่มเพิ่ม/ลดจำนวนสินค้า
@Composable
fun TitleNumberRow(
    item: FoodModel, // TODO: ข้อมูลอาหารที่จะแสดงชื่อ
    numberInCart: Int, // TODO: จำนวนสินค้าที่อยู่ในตะกร้า
    onIncrement: () -> Unit, // TODO: ฟังก์ชันเพิ่มจำนวนสินค้า
    onDecrement: () -> Unit, // TODO: ฟังก์ชันลดจำนวนสินค้า
    modifier: Modifier = Modifier // TODO: Modifier ภายนอกสำหรับปรับแต่ง
) {
    // TODO: Row จัดเรียงชื่ออาหารและปุ่มเพิ่ม/ลดในแนวนอน
    Row(
        modifier = modifier
            .fillMaxWidth() // TODO: กว้างเต็มหน้าจอ
            .padding(16.dp) // TODO: เว้นระยะรอบ ๆ
    ) {
        // TODO: แสดงชื่ออาหาร
        Text(
            text = item.Title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            color = Color.White,
            modifier = Modifier.weight(1f) // TODO: ขยายเต็มพื้นที่ที่เหลือ
        )

        // TODO: ConstraintLayout สำหรับปุ่ม + / - และแสดงจำนวน
        ConstraintLayout(
            modifier = Modifier
                .width(100.dp) // TODO: กำหนดความกว้างของกล่อง
                .padding(start = 8.dp)
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = colorResource(R.color.black3) // TODO: พื้นหลังสีเข้ม
                )
        ) {
            val (plusCartBtn, minusCartBtn, numberItemText) = createRefs()

            // TODO: แสดงจำนวนสินค้า
            Text(
                text = "$numberInCart",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .size(28.dp)
                    .wrapContentSize(Alignment.Center)
                    .constrainAs(numberItemText) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            // TODO: ปุ่มเพิ่มจำนวน (+)
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(30.dp)
                    .background(
                        color = colorResource(R.color.green),
                        shape = RoundedCornerShape(100.dp) // TODO: กลม
                    )
                    .constrainAs(plusCartBtn) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .clickable {
                        onIncrement() // TODO: เรียกเพิ่มจำนวน
                    }
            ) {
                Text(
                    text = "+",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }

            // TODO: ปุ่มลดจำนวน (-)
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(30.dp)
                    .background(
                        color = colorResource(R.color.green),
                        shape = RoundedCornerShape(100.dp) // TODO: กลม
                    )
                    .constrainAs(minusCartBtn) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable {
                        onDecrement() // TODO: เรียกลดจำนวน
                    }
            ) {
                Text(
                    text = "-",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// TODO: Preview แสดง TitleNumberRow ด้วยตัวอย่าง FoodModel
@Preview
@Composable
fun TitleNumberRowPreview() {
    val item = FoodModel(
        Title = "Sample Food",
        Price = 9.99.toInt(),
        Star = 4.5
    )
    TitleNumberRow(item = item, numberInCart = 1, onIncrement = {}, onDecrement = {})
}

