package uz.salikhdev.dictonariy.helper

import android.app.Dialog
import android.content.Context
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import uz.salikhdev.dictonariy.R

class AddDialog(context: Context) : Dialog(context) {

    var onClickSave: ((uz: String, en: String) -> Unit)? = null

    init {
        setContentView(R.layout.add_dialog)
        loadView()
    }

    private fun loadView() {
        val uzbText = findViewById<EditText>(R.id.uzb_text)
        val engText = findViewById<EditText>(R.id.eng_text)
        val saveButton = findViewById<AppCompatButton>(R.id.save_button)

        saveButton.setOnClickListener {
            val uzb = uzbText.text.toString().trim()
            val eng = engText.text.toString().trim()
            onClickSave?.invoke(uzb,eng)
        }

    }

}