package com.calmwolfs.valorantmodelapi.models

data class ShopData(
    val cost: Int,
    val category: String,
    val categoryText: String,
    val gridPosition: WeaponShopDataGridPosition?,
    val image: String?,
    val newImage: String,
    val newImage2: String?,
    val assetPath: String,
)
