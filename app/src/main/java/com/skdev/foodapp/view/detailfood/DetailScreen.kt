package com.skdev.foodapp.view.detailfood


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skdev.foodapp.R
import com.skdev.foodapp.domain.FoodModel
import com.skdev.foodapp.viewmodel.MainViewModel
import com.skdev.foodapp.helper.ManagmentCart


@Composable
fun DetailScreen(
    item: FoodModel, // TODO: ข้อมูลอาหารที่จะแสดงรายละเอียด
    onBackClick: () -> Unit = {}, // TODO: ฟังก์ชันเมื่อกดปุ่มย้อนกลับ
    onAddToCartClick: () -> Unit = {}, // TODO: ฟังก์ชันเมื่อกดเพิ่มลงตะกร้า
    viewModel: MainViewModel, // TODO: ViewModel สำหรับจัดการข้อมูลอาหารและโหลด Recommended List
    onOpenDetail: (FoodModel) -> Unit // TODO: ฟังก์ชันเปิดหน้ารายละเอียดอาหารอื่นจาก Recommended List
) {
    val context = LocalContext.current
    // TODO: สร้าง instance สำหรับจัดการตะกร้าสินค้า
    val managmentCart = remember { ManagmentCart(context) }

    // TODO: เก็บจำนวนสินค้าในตะกร้าที่ผู้ใช้เลือก
    var numberInCart by remember { mutableIntStateOf(item.numberInCart) }

    // TODO: จัดการเมื่อผู้ใช้กดปุ่มย้อนกลับของระบบ
    BackHandler(enabled = true) {
        viewModel.selectedFood(foodModel = null) // TODO: ล้างค่า selectedFood ใน ViewModel
        onBackClick() // TODO: เรียก callback ของหน้าหลัก
    }

    // TODO: ใช้ ConstraintLayout สำหรับจัดตำแหน่ง Column และ FooterSection
    ConstraintLayout {
        val (footer, column) = createRefs()

        // TODO: Column แสดงเนื้อหาหลักของหน้ารายละเอียด
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.black2)) // TODO: พื้นหลังสีดำ
                .verticalScroll(rememberScrollState()) // TODO: ทำให้เนื้อหาสามารถเลื่อนขึ้นลงได้
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(bottom = 100.dp) // TODO: เว้นพื้นที่ด้านล่างให้ FooterSection
        ) {
            // TODO: แสดงส่วน Header (รูปอาหาร + ปุ่มย้อนกลับ)
            HeaderSection(
                item = item,
                onBackClick = {
                    viewModel.selectedFood(foodModel = null)
                    onBackClick()
                }
            )

            // TODO: แถวชื่ออาหารและจำนวนสินค้า พร้อมปุ่มเพิ่ม/ลดจำนวน
            TitleNumberRow(
                item = item,
                numberInCart = numberInCart,
                onIncrement = {
                    numberInCart++
                    item.numberInCart = numberInCart // TODO: อัปเดตค่าใน FoodModel
                },
                onDecrement = {
                    if (numberInCart > 1) {
                        numberInCart--
                        item.numberInCart = numberInCart
                    }
                },
            )

            // TODO: แสดงราคาอาหาร
            Text(
                text = "$${item.Price}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // TODO: แสดงรายละเอียดเพิ่มเติมแบบแถว เช่น เวลา, คะแนนดาว
            RowDetail(item = item)

            // TODO: แสดงคำอธิบายสินค้าและโปรโมชัน
            DescriptionSection(item.Description)

            // TODO: แสดงรายการอาหารแนะนำ (Recommended List)
            RecommendedList(
                viewModel = viewModel,
                onItemClick = onOpenDetail
            )
        }

        // TODO: FooterSection แสดงปุ่มเพิ่มลงตะกร้าและราคารวม
        FooterSection(
            onAddToCartClick = {
                managmentCart.insertItem(item) // TODO: เพิ่มสินค้าไปยังตะกร้า
                onAddToCartClick() // TODO: เรียก callback ภายนอก
            },
            totalPrice = ((item.Price * numberInCart).toDouble()), // TODO: คำนวณราคารวม
            modifier = Modifier.constrainAs(footer) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}



