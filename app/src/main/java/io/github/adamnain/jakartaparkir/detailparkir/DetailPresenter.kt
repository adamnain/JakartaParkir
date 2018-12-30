package io.github.adamnain.jakartaparkir.detailparkir

import com.google.gson.Gson
import io.github.adamnain.jakartaparkir.api.ApiRepository
import io.github.adamnain.jakartaparkir.api.ParkirAPI
import io.github.adamnain.jakartaparkir.model.ParkirResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson
) {

    fun getDetailParkir(parkirId: String) {
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(ParkirAPI.getDetailParkir(parkirId)),
                ParkirResponse::class.java
            )

            uiThread {
                view.showDetail(data.parkir)
            }
        }
    }
}