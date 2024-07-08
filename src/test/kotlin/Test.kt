import com.calmwolfs.valorantmodelapi.enums.AgentType
import com.calmwolfs.valorantmodelapi.models.LevelBorder

fun main(args: Array<String>) {
//    val testData = ValorantModelApi.getAgent(AgentType.BRIMSTONE)
//    println(testData)

    println(AgentType.fromId("asfsa"))

//    val data = ValorantModelApi.getLevelBorders()
//    createEnumValues(data)
}

fun createEnumValues(data: List<LevelBorder>) {
//    for (season in data) {
//        val seasonName = constructSeasonName(season, data) ?: continue
//
//        println("${formatEnumName(seasonName)}(\"${seasonName}\", \"${season.uuid}\"),")
//    }
    data.forEach {
        println("${formatEnumName(it.displayName)}(\"${it.displayName}\", \"${it.uuid}\"),")
    }
}

//fun constructSeasonName(season: Season, seasons: List<Season>): String? {
//    val seasonParent = season.parentUuid ?: return null
//    val parent = seasons.find { it.uuid == seasonParent } ?: return null
//    return "${parent.displayName} ${season.displayName}"
//}

fun formatEnumName(name: String?): String {
    name ?: return "NONE"
    return name.uppercase()
        .replace(" ", "_")
        .replace("-", "_")
        .replace("/", "")
        .replace(",", "")
        .replace(".", "")
        .replace("'", "")
        .replace("&_", "")
        .replace("&", "")
        .replace("!", "")
        .replace("+", "")
        .replace("\"", "")
        .replace("\\", "")
        .replace(":", "")
        .replace("?", "")
        .replace(";", "")
        .replace("%", "")
        .replace("#", "")
        .replace("_BUDDY", "")
        .replace("_SPRAY", "")
        .replace("_CARD", "")
        .replace("_TITLE", "")
        .replace("_BORDER", "")
        .replace("°", "")
        .replace("__", "_")
        .removeSuffix("_")
}