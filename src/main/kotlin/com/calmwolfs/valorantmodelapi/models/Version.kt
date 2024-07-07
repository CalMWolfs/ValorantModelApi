package com.calmwolfs.valorantmodelapi.models

import java.util.Date

data class Version(
    val branch: String,
    val version: String,
    val buildVersion: String,
    val buildDate: Date,
)