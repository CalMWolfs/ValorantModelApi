package com.calmwolfs.valorantmodelapi.enums

enum class GearType(val displayName: String, val uuid: String) {
    HEAVY_SHIELDS("Heavy Shields", "822bcab2-40a2-324e-c137-e09195ad7692"),
    LIGHT_SHIELDS("Light Shields", "4dec83d5-4902-9ab3-bed6-a7a390761157"),
    ;

    companion object {
        fun fromId(uuid: String): GearType? {
            return entries.find { it.uuid == uuid }
        }

        fun fromName(displayName: String): GearType? {
            return entries.find { it.displayName == displayName }
        }
    }
}
