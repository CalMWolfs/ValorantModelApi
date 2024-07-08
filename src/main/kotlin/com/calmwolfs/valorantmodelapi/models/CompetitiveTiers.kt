package com.calmwolfs.valorantmodelapi.models

data class CompetitiveTiers(
    val uuid: String,
    val assetObjectName: String,
    val tiers: List<CompetitiveTier>
)

data class CompetitiveTier(
    val tier: Int,
    val tierName: String,
    val division: String,
    val divisionName: String,
    val color: String,
    val backgroundColor: String,
    val smallIcon: String?,
    val largeIcon: String?,
    val rankTriangleDownIcon: String?,
    val rankTriangleUpIcon: String?,
)
