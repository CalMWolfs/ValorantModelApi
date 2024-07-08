package com.calmwolfs.valorantmodelapi.models

import java.util.Date

data class Version(
    val manifestId: String,
    val branch: String,
    val version: String,
    val buildVersion: String,
    val engineVersion: String,
    val riotClientVersion: String,
    val riotClientBuild: String,
    val buildDate: Date,
)