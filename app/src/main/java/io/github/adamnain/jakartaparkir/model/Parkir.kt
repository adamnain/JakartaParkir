package io.github.adamnain.jakartaparkir.model

import com.google.gson.annotations.SerializedName
import android.os.Parcel
import android.os.Parcelable

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

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idParkir)
        parcel.writeString(namaTempatParkir)
        parcel.writeString(jenisLokasiParkir)
        parcel.writeString(alamatParkir)
        parcel.writeString(luasParkir)
        parcel.writeString(kapasitasMobil)
        parcel.writeString(kapasitasMotor)
        parcel.writeString(kapasitasBusTruk)
        parcel.writeString(pictureParkir)
        parcel.writeString(latParkir)
        parcel.writeString(longParkir)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Parkir> {
        override fun createFromParcel(parcel: Parcel): Parkir {
            return Parkir(parcel)
        }

        override fun newArray(size: Int): Array<Parkir?> {
            return arrayOfNulls(size)
        }
    }
}