package com.calmwolfs.valorantmodelapi.models

data class Gear(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val assetPath: String,
    val shopData: ShopData,
)
