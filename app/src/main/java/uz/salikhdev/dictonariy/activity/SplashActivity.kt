package uz.salikhdev.dictonariy.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.salikhdev.dictonariy.R

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, DictonaryActivity::class.java)

        lifecycleScope.launch {
            delay(2000)
            startActivity(intent)
            finish()
        }




    }
}