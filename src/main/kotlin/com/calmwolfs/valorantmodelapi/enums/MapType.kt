package com.calmwolfs.valorantmodelapi.enums

import com.calmwolfs.valorantmodelapi.ValorantModelApi
import com.calmwolfs.valorantmodelapi.models.ValorantMap

enum class MapType(val displayName: String, val uuid: String) {
    ASCENT("Ascent", "7eaecc1b-4337-bbf6-6ab9-04b8f06b3319"),
    SPLIT("Split", "d960549e-485c-e861-8d71-aa9d1aed12a2"),
    FRACTURE("Fracture", "b529448b-4d60-346e-e89e-00a4c527a405"),
    BIND("Bind", "2c9d57ec-4431-9c5e-2939-8f9ef6dd5cba"),
    BREEZE("Breeze", "2fb9a4fd-47b8-4e7d-a969-74b4046ebd53"),
    DISTRICT("District", "690b3ed2-4dff-945b-8223-6da834e30d24"),
    KASBAH("Kasbah", "12452a9d-48c3-0b02-e7eb-0381c3520404"),
    DRIFT("Drift", "2c09d728-42d5-30d8-43dc-96a05cc7ee9d"),
    PIAZZA("Piazza", "de28aa9b-4cbe-1003-320e-6cb3ec309557"),
    ABYSS("Abyss", "224b0a95-48b9-f703-1bd8-67aca101a61f"),
    LOTUS("Lotus", "2fe4ed3a-450a-948b-6d6b-e89a78e680a9"),
    SUNSET("Sunset", "92584fbe-486a-b1b2-9faa-39b0f486b498"),
    PEARL("Pearl", "fd267378-4d1d-484f-ff52-77821ed10dc2"),
    ICEBOX("Icebox", "e2ad5c54-4114-a870-9641-8ea21279579a"),
    THE_RANGE("The Range", "ee613ee9-28b7-4beb-9666-08db13bb2244"),
    HAVEN("Haven", "2bee0dc9-4ffe-519b-1cbd-7fbe763a6047"),
    ;

    val map: ValorantMap?
        get() = ValorantModelApi.getMap(this)

    companion object {
        fun fromId(uuid: String): MapType? {
            return entries.find { it.uuid == uuid }
        }

        fun fromName(displayName: String): MapType? {
            return entries.find { it.displayName == displayName }
        }
    }
}
