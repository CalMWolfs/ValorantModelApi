package com.calmwolfs.valorantmodelapi.enums

import com.calmwolfs.valorantmodelapi.ValorantModelApi
import com.calmwolfs.valorantmodelapi.models.GamemodeEquippable

enum class GamemodeEquippableType(val displayName: String, val uuid: String) {
    CLASSIC("Classic", "c5de005c-4bdc-26a7-a47d-c295eaaae9d8"),
    GOLDEN_GUN("Golden Gun", "3de32920-4a8f-0499-7740-648a5bf95470"),
    UNKNOWN("Unknown", "")
    ;

    override fun toString(): String {
        return displayName
    }

    val gamemodeEquippable: GamemodeEquippable?
        get() = ValorantModelApi.getGamemodeEquippable(this)

    companion object {
        fun fromId(id: String?): GamemodeEquippableType {
            return entries.find { it.uuid == id } ?: run {
                ValorantModelApi.logUnknownId(id, GamemodeEquippableType)
                UNKNOWN
            }
        }

        fun fromName(displayName: String): GamemodeEquippableType {
            return entries.find { it.displayName == displayName } ?: UNKNOWN
        }
    }
}
