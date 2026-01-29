package com.yigitberk.pickstyle_2026p.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.yigitberk.pickstyle_2026p.model.Item
import com.yigitberk.pickstyle_2026p.roomdb.ItemDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
* ViewModel
* AndroidViewModel
* şeklinde iki türlüsü mevcut
* */

//aşağıdaki örnekte context verebilmemiz için AndroidViewModel'a ihtiyacımız

//Eğer context lazımsa AndroidViewModel eğer lazım değilse yulkarıda gri olarak ViewModel ile yapabilirsin.
class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application.baseContext,
        ItemDatabase::class.java,
        name = "Items"
    ).build()

    private val itemDao = db.itemDao()

    //bu noktadan sonra bize uygulamamızda lazım olacak fonksiyonları oluşturup itemDao ile tetikleriz

    //mutable ile ilgili neden mi işlem yapıyoruz çünkü:
    //Ekranımızda olan bir değişiklik için aşağıdaki veriler uyum içinde UI'da güncellensin diye.
    //kısaca burdaki değişiklikler ana ekranda'da kendini anlık göstersin diye
    val itemList = mutableStateOf<List<Item>>(listOf())
    val selectedItem = mutableStateOf<Item>(Item("","", ByteArray(1))) //boş değişken atadık

    //not: internetten tek 1 veri çektin diyelim ve o veri burada öylesine duracak.
    //bu durumda init kullanabilrisin
    // çünkü ilk başta init edilip tekrar init edilmeyecek listede veri değişimi olursa sorun olur
    /*
    init {
        getItemList()
    }
     */

    //scope için hatırlatma: IO internetten veri çekme, veritabanından veri çekme için kullanılıyor.
    //Main'de bir işlemimiz varsa'da Main'i kullanırız benzer mantıkla

    //_______coroutine_______

    //tüm itemları listeleme için
    fun getItemList(){
        viewModelScope.launch(Dispatchers.IO) {
            itemList.value = itemDao.getItemWithNameAndId()
        }
    }

    /*tıklanan item'ı listelemek için*/
    fun getItem(id : Int){
        viewModelScope.launch (Dispatchers.IO){

            val item = itemDao.getItemById(id)//tek veri çekme
            item?.let {
                selectedItem.value = it
            }

        }
    }

    //veri kaydetme ve silme işlemleri için
    fun saveItem(item: Item){
        viewModelScope.launch(Dispatchers.IO){
            itemDao.insert(item)//DAO'ya kaydet diyoruz
        }
    }

    fun deleteItem(item: Item){
        viewModelScope.launch (Dispatchers.IO){
            itemDao.delete(item) // DAO silme işlemi yap
        }
    }

}