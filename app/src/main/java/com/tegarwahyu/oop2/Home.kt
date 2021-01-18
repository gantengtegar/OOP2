package com.tegarwahyu.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home);
        btn_siswa.setOnClickListener {

            val go = Intent(this@Home, ViewSuplier::class.java)

            startActivity(go)
        }

        btn_guru.setOnClickListener {

            val go = Intent(this@Home, ViewBarang::class.java)

            startActivity(go)
        }
    }
}