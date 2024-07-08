package com.calmwolfs.valorantmodelapi.models

data class PlayerTitle(
    val uuid: String,
    val displayName: String,
    val titleText: String,
    val isbHiddenIfNotOwner: Boolean,
    val assetPath: String,
)