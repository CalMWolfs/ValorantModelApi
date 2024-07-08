package com.calmwolfs.valorantmodelapi.models

data class CompetitiveTier(
    val uuid: String,
    val assetObjectName: String,
    val tiers: List<CompetitiveRank>
)

data class CompetitiveRank(
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
