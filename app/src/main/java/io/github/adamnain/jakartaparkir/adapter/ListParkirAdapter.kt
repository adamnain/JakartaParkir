package io.github.adamnain.jakartaparkir.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import io.github.adamnain.jakartaparkir.R
import io.github.adamnain.jakartaparkir.model.Parkir
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick


class ListParkirAdapter(private val parkir: List<Parkir>, val context: Context?, private val listener: (Parkir) -> Unit)
    : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_parkir, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(parkir[position], listener)
    }

    override fun getItemCount(): Int = parkir.size

}


class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val ivTempatParkir: ImageView = view.find(R.id.iv_tempat_parkir)
    private val tvNamaLokasi: TextView = view.find(R.id.tv_nama_tempat)
    private val tvJenisLokasi: TextView = view.find(R.id.tv_jenis_lokasi)
    private val tvKapasitasMobil: TextView = view.find(R.id.tv_kapasitas_mobil)
    private val tvKapasitasMotor: TextView = view.find(R.id.tv_kapasitas_motor)
    private val tvKapasitasBus: TextView = view.find(R.id.tv_kapasitas_bus)

    fun bindItem(data: Parkir, listener: (Parkir) -> Unit) {
        tvNamaLokasi.text = data.namaTempatParkir
        tvJenisLokasi.text = data.jenisLokasiParkir
        tvKapasitasMobil.text = data.kapasitasMobil
        tvKapasitasMotor.text = data.kapasitasMotor
        tvKapasitasBus.text = data.kapasitasBusTruk
        Glide.with(itemView.context)
            .load(data.pictureParkir)
            .into(ivTempatParkir)

        itemView.onClick {
            listener(data)
        }
    }
}