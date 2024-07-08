package com.calmwolfs.valorantmodelapi.enums

import com.calmwolfs.valorantmodelapi.ValorantModelApi
import com.calmwolfs.valorantmodelapi.models.Season

enum class SeasonType(val displayName: String, val uuid: String) {
    CLOSED_BETA("Closed Beta", "0df5adb9-4dcb-6899-1306-3e9860661dd3"),
    EPISODE_1_ACT_1("EPISODE 1 ACT 1", "3f61c772-4560-cd3f-5d3f-a7ab5abda6b3"),
    EPISODE_1_ACT_2("EPISODE 1 ACT 2", "0530b9c4-4980-f2ee-df5d-09864cd00542"),
    EPISODE_1_ACT_3("EPISODE 1 ACT 3", "46ea6166-4573-1128-9cea-60a15640059b"),
    EPISODE_2_ACT_1("EPISODE 2 ACT 1", "97b6e739-44cc-ffa7-49ad-398ba502ceb0"),
    EPISODE_2_ACT_2("EPISODE 2 ACT 2", "ab57ef51-4e59-da91-cc8d-51a5a2b9b8ff"),
    EPISODE_2_ACT_3("EPISODE 2 ACT 3", "52e9749a-429b-7060-99fe-4595426a0cf7"),
    EPISODE_3_ACT_1("EPISODE 3 ACT 1", "2a27e5d2-4d30-c9e2-b15a-93b8909a442c"),
    EPISODE_3_ACT_2("EPISODE 3 ACT 2", "4cb622e1-4244-6da3-7276-8daaf1c01be2"),
    EPISODE_3_ACT_3("EPISODE 3 ACT 3", "a16955a5-4ad0-f761-5e9e-389df1c892fb"),
    EPISODE_4_ACT_1("EPISODE 4 ACT 1", "573f53ac-41a5-3a7d-d9ce-d6a6298e5704"),
    EPISODE_4_ACT_2("EPISODE 4 ACT 2", "d929bc38-4ab6-7da4-94f0-ee84f8ac141e"),
    EPISODE_4_ACT_3("EPISODE 4 ACT 3", "3e47230a-463c-a301-eb7d-67bb60357d4f"),
    EPISODE_5_ACT_1("EPISODE 5 ACT 1", "67e373c7-48f7-b422-641b-079ace30b427"),
    EPISODE_5_ACT_2("EPISODE 5 ACT 2", "7a85de9a-4032-61a9-61d8-f4aa2b4a84b6"),
    EPISODE_5_ACT_3("EPISODE 5 ACT 3", "aca29595-40e4-01f5-3f35-b1b3d304c96e"),
    EPISODE_6_ACT_1("EPISODE 6 ACT 1", "9c91a445-4f78-1baa-a3ea-8f8aadf4914d"),
    EPISODE_6_ACT_2("EPISODE 6 ACT 2", "34093c29-4306-43de-452f-3f944bde22be"),
    EPISODE_6_ACT_3("EPISODE 6 ACT 3", "2de5423b-4aad-02ad-8d9b-c0a931958861"),
    EPISODE_7_ACT_1("EPISODE 7 ACT 1", "0981a882-4e7d-371a-70c4-c3b4f46c504a"),
    EPISODE_7_ACT_2("EPISODE 7 ACT 2", "03dfd004-45d4-ebfd-ab0a-948ce780dac4"),
    EPISODE_7_ACT_3("EPISODE 7 ACT 3", "4401f9fd-4170-2e4c-4bc3-f3b4d7d150d1"),
    EPISODE_8_ACT_1("EPISODE 8 ACT 1", "ec876e6c-43e8-fa63-ffc1-2e8d4db25525"),
    EPISODE_8_ACT_2("EPISODE 8 ACT 2", "22d10d66-4d2a-a340-6c54-408c7bd53807"),
    EPISODE_8_ACT_3("EPISODE 8 ACT 3", "4539cac3-47ae-90e5-3d01-b3812ca3274e"),
    EPISODE_9_ACT_1("EPISODE 9 ACT 1", "52ca6698-41c1-e7de-4008-8994d2221209"),
    EPISODE_9_ACT_2("EPISODE 9 ACT 2", "292f58db-4c17-89a7-b1c0-ba988f0e9d98"),
    EPISODE_9_ACT_3("EPISODE 9 ACT 3", "dcde7346-4085-de4f-c463-2489ed47983b"),
    UNKNOWN("Unknown", "")
    ;

    override fun toString(): String {
        return displayName
    }

    val season: Season?
        get() = ValorantModelApi.getSeason(this)

    companion object {
        fun fromId(id: String?): SeasonType {
            return entries.find { it.uuid == id } ?: run {
                ValorantModelApi.logUnknownId(id, SeasonType)
                UNKNOWN
            }
        }

        fun fromName(displayName: String): SeasonType {
            return entries.find { it.displayName == displayName } ?: UNKNOWN
        }
    }
}
