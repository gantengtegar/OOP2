package com.tegarwahyu.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home);
        btn_suplier.setOnClickListener {

            val go = Intent(this@Home, ViewSuplier::class.java)

            startActivity(go)
        }

        btn_barang.setOnClickListener {

            val go = Intent(this@Home, ViewBarang::class.java)

            startActivity(go)
        }
    }
}
