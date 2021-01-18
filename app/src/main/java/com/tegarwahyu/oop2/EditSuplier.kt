package com.tegarwahyu.oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tegarwahyu.oop2.db.SuplierDao
import com.tegarwahyu.oop2.db.SuplierRoomDatabase
import com.tegarwahyu.oop2.model.Suplier
import kotlinx.android.synthetic.main.activity_edit_suplier.*

class EditSuplier : AppCompatActivity() {

    val EDIT_SUPLIER_EXTRA = "edit_suplier_extra"
    private lateinit var suplier: Suplier
    private var isUpdate = false
    private lateinit var database: SuplierRoomDatabase
    private lateinit var dao: SuplierDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_suplier)

        database = SuplierRoomDatabase.getDatabase(applicationContext)
        dao = database.getSuplierDao()

        if (intent.getParcelableExtra<Suplier>(EDIT_SUPLIER_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            suplier = intent.getParcelableExtra(EDIT_SUPLIER_EXTRA)
            edit_text_nama.setText(suplier.nama)
            edit_text_nis.setText(suplier.alamat)
            edit_text_kelas.setText(suplier.nohp)

            edit_text_nama.setSelection(suplier.nama.length)

        }

        button_save.setOnClickListener {
            val nama = edit_text_nama.text.toString()
            val alamat = edit_text_nis.text.toString()
            val nohp = edit_text_kelas.text.toString()

            if (nama.isEmpty() && alamat.isEmpty() && nohp.isEmpty()){
                Toast.makeText(applicationContext, "Sulier cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else{
                if (isUpdate){
                    saveSuplier(Suplier(id = suplier.id, nama = nama, alamat = alamat, nohp = nohp))
                }
                else{
                    saveSuplier(Suplier(nama = nama, alamat = alamat, nohp = nohp))
                }
            }

            finish()
        }

        button_delete.setOnClickListener {
            deleteSuplier(suplier)
            finish()
        }

    }

    private fun saveSuplier(suplier: Suplier){

        if (dao.getById(suplier.id).isEmpty()){

            dao.insert(suplier)
        }
        else{

            dao.update(suplier)
        }

        Toast.makeText(applicationContext, "Suplier saved", Toast.LENGTH_SHORT).show()

    }

    private fun deleteSuplier(suplier: Suplier){
        dao.delete(suplier)
        Toast.makeText(applicationContext, "Suplier removed", Toast.LENGTH_SHORT).show()
    }
}
