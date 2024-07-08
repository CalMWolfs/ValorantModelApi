package com.calmwolfs.valorantmodelapi.models

import java.util.Date

data class Season(
    val uuid: String,
    val displayName: String,
    val title: String?,
    val type: String?,
    val startTime: Date,
    val endTime: Date,
    val parentUuid: String?,
    val assetPath: String,
)