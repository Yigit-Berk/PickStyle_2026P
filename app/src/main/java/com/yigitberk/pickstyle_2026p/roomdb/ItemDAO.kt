package com.yigitberk.pickstyle_2026p.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yigitberk.pickstyle_2026p.model.Item


/*Coroutine kullanmak için fonksiyonları suspend yaparız
* şu manaya geliyor suspend gerektiği zaman durdurulabilen ve
* Şunu IO thred'de çalıştır veya main thread'de çalıştır şeklinde scope'larımız vardı hatırlarsan
* Peki neden insert ve delete'de yalnızca yazdık
* çünkü Query'ler doğası gereği zaten suspend olarak gelir belirtmek gerekmez
* internetteki örnekler de aynı şekilde zaten
* */

@Dao
interface ItemDAO {
    @Query("SELECT name, id FROM Item")
    fun getItemWithNameAndId() : List<Item> // 1. ekranda sadece stil adı olacak

    @Query("SELECT * FROM Item WHERE id = :id")
    fun getItemById(id: Int): Item? //CheckSaved ekranında görüntülenecek tek bir item için

    /*query'ler de suspend'dir ancak Create ve delete için belirtmek zorundayız*/
    @Insert
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

}