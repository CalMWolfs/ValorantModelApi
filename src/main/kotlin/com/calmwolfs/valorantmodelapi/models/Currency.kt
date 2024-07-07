package com.calmwolfs.valorantmodelapi.models

data class Currency(
    val uuid: String,
    val displayName: String,
    val displayNameSingular: String,
    val displayIcon: String,
    val largeIcon: String?,
    val assetPath: String,
)