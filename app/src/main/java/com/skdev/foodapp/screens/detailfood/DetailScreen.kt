package com.skdev.foodapp.screens.detailfood


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
import com.skdev.foodapp.Helper.ManagmentCart


@Composable
fun DetailScreen(
    item: FoodModel,
    onBackClick: () -> Unit = {},
    onAddToCartClick: () -> Unit = {},
    viewModel: MainViewModel,
    onOpenDetail: (FoodModel) -> Unit
) {
    val context = LocalContext.current
    val managmentCart = remember { ManagmentCart(context) }
    var numberInCart by remember { mutableIntStateOf(item.numberInCart) }

    BackHandler(enabled = true) {
        viewModel.selectedFood(foodModel = null)
        onBackClick()
    }

    ConstraintLayout {
        val (footer, column) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.black2))
                .verticalScroll(rememberScrollState())
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(bottom = 100.dp)
        ) {
            HeaderSection(
                item = item,
                onBackClick = {
                    viewModel.selectedFood(foodModel = null)
                    onBackClick()
                }
            )

            TitleNumberRow(
                item = item,
                numberInCart = numberInCart,
                onIncrement = {
                    numberInCart++
                    item.numberInCart = numberInCart
                },
                onDecrement = {
                    if (numberInCart > 1) {
                        numberInCart--
                        item.numberInCart = numberInCart
                    }
                },
            )

            Text(
                text = "$${item.Price}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            RowDetail(item = item)
            DescriptionSection(item.Description)

            RecommendedList(
                viewModel = viewModel,
                onItemClick = onOpenDetail
            )

        }

        FooterSection(
            onAddToCartClick = {
             managmentCart.insertItem(item)
                onAddToCartClick()
            },
            totalPrice = ((item.Price*numberInCart).toDouble()),
            modifier = Modifier.constrainAs(footer){
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

        )
    }
}


