package io.github.adamnain.jakartaparkir.detailparkir

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.gson.Gson
import io.github.adamnain.jakartaparkir.R
import io.github.adamnain.jakartaparkir.api.ApiRepository
import io.github.adamnain.jakartaparkir.model.Parkir
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.list_item_parkir.*

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var id: String
    private lateinit var presenter: DetailPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        id = intent.getStringExtra("id")
        supportActionBar?.title = "Detail Parkir"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = DetailPresenter(this, ApiRepository(), Gson())
        presenter.getDetailParkir(id)
    }

    override fun showDetail(data: List<Parkir>) {
        Glide.with(this)
            .load(data[0].pictureParkir)
            .into(iv_detail_parkir)
        tv_nama_detail.text = data[0].namaTempatParkir
        tv_jenis_detail.text = data[0].jenisLokasiParkir
        tv_alamat_detail.text = data[0].alamatParkir
        tv_luas_detail.text = data[0].luasParkir
        tv_kapasitas_mobil_detail.text = data[0].kapasitasMobil
        tv_kapasitas_motor_detail.text = data[0].kapasitasMotor
        tv_kapasitas_bus_detail.text = data[0].kapasitasBusTruk
        tv_kapasitas_bus_detail.text = data[0].kapasitasBusTruk

    }
}
