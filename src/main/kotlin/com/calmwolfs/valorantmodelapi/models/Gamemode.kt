package com.calmwolfs.valorantmodelapi.models

data class Gamemode(
    val uuid: String,
    val displayName: String,
    val duration: String,
    val isTeamVoiceAllowed: Boolean,
    val isMinimapHidden: Boolean,
    val orbCount: Int,
    val teamRoles: List<String>?,
    val gameFeatureOverrides: List<OverriddenGameFeature>?,
    val displayIcon: String,
    val assetPath: String,
)

data class OverriddenGameFeature(
    val featureName: String,
    val state: Boolean,
)

data class GamemodeEquippable(
    val uuid: String,
    val displayName: String,
    val category: String,
    val displayIcon: String,
    val killStreamIcon: String,
    val assetPath: String,
)