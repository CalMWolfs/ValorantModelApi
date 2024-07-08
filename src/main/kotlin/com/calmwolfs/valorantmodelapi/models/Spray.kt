package com.calmwolfs.valorantmodelapi.models

data class Spray(
    val uuid: String,
    val displayName: String,
    val category: String?,
    val themeUuid: String?,
    val isNullSpray: Boolean,
    val hideIfNotOwned: Boolean,
    val displayIcon: String,
    val fullIcon: String,
    val fullTransparentIcon: String,
    val animationPng: String?,
    val animationGif: String?,
    val assetPath: String,
)