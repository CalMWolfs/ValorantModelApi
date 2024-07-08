package com.calmwolfs.valorantmodelapi.enums

enum class CeremonyType(val displayName: String, val uuid: String) {
    ACE("ACE", "1e71c55c-476e-24ac-0687-e48b547dbb35"),
    CLOSER("CLOSER", "b41f4d69-4f9d-ffa9-2be8-e2878cf7f03b"),
    CLUTCH("CLUTCH", "a6100421-4ecb-bd55-7c23-e4899643f230"),
    FLAWLESS("FLAWLESS", "eb651c62-421f-98fc-8008-68bee9ec942d"),
    TEAM_ACE("TEAM ACE", "87c91747-4de4-635e-a64b-6ba4faeeae78"),
    THRIFTY("THRIFTY", "bf94f35e-4794-8add-dc7d-fb90a08d3d04"),
    ;

    companion object {
        fun fromId(id: String): CeremonyType? {
            return entries.find { it.uuid == id }
        }

        fun fromName(name: String): CeremonyType? {
            return entries.find { it.displayName == name }
        }
    }
}
