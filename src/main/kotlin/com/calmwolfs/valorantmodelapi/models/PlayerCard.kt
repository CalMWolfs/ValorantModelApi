package com.calmwolfs.valorantmodelapi.models

data class PlayerCard(
    val uuid: String,
    val displayName: String,
    val isHiddenIfNotOwner: Boolean,
    val themeUuid: String?,
    val displayIcon: String,
    val smallArt: String,
    val wideArt: String,
    val largeArt: String,
    val assetPath: String,
)