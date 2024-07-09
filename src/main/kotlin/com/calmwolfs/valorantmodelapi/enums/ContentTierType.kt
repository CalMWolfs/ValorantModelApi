package com.calmwolfs.valorantmodelapi.enums

import com.calmwolfs.valorantmodelapi.ValorantModelApi
import com.calmwolfs.valorantmodelapi.models.ContentTier

enum class ContentTierType(val displayName: String, val uuid: String) {
    DELUXE_EDITION("Deluxe Edition", "0cebb8be-46d7-c12a-d306-e9907bfc5a25"),
    EXCLUSIVE_EDITION("Exclusive Edition", "e046854e-406c-37f4-6607-19a9ba8426fc"),
    PREMIUM_EDITION("Premium Edition", "60bca009-4182-7998-dee7-b8a2558dc369"),
    SELECT_EDITION("Select Edition", "12683d76-48d7-84a3-4e09-6985794f0445"),
    ULTRA_EDITION("Ultra Edition", "411e4a55-4e59-7757-41f0-86a53f101bb5"),
    UNKNOWN("Unknown", "")
    ;

    override fun toString(): String {
        return displayName
    }

    val contentTier: ContentTier?
        get() = runCatching { ValorantModelApi.getContentTier(this) }.getOrNull()

    companion object {
        fun fromId(id: String?): ContentTierType {
            return entries.find { it.uuid == id } ?: run {
                ValorantModelApi.logUnknownId(id, ContentTierType)
                UNKNOWN
            }
        }

        fun fromName(displayName: String): ContentTierType {
            return entries.find { it.displayName == displayName } ?: UNKNOWN
        }
    }
}
