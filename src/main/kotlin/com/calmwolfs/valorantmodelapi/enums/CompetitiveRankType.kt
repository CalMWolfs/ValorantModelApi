package com.calmwolfs.valorantmodelapi.enums

import com.calmwolfs.valorantmodelapi.ValorantModelApi
import com.calmwolfs.valorantmodelapi.models.CompetitiveRank

enum class CompetitiveRankType(val displayName: String, val tier: Int) {
    UNRANKED("UNRANKED", 0),
    UNUSED1("Unused1", 1),
    UNUSED2("Unused2", 2),
    IRON_1("IRON 1", 3),
    IRON_2("IRON 2", 4),
    IRON_3("IRON 3", 5),
    BRONZE_1("BRONZE 1", 6),
    BRONZE_2("BRONZE 2", 7),
    BRONZE_3("BRONZE 3", 8),
    SILVER_1("SILVER 1", 9),
    SILVER_2("SILVER 2", 10),
    SILVER_3("SILVER 3", 11),
    GOLD_1("GOLD 1", 12),
    GOLD_2("GOLD 2", 13),
    GOLD_3("GOLD 3", 14),
    PLATINUM_1("PLATINUM 1", 15),
    PLATINUM_2("PLATINUM 2", 16),
    PLATINUM_3("PLATINUM 3", 17),
    DIAMOND_1("DIAMOND 1", 18),
    DIAMOND_2("DIAMOND 2", 19),
    DIAMOND_3("DIAMOND 3", 20),
    ASCENDANT_1("ASCENDANT 1", 21),
    ASCENDANT_2("ASCENDANT 2", 22),
    ASCENDANT_3("ASCENDANT 3", 23),
    IMMORTAL_1("IMMORTAL 1", 24),
    IMMORTAL_2("IMMORTAL 2", 25),
    IMMORTAL_3("IMMORTAL 3", 26),
    RADIANT("RADIANT", 27),
    UNKNOWN("UNKNOWN", -1)
    ;

    override fun toString(): String {
        return displayName
    }

    val rank: CompetitiveRank?
        get() = ValorantModelApi.getCompetitiveRank(this)

    companion object {
        fun fromTier(tier: Int?): CompetitiveRankType {
            return entries.find { it.tier == tier } ?: UNKNOWN
        }

        fun fromName(name: String): CompetitiveRankType {
            return entries.find { it.displayName == name } ?: UNKNOWN
        }
    }
}
