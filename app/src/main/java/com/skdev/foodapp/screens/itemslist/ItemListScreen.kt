package com.skdev.foodapp.screens.itemslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.CircularProgressIndicator
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skdev.foodapp.R
import com.skdev.foodapp.domain.FoodModel
import com.skdev.foodapp.viewmodel.MainViewModel
import java.nio.file.WatchEvent

@Composable
fun ItemListScreen(
    title: String,
    id: String,
    viewModel: MainViewModel,
    onBackClick: () -> Unit,
    onOpenDetail: (FoodModel) -> Unit

) {

    val items by viewModel.loadFiltered(id).observeAsState( emptyList())
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(id){ viewModel.loadFiltered(id)}
    LaunchedEffect(items) { isLoading = items.isEmpty() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.black2))
    ){
        ConstraintLayout ( modifier = Modifier
            .padding(top = 36.dp, start = 16.dp, end = 16.dp)
        ){
          val (blackBtn, cartText) = createRefs()
          Text(
              modifier = Modifier
                  .fillMaxWidth()
                  .constrainAs(cartText) {
                      centerTo(parent)
                  },
              textAlign = TextAlign.Center,
              text = title,
              fontSize = 25.sp,
              color = colorResource(R.color.white),
              fontWeight = FontWeight.Bold
          )
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .clickable{onBackClick()}
                    .constrainAs(blackBtn) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
        if (isLoading){
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator(
                    color = colorResource(R.color.orange)
                )
            }
        }else{
            ItemsList(
                items = items,
                onItemClick = {food -> onOpenDetail(food)}
            )
        }
    }
}
