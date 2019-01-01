package io.github.adamnain.jakartaparkir.detailparkir

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.gson.Gson
import io.github.adamnain.jakartaparkir.R
import io.github.adamnain.jakartaparkir.R.drawable.ic_add_to_favorites
import io.github.adamnain.jakartaparkir.R.drawable.ic_added_to_favorites
import io.github.adamnain.jakartaparkir.R.id.add_to_favorite
import io.github.adamnain.jakartaparkir.R.menu.detail_menu
import io.github.adamnain.jakartaparkir.api.ApiRepository
import io.github.adamnain.jakartaparkir.db.Favorite
import io.github.adamnain.jakartaparkir.db.database
import io.github.adamnain.jakartaparkir.model.Parkir
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.list_item_parkir.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import com.facebook.shimmer.ShimmerFrameLayout



class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var parkir: Parkir
    private lateinit var id: String
    private lateinit var presenter: DetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var shimmerContainer: ShimmerFrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        shimmerContainer = findViewById(R.id.shimmer_detail)
        shimmerContainer.startShimmerAnimation()

        parkir = intent.getParcelableExtra("PARKIR")
        id = parkir.idParkir.toString()
        favoriteState()
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
        tv_luas_detail.text = data[0].luasParkir
        tv_kapasitas_motor_detail.text = data[0].kapasitasMotor
        tv_kapasitas_mobil_detail.text = data[0].kapasitasMobil
        tv_kapasitas_bus_detail.text = data[0].kapasitasBusTruk
        tv_alamat_detail.text = data[0].alamatParkir
        tv_kapasitas_bus_detail.text = data[0].kapasitasBusTruk
        shimmerContainer.stopShimmerAnimation()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(PARKIR_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.PARKIR_ID to parkir.idParkir,
                    Favorite.PICTURE to parkir.pictureParkir,
                    Favorite.NAMA_TEMPAT to parkir.namaTempatParkir,
                    Favorite.JENIS_LOKASI to parkir.jenisLokasiParkir,
                    Favorite.KAPASITAS_MOBIL to parkir.kapasitasMobil,
                    Favorite.KAPASITAS_MOTOR to parkir.kapasitasMotor,
                    Favorite.KAPASITAS_BUS to parkir.kapasitasBusTruk

                )
            }
            swipe_match.snackbar("Added to Favorite").show()
        } catch (e: SQLiteConstraintException){
            swipe_match.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(PARKIR_ID = {id})",
                    "id" to id)
            }
            swipe_match.snackbar("Remove from Favorite").show()
        } catch (e: SQLiteConstraintException){
            swipe_match.snackbar( e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

}
