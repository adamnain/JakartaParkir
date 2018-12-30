package io.github.adamnain.jakartaparkir.listparkir


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import io.github.adamnain.jakartaparkir.R
import io.github.adamnain.jakartaparkir.R.color.colorAccent
import io.github.adamnain.jakartaparkir.adapter.ListParkirAdapter
import io.github.adamnain.jakartaparkir.api.ApiRepository
import io.github.adamnain.jakartaparkir.detailparkir.DetailActivity
import io.github.adamnain.jakartaparkir.model.Parkir
import io.github.adamnain.jakartaparkir.utils.invisible
import io.github.adamnain.jakartaparkir.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class ListParkirFragment : Fragment(), ListParkirView {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listParkir: RecyclerView
    private lateinit var adapter: ListParkirAdapter
    private lateinit var presenter: ListParkirPresenter
    private lateinit var progressBar: ProgressBar
    private var parkir: MutableList<Parkir> = mutableListOf()
    private lateinit var spinner: Spinner
    private lateinit var kategori: String



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        setSpinner()
        adapter = ListParkirAdapter(parkir, ctx){
            context!!.startActivity<DetailActivity>("PARKIR" to it)
        }
        listParkir.adapter = adapter

        presenter = ListParkirPresenter(this, ApiRepository(), Gson())

        swipeRefresh.onRefresh {
            presenter.getListParkirKategori(kategori)
        }

        presenter.getListParkirKategori(kategori)

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
                    setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                    verticalLayout {
                        lparams(width = matchParent, height = wrapContent)

                        spinner = spinner()

                        listParkir = recyclerView {
                            id = R.id.rv_list_parkir
                            layoutManager = LinearLayoutManager(ctx)
                        }.lparams(width = matchParent, height = wrapContent)

                        progressBar = progressBar {
                        }.lparams(width = matchParent, height = wrapContent)
                    }
                }
            }
        }.view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showListParkir(data: List<Parkir>) {
        swipeRefresh.isRefreshing = false
        parkir.clear()
        parkir.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private fun setSpinner(){
        val strKategori = resources.getStringArray(R.array.querykategori)
        kategori = strKategori[0].toString()

        val spinnerItems = resources.getStringArray(R.array.titlekategori)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Retrieve the team name that was clicked by user
                kategori = strKategori[position].toString()
                presenter.getListParkirKategori(kategori)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.Search)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getSearch(query)
                return true
            }
            override fun onQueryTextChange(query: String?): Boolean = true
        })
        super.onCreateOptionsMenu(menu, inflater)
    }



}
