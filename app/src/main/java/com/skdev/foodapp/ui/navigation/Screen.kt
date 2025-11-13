package com.skdev.foodapp.ui.navigation

// TODO: สร้าง sealed class สำหรับเก็บเส้นทาง (route) ของหน้าต่าง ๆ ใน Navigation
sealed class Screen(val route: String) {

    // TODO: หน้าหลักของแอป (Home Screen)
    data object Home : Screen("home")

    // TODO: หน้ารายการไอเท็ม โดยมีพารามิเตอร์ id และ title
    data object Items : Screen("itemsList/{id}/{title}") {

        // TODO: ฟังก์ชันนี้ใช้สร้างเส้นทางจริง (route) พร้อมส่งค่าพารามิเตอร์ id และ title
        // TODO: ใช้ URLEncoder เพื่อเข้ารหัส title ป้องกันอักขระพิเศษใน URL
        fun path(id: String, title: String) = "itemsList/$id/${java.net.URLEncoder
            .encode(title, Charsets.UTF_8.name())}"
    }

    // TODO: หน้ารายละเอียดของไอเท็ม (Detail Screen)
    data object Detail : Screen("detail")
}