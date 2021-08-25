package ua.sergeylevchenko.buttonclick

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ua.sergeylevchenko.buttonclick.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences
    private val APP_PREFERENCES_COUNTER = "counter"
    private var counter: Int = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs =
            getSharedPreferences("settings", Context.MODE_PRIVATE)
        binding.crowButton.setOnClickListener{
            counter = ++counter
            binding.infoTextView.text = "Я насчитал $counter ворон"}
    }
    override fun onPause() {
        super.onPause()

        // Запоминаем данные
        val editor = prefs.edit()
        editor.putInt(APP_PREFERENCES_COUNTER, counter).apply()
    }
    override fun onResume() {
        super.onResume()
        if(prefs.contains(APP_PREFERENCES_COUNTER)){
            // Получаем число из настроек
            counter = prefs.getInt(APP_PREFERENCES_COUNTER, 0)
            // Выводим на экран данные из настроек
            binding.infoTextView.text = "Я насчитал $counter ворон"
        }
    }
}