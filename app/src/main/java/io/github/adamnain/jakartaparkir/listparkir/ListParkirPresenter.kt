package io.github.adamnain.jakartaparkir.listparkir

import com.google.gson.Gson
import io.github.adamnain.jakartaparkir.api.ApiRepository
import io.github.adamnain.jakartaparkir.api.ParkirAPI
import io.github.adamnain.jakartaparkir.model.ParkirResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ListParkirPresenter(private val view: ListParkirView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson){
//    fun getListParkir() {
//        view.showLoading()
//        doAsync {
//            val data = gson.fromJson(apiRepository
//                .doRequest(ParkirAPI.getListParkir()),
//                ParkirResponse::class.java
//            )
//
//            uiThread {
//                view.hideLoading()
//                view.showListParkir(data.parkir)
//            }
//        }
//    }

    fun getListParkirKategori(kategori: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(ParkirAPI.getListParkirKategori(kategori)),
                ParkirResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showListParkir(data.parkir)
            }
        }
    }

    fun getSearch(keywoard: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(ParkirAPI.getSearch(keywoard)),
                ParkirResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showListParkir(data.parkir)
            }
        }
    }
}