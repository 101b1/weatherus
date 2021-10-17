package com.ilih.weatherus.domain.entity

data class CityDto(
    val id: Long,
    val name: String,
    val stateCode: String,
    val countryCode: String,
    val countryName: String,
    val latitude: Float,
    val longitude: Float,
    val favourite: Boolean
) {
    companion object {
        fun build(
            id: Long = 0L,
            name: String = "",
            stateCode: String = "",
            countryCode: String = "",
            countryName: String = "",
            latitude: Float = 0F,
            longitude: Float = 0F,
            favourite: Boolean = false
        ) = CityDto(
            id,
            name, stateCode, countryCode, countryName, latitude, longitude, favourite
        )
    }
}
