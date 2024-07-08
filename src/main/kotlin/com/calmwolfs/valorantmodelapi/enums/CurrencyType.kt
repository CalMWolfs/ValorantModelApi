package com.calmwolfs.valorantmodelapi.enums

enum class CurrencyType(val displayName: String, val uuid: String) {
    FREE_AGENTS("Free Agents", "f08d4ae3-939c-4576-ab26-09ce1f23bb37"),
    KINGDOM_CREDITS("Kingdom Credits", "85ca954a-41f2-ce94-9b45-8ca3dd39a00d"),
    RADIANITE_POINTS("RADIANITE Points", "e59aa87c-4cbf-517a-5983-6e81511be9b7"),
    VALORANT_POINTS("VALORANT POINTS", "85ad13f7-3d1b-5128-9eb2-7cd8ee0b5741"),
    ;

    companion object {
        fun fromId(uuid: String): CurrencyType? {
            return entries.find { it.uuid == uuid }
        }

        fun fromName(displayName: String): CurrencyType? {
            return entries.find { it.displayName == displayName }
        }
    }
}
