package com.calmwolfs.valorantmodelapi.models

data class ContentTier(
    val uuid: String,
    val displayName: String,
    val devName: String,
    val rank: Int,
    val juiceValue: Int,
    val juiceCost: Int,
    val highlightColor: String,
    val displayIcon: String,
    val assetPath: String,
)