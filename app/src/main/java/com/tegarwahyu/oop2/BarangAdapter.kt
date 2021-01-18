package com.tegarwahyu.oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tegarwahyu.oop2.model.Barang

class BarangAdapter(
    private val listItems: ArrayList<Barang>,
    private val listener: BarangListener
) : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_barang, parent, false)
        return BarangViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val item = listItems[position]
        holder.textViewNama.text = item.nama
        holder.textViewNip.text = item.merk
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    class BarangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewNama = itemView.findViewById<TextView>(R.id.text_view_nama)
        var textViewNip = itemView.findViewById<TextView>(R.id.text_view_nip)

    }

    interface BarangListener{
        fun OnItemClicked(barang: Barang)
    }
}