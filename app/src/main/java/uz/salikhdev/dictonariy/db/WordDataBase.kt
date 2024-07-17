package uz.salikhdev.dictonariy.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import androidx.core.database.getIntOrNull
import uz.salikhdev.dictonariy.helper.DbHelper
import java.util.Optional

class WordDataBase(context: Context) : DbHelper(context, "dictionary.db") {


    fun getAllWords(): List<WordModel> {

        val allData = ArrayList<WordModel>()
        allData.clear()
        val cursor: Cursor = mDataBase.rawQuery("SELECT * FROM dictionary", null)

        cursor.moveToFirst()

        while (!cursor.isAfterLast) {
            val id = cursor.getInt(0)
            val uzb = cursor.getString(1)
            val eng = cursor.getString(2)
            val isFav = cursor.getInt(3)
            val soz = WordModel(id, uzb, eng, isFav)
            allData.add(soz)
            cursor.moveToNext()
        }

        return allData
    }

    fun getWordDetail(itemId: String): Optional<WordModel> {

        val cursor =
            mDataBase.rawQuery("SELECT * FROM dictionary WHERE id=?", arrayOf(itemId))

        cursor.moveToFirst()

        try {
            val id = cursor.getIntOrNull(cursor.getColumnIndex("id"))
            return if (id == null) {
                Optional.empty()
            } else {
                val uzb = cursor.getString(1)
                val eng = cursor.getString(2)
                val isFav = cursor.getInt(3)
                Optional.of(WordModel(id, uzb, eng, isFav))
            }

        } catch (e: Exception) {
            return Optional.empty()
        }

    }

    fun addWord(word: WordModel) {
        val contectValues = ContentValues()
        contectValues.put("uzb", word.uzb)
        contectValues.put("eng", word.eng)
        contectValues.put("is_fav", word.isFav)
        mDataBase.insert("dictionary", null, contectValues)
    }

    fun deleteWord(id: Int) {
        mDataBase.delete("dictionary", "id=?", arrayOf(id.toString()))
    }

    fun saveWord(id: Int) { // 10
        val value = ContentValues()
        value.put("is_fav", 1)
        mDataBase.update("dictionary", value, "id=?", arrayOf(id.toString()))
    }

    fun unSaveWord(id: Int) { // 10
        val value = ContentValues()
        value.put("is_fav", 0)
        mDataBase.update("dictionary", value, "id=?", arrayOf(id.toString()))
    }

    fun getFavouriteWords(): List<WordModel> {

        val data = ArrayList<WordModel>()

        // TODO : logic | hamma save bolgan malumotlarni olib chiqsin.


        


        return data
    }


}