package com.tegarwahyu.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tegarwahyu.oop2.db.SuplierRoomDatabase
import com.tegarwahyu.oop2.model.Suplier
import kotlinx.android.synthetic.main.activity_view_suplier.*

class ViewSuplier : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_suplier)

        getSuplierData()

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, EditSuplier::class.java))
        }

    }

    private fun getSuplierData(){
        val database = SuplierRoomDatabase.getDatabase(applicationContext)
        val dao = database.getSuplierDao()
        val listItems = arrayListOf<Suplier>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
        if (listItems.isNotEmpty()){
            text_view_suplier_empty.visibility = View.GONE
        }
        else{
            text_view_suplier_empty.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView(listItems: ArrayList<Suplier>){
        recycler_view_main.apply {
            adapter = SuplierAdapter(listItems, object : SuplierAdapter.SuplierListener{
                override fun OnItemClicked(suplier: Suplier) {
                    val intent = Intent(this@ViewSuplier, EditSuplier::class.java)
                    intent.putExtra(EditSuplier().EDIT_SUPLIER_EXTRA, suplier)
                    startActivity(intent)
                }
            })

            layoutManager = LinearLayoutManager(this@ViewSuplier)
        }
    }

    override fun onResume() {
        super.onResume()
        getSuplierData()
    }
}
