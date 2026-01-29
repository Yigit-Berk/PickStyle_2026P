package com.yigitberk.pickstyle_2026p.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yigitberk.pickstyle_2026p.model.Item

/*kısaca veritabanını oluşturduğumuz kısım*/
@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDAO
}