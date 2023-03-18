package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import kotlin.reflect.full.*
import kotlinx.coroutines.flow.first
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import androidx.datastore.preferences.core.preferencesKey
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private  lateinit var date : TextView
    private lateinit var time : TextView
    private  lateinit var saturation: TextView
    private  lateinit var happiness : TextView
    private  lateinit var education : TextView
    private  lateinit var literacy : TextView
    private  lateinit var money : TextView

    private lateinit var dataStore: DataStore<Preferences>
    private val DOUBLE_KEY_SATURATION = preferencesKey<Double>("key_saturation")
    private val DOUBLE_KEY_HAPPINESS = preferencesKey<Double>("key_happiness")
    private val DOUBLE_KEY_PERFORMANCE = preferencesKey<Double>("key_performance")
    private val DOUBLE_KEY_FINANCIAL_LITERACY = preferencesKey<Double>("key_financial_literacy")
    private val DOUBLE_KEY_MONEY = preferencesKey<Double>("key_money")
    private val LONG_KEY_DATETIME = preferencesKey<Long>("key_datetime")

    private lateinit var navController : NavController

    enum class Textviews(val intValue: Int) {
        SATURATION(1),
        HAPPINESS(2),
        EDUCATION(3),
        LITERACY(4),
        MONEY(5)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val foodFragment = FoodFragment()
        val eduFragment = EduFragment()
        val perfFragment = PerfFragment()
        val navHostController = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostController.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        setupWithNavController(bottomNavigationView, navController)
        dataStore = applicationContext.createDataStore(name = "vars")

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment,foodFragment)
            commit()
        }



        saturation = findViewById(R.id.saturation)
        happiness = findViewById(R.id.happiness)
        education  = findViewById(R.id.education)
        literacy = findViewById(R.id.literacy)
        money  = findViewById(R.id.money)
        date= findViewById(R.id.date)
        time= findViewById(R.id.time)

        lifecycleScope.launch {
            Load()
        }
        GameManager.ChangeSaturation(0.0)
        GameManager.ChangeMoney(0.0)
        UpdateTxtv(Textviews.SATURATION)
        UpdateTxtv(Textviews.HAPPINESS)
        UpdateTxtv(Textviews.EDUCATION)
        UpdateTxtv(Textviews.LITERACY)
        UpdateTxtv(Textviews.MONEY)
        UpdateTime()
    }
    fun UpdateTime(){
        date.setText(GameManager.GetDate())
        time.setText(GameManager.GetTime())
    }
    fun UpdateTxtv(txt: Textviews){
        when (txt.intValue) {
            1 -> saturation.setText("saturation: " + GameManager.saturation.toString())
            2 -> happiness.setText("happiness: " + GameManager.happiness.toString())
            3 -> education.setText("education: " + GameManager.perfomance.toString())
            4 -> literacy.setText("literacy: " + GameManager.financial_literacy.toString())
            5 -> money.setText("money: " + GameManager.money.toString())
        }

    }
    override fun onStart() {
        super.onStart()


        lifecycleScope.launch {
            Load()
        }
        UpdateTxtv(Textviews.SATURATION)
        UpdateTxtv(Textviews.HAPPINESS)
        UpdateTxtv(Textviews.EDUCATION)
        UpdateTxtv(Textviews.LITERACY)
        UpdateTxtv(Textviews.MONEY)
        UpdateTime()
    }
    override fun onResume() {
        super.onResume()


        lifecycleScope.launch {
            Load()
        }
        UpdateTxtv(Textviews.SATURATION)
        UpdateTxtv(Textviews.HAPPINESS)
        UpdateTxtv(Textviews.EDUCATION)
        UpdateTxtv(Textviews.LITERACY)
        UpdateTxtv(Textviews.MONEY)
        UpdateTime()
    }
    override fun onStop(){
        super.onStop()


        lifecycleScope.launch {
            Save()
        }
    }
    override fun onPause(){
        super.onPause()

        lifecycleScope.launch {
            Save()
        }
    }
    suspend fun Save() {
        dataStore.edit { settings ->
            settings[DOUBLE_KEY_SATURATION] = GameManager.saturation
            settings[DOUBLE_KEY_HAPPINESS] = GameManager.happiness
            settings[DOUBLE_KEY_PERFORMANCE] = GameManager.perfomance
            settings[DOUBLE_KEY_FINANCIAL_LITERACY] = GameManager.financial_literacy
            settings[DOUBLE_KEY_MONEY] = GameManager.money
            settings[LONG_KEY_DATETIME] = GameManager.datetime.toEpochSecond()
        }
    }
    suspend fun Load() {
        val preferences = dataStore.data.first()

        GameManager.saturation = preferences[DOUBLE_KEY_SATURATION] ?: GameManager.saturation
        GameManager.happiness = preferences[DOUBLE_KEY_HAPPINESS] ?: GameManager.happiness
        GameManager.perfomance = preferences[DOUBLE_KEY_PERFORMANCE] ?: GameManager.perfomance
        GameManager.financial_literacy = preferences[DOUBLE_KEY_FINANCIAL_LITERACY] ?: GameManager.financial_literacy
        GameManager.money = preferences[DOUBLE_KEY_MONEY] ?: GameManager.money
        GameManager.datetime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(preferences[LONG_KEY_DATETIME] ?: GameManager.datetime.toEpochSecond()), ZoneId.of("Europe/Moscow"))
    }
    fun replaceFragment(fr: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment,fr)
            commit()
        }
    }

}

