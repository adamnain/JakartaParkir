package io.github.adamnain.jakartaparkir.model

import com.google.gson.annotations.SerializedName

data class Parkir(

    @SerializedName("id")
    var idParkir:String? = null,

    @SerializedName("nama_tempat_parkir")
    var namaTempatParkir:String? = null,

    @SerializedName("jenis_lokasi_parkir")
    var jenisLokasiParkir:String? = null,

    @SerializedName("alamat")
    var alamatParkir:String? = null,

    @SerializedName("luas_m2")
    var luasParkir:String? = null,

    @SerializedName("kapasitas_mobil")
    var kapasitasMobil:String? = null,

    @SerializedName("kapasitas_motor")
    var kapasitasMotor:String? = null,

    @SerializedName("kapasitas_bus_truk")
    var kapasitasBusTruk:String? = null,

    @SerializedName("picture")
    var pictureParkir:String? = null,

    @SerializedName("lat")
    var latParkir:String? = null,

    @SerializedName("long")
    var longParkir:String? = null

)