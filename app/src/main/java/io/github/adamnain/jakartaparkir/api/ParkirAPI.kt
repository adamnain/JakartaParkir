package io.github.adamnain.jakartaparkir.api

import io.github.adamnain.jakartaparkir.BuildConfig

object ParkirAPI{

    fun getListParkirKategori(kategori: String?): String{
        return BuildConfig.BASE_URL + "parkir/" + kategori
    }

    fun getSearch(keyword: String?): String{
        return BuildConfig.BASE_URL + "parkir/search/?keyword=" + keyword
    }

    fun getDetailParkir(id: String?): String{
        return BuildConfig.BASE_URL + "parkir/detail/" + id
    }

}