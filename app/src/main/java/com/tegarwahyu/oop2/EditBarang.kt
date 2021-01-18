package com.tegarwahyu.oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tegarwahyu.oop2.db.BarangDao
import com.tegarwahyu.oop2.db.BarangRoomDatabase
import com.tegarwahyu.oop2.model.Barang
import kotlinx.android.synthetic.main.activity_edit_barang.*
import kotlinx.android.synthetic.main.activity_edit_barang.button_delete
import kotlinx.android.synthetic.main.activity_edit_barang.button_save
import kotlinx.android.synthetic.main.activity_edit_barang.edit_text_nama


class EditBarang : AppCompatActivity() {

    val EDIT_BARANG_EXTRA = "edit_barang_extra"
    private lateinit var barang: Barang
    private var isUpdate = false
    private lateinit var database: BarangRoomDatabase
    private lateinit var dao: BarangDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_barang)

        database = BarangRoomDatabase.getDatabase(applicationContext)
        dao = database.getBarangDao()

        if (intent.getParcelableExtra<Barang>(EDIT_BARANG_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            barang = intent.getParcelableExtra(EDIT_BARANG_EXTRA)
            edit_text_nama.setText(barang.nama)
            edit_text_nip.setText(barang.merk)
            edit_text_ngajar.setText(barang.harga)

            edit_text_nama.setSelection(barang.nama.length)

        }

        button_save.setOnClickListener {
            val nama = edit_text_nama.text.toString()
            val merk = edit_text_nip.text.toString()
            val harga = edit_text_ngajar.text.toString()

            if (nama.isEmpty() && merk.isEmpty() && harga.isEmpty()){
                Toast.makeText(applicationContext, "barang cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else{
                if (isUpdate){
                    saveBarang(Barang(id = barang.id, nama = nama, merk = merk, harga = harga))
                }
                else{
                    saveBarang(Barang(nama = nama, merk = merk, harga = harga))
                }
            }

            finish()
        }

        button_delete.setOnClickListener {
            deleteBarang(barang)
            finish()
        }

    }

    private fun saveBarang(barang: Barang){

        if (dao.getById(barang.id).isEmpty()){

            dao.insert(barang)
        }
        else{

            dao.update(barang)
        }

        Toast.makeText(applicationContext, "Barang saved", Toast.LENGTH_SHORT).show()

    }

    private fun deleteBarang(barang: Barang){
        dao.delete(barang)
        Toast.makeText(applicationContext, "Barang removed", Toast.LENGTH_SHORT).show()
    }
}