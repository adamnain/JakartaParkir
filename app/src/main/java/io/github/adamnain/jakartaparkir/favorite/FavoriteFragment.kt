package io.github.adamnain.jakartaparkir.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import com.google.gson.Gson
import io.github.adamnain.jakartaparkir.R
import io.github.adamnain.jakartaparkir.adapter.FavoriteAdapter
import io.github.adamnain.jakartaparkir.adapter.ListParkirAdapter
import io.github.adamnain.jakartaparkir.api.ApiRepository
import io.github.adamnain.jakartaparkir.db.Favorite
import io.github.adamnain.jakartaparkir.db.database
import io.github.adamnain.jakartaparkir.detailparkir.DetailActivity
import io.github.adamnain.jakartaparkir.listparkir.ListParkirPresenter
import io.github.adamnain.jakartaparkir.model.Parkir
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteFragment : Fragment() {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listParkir: RecyclerView
    private lateinit var adapter: FavoriteAdapter
    private var favorites: MutableList<Favorite> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteAdapter(favorites,ctx){
            val favorite = Parkir(
                idParkir = it.parkirId,
                pictureParkir = it.picture,
                namaTempatParkir = it.namaTempat,
                jenisLokasiParkir = it.jenisLokasi,
                kapasitasMobil = it.kapasitasMobil,
                kapasitasMotor = it.kapasitasMotor,
                kapasitasBusTruk = it.kapasitasBus

            )
            context!!.startActivity<DetailActivity>("PARKIR" to favorite)
        }
        listParkir.adapter = adapter

        showFavorite()
        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return UI {
            frameLayout {
                lparams(width = matchParent, height = wrapContent)
                swipeRefresh = swipeRefreshLayout {
                    id = R.id.swipe_refresh
                    setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                    verticalLayout {
                        lparams(width = matchParent, height = wrapContent)

                        listParkir = recyclerView {
                            id = R.id.rv_list_parkir
                            layoutManager = LinearLayoutManager(ctx)
                        }.lparams(width = matchParent, height = wrapContent)
                    }
                }
            }
        }.view
    }

    private fun showFavorite() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }


}
