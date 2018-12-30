package io.github.adamnain.jakartaparkir.model

import com.google.gson.annotations.SerializedName

data class ParkirResponse(
    @SerializedName("data") val parkir: List<Parkir>)