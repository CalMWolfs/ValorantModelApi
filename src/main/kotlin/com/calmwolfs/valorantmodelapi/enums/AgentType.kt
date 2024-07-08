package com.calmwolfs.valorantmodelapi.enums

import com.calmwolfs.valorantmodelapi.ValorantModelApi
import com.calmwolfs.valorantmodelapi.models.Agent

enum class AgentType(val displayName: String, val uuid: String) {
    ASTRA("Astra", "41fb69c1-4189-7b37-f117-bcaf1e96f1bf"),
    BREACH("Breach", "5f8d3a7f-467b-97f3-062c-13acf203c006"),
    BRIMSTONE("Brimstone", "9f0d8ba9-4140-b941-57d3-a7ad57c6b417"),
    CHAMBER("Chamber", "22697a3d-45bf-8dd7-4fec-84a9e28c69d7"),
    CLOVE("Clove", "1dbf2edd-4729-0984-3115-daa5eed44993"),
    CYPHER("Cypher", "117ed9e3-49f3-6512-3ccf-0cada7e3823b"),
    DEADLOCK("Deadlock", "cc8b64c8-4b25-4ff9-6e7f-37b4da43d235"),
    FADE("Fade", "dade69b4-4f5a-8528-247b-219e5a1facd6"),
    GEKKO("Gekko", "e370fa57-4757-3604-3648-499e1f642d3f"),
    HARBOR("Harbor", "95b78ed7-4637-86d9-7e41-71ba8c293152"),
    ISO("Iso", "0e38b510-41a8-5780-5e8f-568b2a4f2d6c"),
    JETT("Jett", "add6443a-41bd-e414-f6ad-e58d267f4e95"),
    KAYO("KAY/O", "601dbbe7-43ce-be57-2a40-4abd24953621"),
    KILLJOY("Killjoy", "1e58de9c-4950-5125-93e9-a0aee9f98746"),
    NEON("Neon", "bb2a4828-46eb-8cd1-e765-15848195d751"),
    OMEN("Omen", "8e253930-4c05-31dd-1b6c-968525494517"),
    PHOENIX("Phoenix", "eb93336a-449b-9c1b-0a54-a891f7921d69"),
    RAZE("Raze", "f94c3b30-42be-e959-889c-5aa313dba261"),
    REYNA("Reyna", "a3bfb853-43b2-7238-a4f1-ad90e9e46bcc"),
    SAGE("Sage", "569fdd95-4d10-43ab-ca70-79becc718b46"),
    SKYE("Skye", "6f2a04ca-43e0-be17-7f36-b3908627744d"),
    SOVA("Sova", "320b2a48-4d9b-a075-30f1-1f93a9b638fa"),
    VIPER("Viper", "707eab51-4836-f488-046a-cda6bf494859"),
    YORU("Yoru", "7f94d92c-4234-0a36-9646-3a87eb8b5c89"),
    UNKNOWN("Unknown", "unknown"),
    ;

    override fun toString(): String {
        return displayName
    }

    val agent: Agent?
        get() = ValorantModelApi.getAgent(this)

    companion object {
        fun fromId(uuid: String?): AgentType {
            return entries.find { it.uuid == uuid } ?: UNKNOWN
        }

        fun fromName(displayName: String): AgentType {
            return entries.find { it.displayName == displayName } ?: UNKNOWN
        }
    }
}
