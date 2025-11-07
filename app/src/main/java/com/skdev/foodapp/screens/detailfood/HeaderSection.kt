package com.skdev.foodapp.screens.detailfood

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


@Composable
fun HeaderSection(
    item: FoodModel,
    onBackClick: () -> Unit = {}
) {
    ConstraintLayout(
      modifier = Modifier
          .fillMaxSize()
          .padding(bottom = 16.dp)
    ){
        val (back,fav,mainImage,arc)=createRefs()
        Image(
            painter = rememberAsyncImagePainter(model = item.ImagePath),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .height(400.dp)
                .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                .constrainAs(mainImage) {
                   top.linkTo(parent.top)
                   start.linkTo(parent.start)
                   end.linkTo(parent.end)
                }
        )

        BackButton(onClick = onBackClick, Modifier.constrainAs(back){
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        })
        FavoriteBackButton(onClick = onBackClick, Modifier.constrainAs(fav){
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        })

        Image(
            painter = painterResource(R.drawable.arc),
            contentDescription = null,
            modifier = Modifier.constrainAs(arc){
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}


@Composable
private fun BackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
   Image(
       painter = painterResource(R.drawable.back),
       contentDescription = null,
       modifier = modifier
           .padding(start = 16.dp, top = 48.dp)
           .clickable { onClick() }
   )
}


@Composable
private fun FavoriteBackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.fav_icon),
        contentDescription = null,
        modifier = modifier
            .padding(end = 16.dp, top = 48.dp)
            .clickable { onClick() }
    )
}


@Preview
@Composable
fun HeaderSectionPreview(){
    val item = FoodModel(
        Title = "Pizza",
        Price = 9.99.toInt(),
        Star = 4.5
    )
    HeaderSection(item = item)
}
