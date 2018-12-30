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
import io.github.adamnain.jakartaparkir.db.Favorite
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoriteAdapter(private val favorite: List<Favorite>, val context: Context?, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_parkir, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val ivTempatParkir: ImageView = view.find(R.id.iv_tempat_parkir)
    private val tvNamaLokasi: TextView = view.find(R.id.tv_nama_tempat)
    private val tvJenisLokasi: TextView = view.find(R.id.tv_jenis_lokasi)
    private val tvKapasitasMobil: TextView = view.find(R.id.tv_kapasitas_mobil)
    private val tvKapasitasMotor: TextView = view.find(R.id.tv_kapasitas_motor)
    private val tvKapasitasBus: TextView = view.find(R.id.tv_kapasitas_bus)

    fun bindItem(data: Favorite, listener: (Favorite) -> Unit) {
        tvNamaLokasi.text = data.namaTempat
        tvJenisLokasi.text = data.jenisLokasi
        tvKapasitasMobil.text = data.kapasitasMobil
        tvKapasitasMotor.text = data.kapasitasMotor
        tvKapasitasBus.text = data.kapasitasBus
        Glide.with(itemView.context)
            .load(data.picture)
            .into(ivTempatParkir)

        itemView.onClick {
            listener(data)
        }
    }
}