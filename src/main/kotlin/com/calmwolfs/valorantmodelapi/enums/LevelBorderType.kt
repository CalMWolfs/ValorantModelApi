package com.calmwolfs.valorantmodelapi.enums

enum class LevelBorderType(val displayName: String, val uuid: String) {
    LEVEL_1("Level 1 Border", "ebc736cd-4b6a-137b-e2b0-1486e31312c9"),
    LEVEL_20("Level 20 Border", "5156a90d-4d65-58d0-f6a8-48a0c003878a"),
    LEVEL_40("Level 40 Border", "9c4afb15-40d7-3557-062a-4bb198cb9958"),
    LEVEL_60("Level 60 Border", "e6238102-425c-a647-6685-e6af7f8982d9"),
    LEVEL_80("Level 80 Border", "49413ac2-4ed5-6953-5791-db838ccb58f3"),
    LEVEL_100("Level 100 Border", "e05371e3-4ec4-a53e-168a-c49346a75c19"),
    LEVEL_120("Level 120 Border", "7e7feff1-44c2-301e-767d-d9b2b1cd9051"),
    LEVEL_140("Level 140 Border", "53d4ed03-4b29-5913-aeda-80a41afcef3a"),
    LEVEL_160("Level 160 Border", "6f610ab6-4a21-63fd-ac19-4a9204bc2721"),
    LEVEL_180("Level 180 Border", "547ac9dd-495d-f11d-d921-3fbd14604ae0"),
    LEVEL_200("Level 200 Border", "bd1082ab-462c-3fb8-e049-28a9750acf0f"),
    LEVEL_220("Level 220 Border", "37a36996-41f3-6e26-c00b-46bf7c037482"),
    LEVEL_240("Level 240 Border", "5d0d6c6c-4f0a-dc65-e506-b786cc27dbe1"),
    LEVEL_260("Level 260 Border", "3635b061-4bf9-b937-55fe-44a4dd0ed3dc"),
    LEVEL_280("Level 280 Border", "ae5eda0d-476b-a159-959c-df93374f4a69"),
    LEVEL_300("Level 300 Border", "3d90bc3a-4626-71d6-a17c-93ae14d05fb0"),
    LEVEL_320("Level 320 Border", "674bbd9e-4a4f-208a-75fa-1d9dd7d7008f"),
    LEVEL_340("Level 340 Border", "d84cf377-4c21-1cdf-0260-4e8ebd9825f5"),
    LEVEL_360("Level 360 Border", "6c1fb61e-46e5-2908-5048-d4866cb64c3d"),
    LEVEL_380("Level 380 Border", "af1852a5-4e66-02a6-2ae3-ab8c885efb80"),
    LEVEL_400("Level 400 Border", "cbd1914e-43f8-7ae5-38c4-228bcbe58756"),
    LEVEL_420("Level 420 Border", "c8a4abff-4ace-f0a3-c9f3-db936791a697"),
    LEVEL_440("Level 440 Border", "086dd1ab-4889-793a-4b33-0a99e311fa25"),
    LEVEL_460("Level 460 Border", "08ab72f1-4fce-ddb5-5fd5-22abd3bc9d49"),
    LEVEL_480("Level 480 Border", "6694d7f7-4ab9-8545-5921-35a9ea8cec24"),
    ;

    companion object {
        fun fromId(uuid: String): LevelBorderType? {
            return entries.find { it.uuid == uuid }
        }

        fun fromName(displayName: String): LevelBorderType? {
            return entries.find { it.displayName == displayName }
        }
    }
}
