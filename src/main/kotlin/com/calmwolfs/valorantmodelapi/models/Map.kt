package com.calmwolfs.valorantmodelapi.models

data class ValorantMap(
    val uuid: String,
    val displayName: String,
    val tacticalDescription: String,
    val coordinates: String,
    val displayIcon: String?,
    val listViewIcon: String?,
    val listViewIconTall: String?,
    val splash: String,
    val stylizedBackgroundImage: String,
    val premierBackgroundImage: String,
    val assetPath: String,
    val mapUrl: String,
    val xMultiplier: Double,
    val yMultiplier: Double,
    val xScalarToAdd: Double,
    val yScalarToAdd: Double,
    val callouts: List<Callout>,
)

data class Callout(
    val regionName: String,
    val superRegionName: String,
    val location: Location,
)
