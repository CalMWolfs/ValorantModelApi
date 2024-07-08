package com.calmwolfs.valorantmodelapi.enums

enum class GamemodeType(val displayName: String, val uuid: String) {
    DEATHMATCH("Deathmatch", "a8790ec5-4237-f2f0-e93b-08a8e89865b2"),
    ESCALATION("Escalation", "a4ed6518-4741-6dcb-35bd-f884aecdc859"),
    ONBOARDING("Onboarding", "d2b4e425-4cab-8d95-eb26-bb9b444551dc"),
    REPLICATION("Replication", "4744698a-4513-dc96-9c22-a9aa437e4a58"),
    SNOWBALL_FIGHT("Snowball Fight", "57038d6d-49b1-3a74-c5ef-3395d9f23a97"),
    SPIKE_RUSH("Spike Rush", "e921d1e6-416b-c31f-1291-74930c330b7b"),
    STANDARD("Standard", "96bd3920-4f36-d026-2b28-c683eb0bcac5"),
    SWIFTPLAY("Swiftplay", "5d0f264b-4ebe-cc63-c147-809e1374484b"),
    TEAM_DEATHMATCH("Team Deathmatch", "e086db66-47fd-e791-ca81-06a645ac7661"),
    THE_RANGE("The Range", "e2dc3878-4fe5-d132-28f8-3d8c259efcc6"),
    ;

    companion object {
        fun fromId(uuid: String): GamemodeType? {
            return entries.find { it.uuid == uuid }
        }

        fun fromName(displayName: String): GamemodeType? {
            return entries.find { it.displayName == displayName }
        }
    }
}
