package com.skdev.foodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.skdev.foodapp.domain.CategoryModel
import com.skdev.foodapp.domain.FoodModel
import com.skdev.foodapp.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()

    }

    fun loadBestFood(): LiveData<MutableList<FoodModel>> {
        return repository.loadBestFood()
    }

    fun loadFiltered(id: String): LiveData<MutableList<FoodModel>> {
        return repository.loadFiltered(id)
    }

    private val _selectedFood= MutableStateFlow<FoodModel?>(null)
    val selectedFood: StateFlow<FoodModel?> = _selectedFood
    fun  selectedFood(foodModel: FoodModel?){
        _selectedFood.value = foodModel
    }
}