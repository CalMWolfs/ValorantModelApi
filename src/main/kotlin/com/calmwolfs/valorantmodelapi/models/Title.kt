package com.calmwolfs.valorantmodelapi.models

data class Title(
    val uuid: String,
    val displayName: String,
    val isbHiddenIfNotOwner: Boolean,
    val assetPath: String
)