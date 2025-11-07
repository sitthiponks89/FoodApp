package com.skdev.foodapp.screens.dashboard


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import com.skdev.foodapp.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextFieldDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable // todo: ฟังก์ชัน UI ที่วาดบนหน้าจอได้ (เป็นส่วนหนึ่งของ Jetpack Compose)
@Preview // todo: แสดงผลล่วงหน้าใน Design Preview โดยไม่ต้องรันแอปจริง
fun TopBar() {
    //todo: เพื่อจัดเรียงองค์ประกอบในแนวนอน
    Row(
        modifier = Modifier
            .padding(top = 48.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
       //todo รูปโปรไฟล์ (Profile Image)
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)
                .clickable {}
        )
        //todo ช่องค้นหา (TextField)
        var text by rememberSaveable  { mutableStateOf("") }
        TextField(
            value = text, onValueChange = { text = it },
            //todo ส่วน label (ข้อความภายในช่อง)
            label = {
                Text(
                    text = "What Would You Like To Eat?",
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold, color = Color.White
                )

            },
            //todo ส่วนไอคอนด้านขวา (trailingIcon)
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            //todo การตกแต่งรูปร่างและสี
            shape =  RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colorResource(id = R.color.black3),
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                textColor = Color.White,
                unfocusedLabelColor = Color.Transparent
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
                .height(45.dp)
                .background(colorResource(id = R.color.black3), CircleShape)
        )
        //todo รูปไอคอนแจ้งเตือน (Notification Icon)
        Image(
            painter = painterResource(id = R.drawable.bell_icon),
            contentDescription = null,
            modifier = Modifier.clickable{}
        )
    }
}