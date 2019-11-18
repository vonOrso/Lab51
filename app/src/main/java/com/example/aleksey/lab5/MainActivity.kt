package com.example.aleksey.lab5

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity(), SensorEventListener {

    var pokaz = ""

    lateinit private var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun AlertSS(viev: View){
        dialog = AlertDialog.Builder(this@MainActivity).create()
        dialog.setTitle("Sensor data");  // заголовок
        dialog.setMessage(pokaz); // сообщение
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
                dialogInterface, i ->
            Toast.makeText(applicationContext, "You clicked on OK", Toast.LENGTH_SHORT).show()
        })
        dialog.show()


    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        pokaz = event!!.values.zip("XYZ".toList()).fold("") { acc, pair ->
            "$acc${pair.second}: ${pair.first}\n"
        }

}

}
