package com.tegarwahyu.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tegarwahyu.oop2.db.BarangRoomDatabase
import com.tegarwahyu.oop2.model.Barang
import kotlinx.android.synthetic.main.activity_view_barang.*
import kotlinx.android.synthetic.main.activity_view_barang.floatingActionButton
import kotlinx.android.synthetic.main.activity_view_barang.recycler_view_main

class ViewBarang : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_barang)

        getBarangData()

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, EditBarang::class.java))
        }

    }

    private fun getBarangData(){
        val database = BarangRoomDatabase.getDatabase(applicationContext)
        val dao = database.getBarangDao()
        val listItem = arrayListOf<Barang>()
        listItem.addAll(dao.getAll())
        setupRecyclerView(listItem)
        if (listItem.isNotEmpty()){
            text_view_barang_empty.visibility = View.GONE
        }
        else{
            text_view_barang_empty.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView(listItem: ArrayList<Barang>){
        recycler_view_main.apply {
            adapter = BarangAdapter(listItem, object : BarangAdapter.BarangListener{
                override fun OnItemClicked(barang: Barang) {
                    val intent = Intent(this@ViewBarang, EditBarang::class.java)
                    intent.putExtra(EditBarang().EDIT_BARANG_EXTRA, barang)
                    startActivity(intent)
                }
            })

            layoutManager = LinearLayoutManager(this@ViewBarang)
        }
    }

    override fun onResume() {
        super.onResume()
        getBarangData()
    }
}
