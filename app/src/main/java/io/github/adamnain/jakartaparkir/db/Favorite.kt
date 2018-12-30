package io.github.adamnain.jakartaparkir.db

data class Favorite(
    val id: Long?,
    val parkirId: String?,
    val picture: String?,
    val namaTempat: String?,
    val jenisLokasi: String?,
    val kapasitasMobil: String?,
    val kapasitasMotor: String?,
    val kapasitasBus: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val PARKIR_ID: String = "PARKIR_ID"
        const val PICTURE: String = "PICTURE"
        const val NAMA_TEMPAT: String = "NAMA_TEMPAT"
        const val JENIS_LOKASI: String = "JENIS_LOKASI"
        const val KAPASITAS_MOBIL: String = "KAPASITAS_MOBIL"
        const val KAPASITAS_MOTOR: String = "KAPASITAS_MOTOR"
        const val KAPASITAS_BUS: String = "KAPASITAS_BUS"
    }
}