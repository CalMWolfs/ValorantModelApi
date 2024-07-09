package com.calmwolfs.valorantmodelapi.enums

import com.calmwolfs.valorantmodelapi.ValorantModelApi
import com.calmwolfs.valorantmodelapi.models.CompetitiveRank

enum class CompetitiveRankType(val displayName: String, val tier: Int) {
    UNRANKED("Unranked", 0),
    UNUSED1("Unused 1", 1),
    UNUSED2("Unused 2", 2),
    IRON_1("Iron 1", 3),
    IRON_2("Iron 2", 4),
    IRON_3("Iron 3", 5),
    BRONZE_1("Bronze 1", 6),
    BRONZE_2("Bronze 2", 7),
    BRONZE_3("Bronze 3", 8),
    SILVER_1("Silver 1", 9),
    SILVER_2("Silver 2", 10),
    SILVER_3("Silver 3", 11),
    GOLD_1("Gold 1", 12),
    GOLD_2("Gold 2", 13),
    GOLD_3("Gold 3", 14),
    PLATINUM_1("Platinum 1", 15),
    PLATINUM_2("Platinum 2", 16),
    PLATINUM_3("Platinum 3", 17),
    DIAMOND_1("Diamond 1", 18),
    DIAMOND_2("Diamond 2", 19),
    DIAMOND_3("Diamond 3", 20),
    ASCENDANT_1("Ascendant 1", 21),
    ASCENDANT_2("Ascendant 2", 22),
    ASCENDANT_3("Ascendant 3", 23),
    IMMORTAL_1("Immortal 1", 24),
    IMMORTAL_2("Immortal 2", 25),
    IMMORTAL_3("Immortal 3", 26),
    RADIANT("Radiant", 27),
    UNKNOWN("Unknown", -1)
    ;

    override fun toString(): String {
        return displayName
    }

    val rank: CompetitiveRank?
        get() = runCatching { ValorantModelApi.getCompetitiveRank(this) }.getOrNull()

    companion object {
        fun fromTier(tier: Int?): CompetitiveRankType {
            return entries.find { it.tier == tier } ?: run {
                ValorantModelApi.logUnknownId(tier.toString(), CompetitiveRankType)
                UNKNOWN
            }
        }

        fun fromName(name: String): CompetitiveRankType {
            return entries.find { it.displayName == name } ?: UNKNOWN
        }
    }
}
