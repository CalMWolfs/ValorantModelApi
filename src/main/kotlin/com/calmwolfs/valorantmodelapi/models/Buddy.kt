package com.calmwolfs.valorantmodelapi.models

data class Buddy(
     val uuid: String,
     val displayName: String,
     val themeUuid: String?,
     val displayIcon: String,
     val assetPath: String,
     val isHiddenIfNotOwned: Boolean
)