package com.example.myapplication

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Environment
import android.widget.Toast
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.floor
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.first
import java.io.File
import java.io.FileOutputStream

object GameManager :AppCompatActivity(){

    var saturation: Double = 100.0
    var happiness: Double = 100.0
    var perfomance: Double = 0.0
    var financial_literacy: Double = 0.0

    var money = 1000.0
    var datetime = ZonedDateTime.of(LocalDateTime.of(2020, 9, 1, 8, 0, 0), ZoneId.of("Europe/Moscow"))

    fun ChangeSaturation(a: Double){
        if(saturation + a>100.0)
            saturation = 100.0
        else
            saturation += a
    }
    fun ChangeHappiness(a: Double){
        if(happiness + a>100.0)
            happiness = 100.0
        else
            happiness += a
    }
    fun ChangePerfomance(a: Double){
        if(perfomance + a>100.0)
            perfomance = 100.0
        else
            perfomance += a
    }
    fun ChangeFinancial_literacy(a: Double){
        if(financial_literacy + a>100.0)
            financial_literacy = 100.0
        else
            financial_literacy += a
    }
    fun ChangeMoney(a: Double){
        money +=a
        if(money<0.0)
            GameOver()
        }
    fun ChangeDateTime(time: Double){
        if(time - floor(time) != 0.0) {
            val minutes: Long = ((time - floor(time)) * 60).toLong()
            datetime = datetime.plusMinutes(minutes).plusHours(floor(time).toLong())
        } else {
            datetime = datetime.plusHours(floor(time).toLong())
        }
    }
    fun GetDate():String{
        val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        return datetime.toLocalDate().toString()
    }
    fun GetTime():String{
        return datetime.toLocalTime().toString()
    }
    fun GameOver(){
        print("gameover")
    }
}

