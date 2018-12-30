package io.github.adamnain.jakartaparkir.detailparkir

import io.github.adamnain.jakartaparkir.model.Parkir

interface DetailView {
    fun showDetail(data: List<Parkir>)
}