package com.skdev.foodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.skdev.foodapp.domain.CategoryModel
import com.skdev.foodapp.domain.FoodModel
import com.skdev.foodapp.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// TODO: ViewModel ชั้นกลาง สำหรับเชื่อมต่อ Repository และจัดการ State ของหน้าจอ
class MainViewModel: ViewModel() {

    // TODO: สร้าง instance ของ MainRepository สำหรับดึงข้อมูลจาก Firebase
    private val repository = MainRepository()

    // TODO: ฟังก์ชันโหลด Category ทั้งหมดจาก Repository
    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        // TODO: คืนค่า LiveData ของ CategoryModel
        return repository.loadCategory()
    }

    // TODO: ฟังก์ชันโหลดอาหารที่ถูกจัดว่า BestFood
    fun loadBestFood(): LiveData<MutableList<FoodModel>> {
        // TODO: คืนค่า LiveData ของ FoodModel ที่ BestFood = true
        return repository.loadBestFood()
    }

    // TODO: ฟังก์ชันโหลดอาหารตาม CategoryId
    fun loadFiltered(id: String): LiveData<MutableList<FoodModel>> {
        // TODO: คืนค่า LiveData ของ FoodModel ที่มี CategoryId ตรงกับ id
        return repository.loadFiltered(id)
    }

    // TODO: StateFlow สำหรับเก็บข้อมูลอาหารที่ถูกเลือก (DetailScreen)
    private val _selectedFood = MutableStateFlow<FoodModel?>(null)

    // TODO: StateFlow แบบอ่านได้ภายนอก เพื่อให้ UI สังเกตได้
    val selectedFood: StateFlow<FoodModel?> = _selectedFood

    // TODO: ฟังก์ชันอัปเดตอาหารที่ถูกเลือก
    fun selectedFood(foodModel: FoodModel?) {
        _selectedFood.value = foodModel
    }
}
