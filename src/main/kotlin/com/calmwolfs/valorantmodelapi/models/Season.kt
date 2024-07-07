package com.calmwolfs.valorantmodelapi.models

import java.util.Date

data class Season(
    val uuid: String,
    val displayName: String,
    val startTime: Date,
    val endTime: Date,
    val borders: List<SeasonBorder>,
    val assetPath: String,
)

data class SeasonBorder(
    val uuid: String,
    val winsRequired: Int,
    val level: Int,
    val displayIcon: String,
    val smallIcon: String,
    val assetPath: String,
)