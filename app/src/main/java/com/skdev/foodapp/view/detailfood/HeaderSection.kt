package com.skdev.foodapp.view.detailfood

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.skdev.foodapp.R
import com.skdev.foodapp.domain.FoodModel


// TODO: แสดงส่วนหัวของหน้ารายละเอียดอาหาร ประกอบด้วยรูปอาหาร, ปุ่มย้อนกลับ, ปุ่ม favorite, และ arc image
@Composable
fun HeaderSection(
    item: FoodModel, // TODO: ข้อมูลอาหารที่จะแสดงใน header
    onBackClick: () -> Unit = {} // TODO: ฟังก์ชันเมื่อกดปุ่มย้อนกลับ
) {
    // TODO: ใช้ ConstraintLayout เพื่อจัดวางองค์ประกอบให้ซ้อนกัน
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize() // TODO: กว้างและสูงเต็มพื้นที่
            .padding(bottom = 16.dp) // TODO: เว้นระยะด้านล่าง
    ) {
        val (back, fav, mainImage, arc) = createRefs() // TODO: สร้าง reference สำหรับการจัดวาง

        // TODO: แสดงรูปหลักของอาหาร
        Image(
            painter = rememberAsyncImagePainter(model = item.ImagePath), // TODO: โหลดรูปจาก URL
            contentDescription = null,
            contentScale = ContentScale.Crop, // TODO: ครอบภาพให้เต็มพื้นที่
            modifier = Modifier
                .fillMaxSize()
                .height(400.dp) // TODO: ความสูงของภาพ
                .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)) // TODO: มุมโค้งด้านล่าง
                .constrainAs(mainImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // TODO: ปุ่มย้อนกลับ (BackButton) วางที่มุมบนซ้าย
        BackButton(onClick = onBackClick, Modifier.constrainAs(back) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        })

        // TODO: ปุ่ม Favorite วางที่มุมบนขวา
        FavoriteBackButton(onClick = onBackClick, Modifier.constrainAs(fav) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        })

        // TODO: แสดงรูป arc ที่ด้านล่างของ header
        Image(
            painter = painterResource(R.drawable.arc),
            contentDescription = null,
            modifier = Modifier.constrainAs(arc) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

// TODO: Composable สำหรับปุ่มย้อนกลับ
@Composable
private fun BackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.back), // TODO: ไอคอนย้อนกลับ
        contentDescription = null,
        modifier = modifier
            .padding(start = 16.dp, top = 48.dp) // TODO: ระยะห่างจากขอบบนซ้าย
            .clickable { onClick() } // TODO: กดเรียก onClick
    )
}

// TODO: Composable สำหรับปุ่ม favorite
@Composable
private fun FavoriteBackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.fav_icon), // TODO: ไอคอน favorite
        contentDescription = null,
        modifier = modifier
            .padding(end = 16.dp, top = 48.dp) // TODO: ระยะห่างจากขอบบนขวา
            .clickable { onClick() } // TODO: กดเรียก onClick
    )
}

// TODO: พรีวิว HeaderSection ด้วยตัวอย่างข้อมูล FoodModel
@Preview
@Composable
fun HeaderSectionPreview() {
    val item = FoodModel(
        Title = "Pizza",
        Price = 9.99.toInt(),
        Star = 4.5
    )
    HeaderSection(item = item)
}

