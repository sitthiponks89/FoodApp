package com.skdev.foodapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.skdev.foodapp.domain.CategoryModel
import com.skdev.foodapp.domain.FoodModel

// TODO: Repository ชั้นกลาง ทำหน้าที่ติดต่อกับ Firebase Realtime Database โดยตรง
// TODO: ใช้สำหรับดึงข้อมูลหมวดหมู่ (Category) และรายการอาหาร (Food)
class MainRepository {

    // TODO: สร้าง instance ของ Firebase Realtime Database
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    // -------------------------------------------------------------------------
    // TODO: ฟังก์ชันที่ 1 - โหลดข้อมูลหมวดหมู่ทั้งหมดจาก Firebase (โหนด "Category")
    // -------------------------------------------------------------------------
    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        // TODO: สร้าง LiveData เพื่อเก็บรายการ Category ที่โหลดมาจาก Firebase
        val listData = MutableLiveData<MutableList<CategoryModel>>()

        // TODO: อ้างอิงไปยัง path "Category" ในฐานข้อมูล Firebase
        val ref = firebaseDatabase.getReference("Category")

        // TODO: ฟังการเปลี่ยนแปลงของข้อมูลแบบเรียลไทม์
        ref.addValueEventListener(object : ValueEventListener {

            // TODO: เมื่อข้อมูลเปลี่ยน หรือโหลดครั้งแรก จะเข้ามาใน onDataChange()
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()

                // TODO: วนลูปดึงข้อมูลลูก (children) ภายใต้ "Category"
                for (data in snapshot.children) {
                    // TODO: แปลง DataSnapshot แต่ละอันให้เป็น CategoryModel
                    val model = data.getValue(CategoryModel::class.java)
                    if (model != null) {
                        list.add(model) // TODO: เพิ่มลงใน list
                    }
                }

                // TODO: อัปเดตค่าของ LiveData เพื่อให้ UI (ที่ Observe อยู่) อัปเดตตาม
                listData.value = list
            }

            // TODO: กรณีโหลดข้อมูลล้มเหลว เช่น ไม่มีสิทธิ์ หรือเชื่อมต่อไม่ได้
            override fun onCancelled(error: DatabaseError) {
                // TODO: เพิ่ม log หรือ error handler ภายหลัง
            }
        })

        // TODO: คืนค่า LiveData ที่เก็บข้อมูล category
        return listData
    }

    // -------------------------------------------------------------------------
    // TODO: ฟังก์ชันที่ 2 - โหลดเฉพาะอาหารที่ถูกจัดว่าเป็น BestFood (BestFood = true)
    // -------------------------------------------------------------------------
    fun loadBestFood(): LiveData<MutableList<FoodModel>> {
        val listData = MutableLiveData<MutableList<FoodModel>>()

        // TODO: อ้างอิงไปที่โหนด "Foods"
        val ref = firebaseDatabase.getReference("Foods")

        // TODO: ใช้ query เพื่อกรองข้อมูลเฉพาะที่ BestFood = true
        val query: Query = ref.orderByChild("BestFood").equalTo(true)

        // TODO: ใช้ listener แบบ single event (โหลดครั้งเดียว)
        query.addListenerForSingleValueEvent(object : ValueEventListener {

            // TODO: เมื่อข้อมูลถูกโหลดเสร็จ
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<FoodModel>()

                // TODO: วนลูปดึงข้อมูลจากทุก child node
                for (data in snapshot.children) {
                    val model = data.getValue(FoodModel::class.java)
                    if (model != null) {
                        list.add(model)
                    }
                }

                // TODO: อัปเดตค่าของ LiveData
                listData.value = list
            }

            // TODO: หากเกิดข้อผิดพลาดในการโหลดข้อมูล
            override fun onCancelled(error: DatabaseError) {
                // TODO: เพิ่มการจัดการ error เช่น Log หรือแจ้งเตือน
            }
        })

        // TODO: คืนค่า LiveData ที่เก็บข้อมูลอาหารที่เป็น BestFood
        return listData
    }

    // -------------------------------------------------------------------------
    // TODO: ฟังก์ชันที่ 3 - โหลดอาหารตามหมวดหมู่ (Filter ด้วย CategoryId)
    // -------------------------------------------------------------------------
    fun loadFiltered(id: String): LiveData<MutableList<FoodModel>> {
        val listData = MutableLiveData<MutableList<FoodModel>>()

        // TODO: อ้างอิงไปยังโหนด "Foods"
        val ref = firebaseDatabase.getReference("Foods")

        // TODO: สร้าง query เพื่อกรองเฉพาะอาหารที่มี CategoryId ตรงกับ id ที่ส่งเข้ามา
        val query: Query = ref.orderByChild("CategoryId").equalTo(id)

        // TODO: ใช้ listener แบบ single event (โหลดครั้งเดียว)
        query.addListenerForSingleValueEvent(object : ValueEventListener {

            // TODO: เมื่อโหลดข้อมูลสำเร็จ
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<FoodModel>()

                // TODO: วนลูปดึงข้อมูลอาหารแต่ละรายการ
                for (data in snapshot.children) {
                    val model = data.getValue(FoodModel::class.java)
                    if (model != null) {
                        list.add(model)
                    }
                }

                // TODO: อัปเดตค่าให้ LiveData เพื่อส่งข้อมูลไปยัง ViewModel/UI
                listData.value = list
            }

            // TODO: เมื่อโหลดข้อมูลล้มเหลว
            override fun onCancelled(error: DatabaseError) {
                // TODO: เพิ่มการจัดการ error ภายหลัง
            }
        })

        // TODO: คืนค่า LiveData ที่เก็บข้อมูลอาหารตามหมวดหมู่
        return listData
    }
}
