package com.calmwolfs.valorantmodelapi.models

data class LevelBorder(
    val uuid: String,
    val displayName: String,
    val startingLevel: Int,
    val levelNumberAppearance: String,
    val smallPlayerCardAppearance: String,
    val assetPath: String,
)
