package io.github.adamnain.jakartaparkir.listparkir

import io.github.adamnain.jakartaparkir.model.Parkir

interface ListParkirView {
    fun showLoading()
    fun hideLoading()
    fun showListParkir(data: List<Parkir>)
}