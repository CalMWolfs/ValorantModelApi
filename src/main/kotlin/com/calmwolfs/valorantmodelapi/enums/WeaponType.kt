package com.calmwolfs.valorantmodelapi.enums

import com.calmwolfs.valorantmodelapi.ValorantModelApi
import com.calmwolfs.valorantmodelapi.models.Weapon

enum class WeaponType(val displayName: String, val uuid: String) {
    ARES("Ares", "55d8a0f4-4274-ca67-fe2c-06ab45efdf58"),
    BUCKY("Bucky", "910be174-449b-c412-ab22-d0873436b21b"),
    BULLDOG("Bulldog", "ae3de142-4d85-2547-dd26-4e90bed35cf7"),
    CLASSIC("Classic", "29a0cfab-485b-f5d5-779a-b59f85e204a8"),
    FRENZY("Frenzy", "44d4e95c-4157-0037-81b2-17841bf2e8e3"),
    GHOST("Ghost", "1baa85b4-4c70-1284-64bb-6481dfc3bb4e"),
    GUARDIAN("Guardian", "4ade7faa-4cf1-8376-95ef-39884480959b"),
    JUDGE("Judge", "ec845bf4-4f79-ddda-a3da-0db3774b2794"),
    MARSHAL("Marshal", "c4883e50-4494-202c-3ec3-6b8a9284f00b"),
    MELEE("Melee", "2f59173c-4bed-b6c3-2191-dea9b58be9c7"),
    ODIN("Odin", "63e6c2b6-4a8e-869c-3d4c-e38355226584"),
    OPERATOR("Operator", "a03b24d3-4319-996d-0f8c-94bbfba1dfc7"),
    OUTLAW("Outlaw", "5f0aaf7a-4289-3998-d5ff-eb9a5cf7ef5c"),
    PHANTOM("Phantom", "ee8e8d15-496b-07ac-e5f6-8fae5d4c7b1a"),
    SHERIFF("Sheriff", "e336c6b8-418d-9340-d77f-7a9e4cfe0702"),
    SHORTY("Shorty", "42da8ccc-40d5-affc-beec-15aa47b42eda"),
    SPECTRE("Spectre", "462080d1-4035-2937-7c09-27aa2a5c27a7"),
    STINGER("Stinger", "f7e1b454-4ad4-1063-ec0a-159e56b58941"),
    VANDAL("Vandal", "9c82e19d-4575-0200-1a81-3eacf00cf872"),
    UNKNOWN("Unknown", "")
    ;

    override fun toString(): String {
        return displayName
    }

    val weapon: Weapon?
        get() = runCatching { ValorantModelApi.getWeapon(this) }.getOrNull()

    companion object {
        fun fromId(id: String?): WeaponType {
            return entries.find { it.uuid == id } ?: run {
                ValorantModelApi.logUnknownId(id, WeaponType)
                UNKNOWN
            }
        }

        fun fromName(displayName: String): WeaponType {
            return entries.find { it.displayName == displayName } ?: UNKNOWN
        }
    }
}
