package uz.salikhdev.dictonariy.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import org.aviran.cookiebar2.CookieBar
import uz.salikhdev.dictonariy.R
import uz.salikhdev.dictonariy.adapter.WordAdapter
import uz.salikhdev.dictonariy.databinding.ActivityDictonaryBinding
import uz.salikhdev.dictonariy.db.WordDataBase
import uz.salikhdev.dictonariy.db.WordModel
import uz.salikhdev.dictonariy.helper.AddDialog

class DictonaryActivity : AppCompatActivity(R.layout.activity_dictonary) {

    private val binding by viewBinding(ActivityDictonaryBinding::bind)
    private val adapter by lazy { WordAdapter() }
    private val db by lazy { WordDataBase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAdapter()
        loadAction()


    }

    private fun loadAction() {

        binding.addButton.setOnClickListener {
            val dialog = AddDialog(this)
            dialog.onClickSave = { uz, eng ->
                val word = WordModel(0, uz, eng, 0)
                db.addWord(word)
                dialog.dismiss()
                adapter.setData(db.getAllWords())
            }
            dialog.show()


        }

        adapter.onClickDelete = { id ->

            val dialog = AlertDialog.Builder(this)
            dialog.setCancelable(false)
            dialog.setMessage("O'chirishni hohlaysizmi ?")
            dialog.setTitle("Delete")
            dialog.setPositiveButton("Ha") { d, v ->


                db.deleteWord(id)
                val snakeBar =
                    Snackbar.make(binding.root, "Item is Deleted ${id}", Snackbar.LENGTH_LONG)
                snakeBar.setAction("ok") {
                    snakeBar.dismiss()
                }
                snakeBar.show()
                adapter.setData(db.getAllWords())
                d.dismiss()
            }
            dialog.show()

        }

        adapter.onClickSave = { id ->
            db.saveWord(id)
            adapter.setData(db.getAllWords())
            CookieBar.build(this)
                .setTitle("Saved")
                .setCookiePosition(Gravity.BOTTOM)
                .setBackgroundColor(R.color.black)
                .show()
        }

    }

    private fun setAdapter() {

        binding.dictonaryList.adapter = adapter
        adapter.setData(db.getAllWords())


    }
}