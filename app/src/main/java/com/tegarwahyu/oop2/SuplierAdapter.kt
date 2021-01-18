package com.tegarwahyu.oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tegarwahyu.oop2.model.Suplier

class SuplierAdapter(
    private val listItems: ArrayList<Suplier>,
    private val listener: SuplierListener
) : RecyclerView.Adapter<SuplierAdapter.SuplierViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuplierViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_suplier, parent, false)
        return SuplierViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: SuplierViewHolder, position: Int) {
        val item = listItems[position]
        holder.textViewNama.text = item.nama
        holder.textViewNis.text = item.alamat
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    class SuplierViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewNama = itemView.findViewById<TextView>(R.id.text_view_nama)
        var textViewNis = itemView.findViewById<TextView>(R.id.text_view_nis)

    }

    interface SuplierListener{
        fun OnItemClicked(suplier: Suplier)
    }
}



