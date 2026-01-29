package com.yigitberk.pickstyle_2026p.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Item (
    //annotations
    @ColumnInfo("name")//style name (first screen)
    var itemName: String?,

    @ColumnInfo("category") //Kategori
    var category: String?,

    //@ColumnInfo("date") sonraki sürümde uygulanacak

    @ColumnInfo("image")
    var image: ByteArray?
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}