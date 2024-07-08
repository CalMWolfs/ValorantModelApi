package com.calmwolfs.valorantmodelapi.models

data class Gamemode(
    val uuid: String,
    val displayName: String,
    val description: String,
    val duration: String,
    val economyType: String?,
    val allowsMatchTimeouts: Boolean,
    val isTeamVoiceAllowed: Boolean,
    val isMinimapHidden: Boolean,
    val orbCount: Int,
    val roundsPerHalf: Int,
    val teamRoles: List<String>?,
    val gameFeatureOverrides: List<OverriddenGameFeature>?,
    val gameRuleBoolOverrides: List<OverriddenGameBool>?,
    val displayIcon: String,
    val listViewIconTall: String,
    val assetPath: String,
)

data class OverriddenGameFeature(
    val featureName: String,
    val state: Boolean,
)

data class OverriddenGameBool(
    val ruleName: String,
    val state: Boolean,
)
