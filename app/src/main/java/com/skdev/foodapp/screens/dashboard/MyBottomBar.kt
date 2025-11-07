package com.skdev.foodapp.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomAppBar
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skdev.foodapp.R

@SuppressLint("AutoboxingStateCreation")
@Composable
@Preview
fun MyComposable() {
    //todo การเตรียมข้อมูลเมนู
    val bottomMenuItemsList = prepareBottomMenuItems()
    //todo เก็บสถานะเมนูที่ถูกเลือก
    var selectedItem by remember { mutableStateOf( "Home") }

    //todo สร้างแถบด้านล่าง (BottomAppBar) พื้นหลังของแถบเมนู
    BottomAppBar(
        backgroundColor = colorResource(id = R.color.black3),
        elevation = 3.dp
    ){
        //todo วนลูปสร้างปุ่มเมนูแต่ละอัน
        bottomMenuItemsList.forEach { bottomMenuItem ->
            //todo ปุ่มแต่ละอันในแถบ
            BottomNavigationItem(
                selected = (selectedItem == bottomMenuItem.laBle),
                onClick = { selectedItem = bottomMenuItem.laBle },
                //todo สีของไอคอน
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.7f),
                //todo กำหนดไอคอนของเมนู
                icon = {
                    Icon(
                       painter = bottomMenuItem.icon,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(20.dp),
                        tint = Color.Unspecified
                    )
                }
            )
        }

    }
}

//todo เป็นคลาสข้อมูลที่เก็บชื่อของเมนู
data class BottomMenuItem(
    val laBle: String, val icon: Painter
)

@Composable
//todo สร้างรายการเมนู
fun prepareBottomMenuItems(): List<BottomMenuItem> {
    return listOf(
        BottomMenuItem(laBle = "Home", icon = painterResource(id = R.drawable.btn_1)),
        BottomMenuItem(laBle = "Cart", icon = painterResource(id = R.drawable.btn_2)),
        BottomMenuItem(laBle = "Favorite", icon = painterResource(id = R.drawable.btn_3)),
        BottomMenuItem(laBle = "Order", icon = painterResource(id = R.drawable.btn_4)),
        BottomMenuItem(laBle = "Profile", icon = painterResource(id = R.drawable.btn_5)),
        )
}