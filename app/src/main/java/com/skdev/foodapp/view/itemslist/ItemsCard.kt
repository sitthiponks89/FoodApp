package com.skdev.foodapp.view.itemslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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

// TODO: แสดงรายการอาหารแบบ List (LazyColumn)
@Composable
fun ItemsList(
    items: List<FoodModel>, // TODO: รายการอาหาร
    onItemClick: (FoodModel) -> Unit // TODO: Callback เมื่อกดแต่ละรายการ
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp) // TODO: เว้นระยะรอบ ๆ
    ){
        // TODO: วนลูปแสดงแต่ละรายการด้วย Items()
        itemsIndexed(items){ _, item ->
            Items(item = item, onItemClick = { onItemClick(item) })
        }
    }
}

// TODO: Preview ของ ItemsList
@Preview
@Composable
fun ItemsListPreview(){
    val items = listOf(
        FoodModel(Title = "Pizza", Price = 12.5.toInt(), Star = 4.5),
        FoodModel(Title = "Burger", Price = 8.0.toInt(), Star = 4.2),
        FoodModel(Title = "Salad", Price = 7.2.toInt(), Star = 4.8)
    )
    ItemsList(items = items, onItemClick = {})
}

// TODO: แสดงรายการอาหารแต่ละแถว (Row) พร้อมรูปและรายละเอียด
@Composable
fun Items(item: FoodModel, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp) // TODO: เว้นระยะบน/ล่าง
            .fillMaxWidth() // TODO: กว้างเต็มหน้าจอ
            .background(
                color = colorResource(R.color.black3),
                shape = RoundedCornerShape(10.dp)
            ) // TODO: พื้นหลังสีเข้ม มุมโค้ง
            .wrapContentHeight() // TODO: ความสูงปรับตามเนื้อหา
            .clickable { onItemClick() } // TODO: คลิกเรียก callback
    ) {
        FoodImage(item = item) // TODO: แสดงรูปอาหาร
        FoodDetail(item = item) // TODO: แสดงรายละเอียดอาหาร
    }
}

// TODO: แสดงรายละเอียดอาหาร (ชื่อ, เวลา, คะแนน, ราคา)
@Composable
fun RowScope.FoodDetail(item: FoodModel) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp) // TODO: เว้นระยะซ้าย
            .fillMaxWidth()
            .weight(1f) // TODO: ขยายเต็มพื้นที่ที่เหลือ
    ) {
        // TODO: ชื่ออาหาร
        Text(
            text = item.Title,
            color = colorResource(R.color.white),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp)
        )

        TimingRow(item.TimeValue) // TODO: แสดงเวลาที่ใช้
        RatingBarRow(item.Star) // TODO: แสดงคะแนนดาว
        PriceRow(item.Price) // TODO: แสดงราคาและปุ่มเพิ่ม
    }
}

// TODO: แถวแสดงราคาและปุ่ม "+ Add"
@Composable
fun PriceRow(price: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
    ) {
        // TODO: แสดงราคา
        Text(
            text = "$price min", // TODO: แสดงตัวเลขราคา
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        // TODO: ปุ่มเพิ่มสินค้า
        Text(
            text = "+ Add",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .background(
                    color = colorResource(R.color.green),
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

// TODO: แถวแสดงคะแนนดาว
@Composable
fun RatingBarRow(star: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.star),
            contentDescription = null,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = "$star min", // TODO: แสดงค่าคะแนนดาว
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

// TODO: แถวแสดงเวลาในการทำอาหาร
@Composable
fun TimingRow(timeValue: Int) {
    Row(
        modifier = Modifier.padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.time),
            contentDescription = null,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = "$timeValue min",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

// TODO: แสดงรูปอาหาร
@Composable
fun FoodImage(item: FoodModel) {
    AsyncImage(
        model = item.ImagePath,
        contentDescription = null,
        modifier = Modifier
            .size(125.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(10.dp)
            ),
        contentScale = ContentScale.Crop
    )
}

