package com.skdev.foodapp.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.CircularProgressIndicator
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skdev.foodapp.R
import com.skdev.foodapp.viewmodel.MainViewModel
import com.skdev.foodapp.domain.CategoryModel
import com.skdev.foodapp.domain.FoodModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onOpenItems:(id: String, title: String) -> Unit,
    onOpenDetail:(FoodModel) -> Unit
){
    val scaffoldState = rememberScaffoldState()

    val categories = remember { mutableStateListOf<CategoryModel>() }
    val bestFood = remember { mutableStateListOf<FoodModel>() }

    var showCategoryLoading  by remember { mutableStateOf(true) }
    var showBestFoodLoading  by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadCategory().observeForever {
            categories.clear()
            categories.addAll(it)
            showCategoryLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadBestFood().observeForever {
            bestFood.clear()
            bestFood.addAll(it)
            showBestFoodLoading = false
        }
    }


    Scaffold (bottomBar =  { MyComposable() },
        scaffoldState = scaffoldState
    ){paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.black2))
                .padding(paddingValues),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item( span = { GridItemSpan(2) })
            { TopBar() }

            item(span = { GridItemSpan(2)}){
                CategorySection(
                    categories = categories,
                    showCategoryLoading = showCategoryLoading,
                    onCategoryItemClick = { category -> onOpenItems(category.Id.toString(), category.Name) }
                )
            }

            item(
                span = {GridItemSpan(2) }) {
                Text(
                    text = "Foods for you",
                    color = colorResource(R.color.white),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
            }

            if (showBestFoodLoading){
                item(span = { GridItemSpan(2)}){
                    Box (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator(color = colorResource(R.color.orange))
                    }
                }
            }else{
                items(bestFood.size){ index->
                    FoodItemCardGrid(
                        item= bestFood[index],
                        onClick = { onOpenDetail(bestFood[index]) }
                    )
                }
            }
        }
    }
}

