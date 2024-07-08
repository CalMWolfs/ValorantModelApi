import com.calmwolfs.valorantmodelapi.ValorantModelApi
import com.calmwolfs.valorantmodelapi.models.CompetitiveRank
import com.calmwolfs.valorantmodelapi.models.Season
import com.calmwolfs.valorantmodelapi.models.Weapon

fun main(args: Array<String>) {
    val data = ValorantModelApi.getCompetitiveRanks()
    createEnumValues(data)
}

fun createEnumValues(data: List<CompetitiveRank>) {
//    for (season in data) {
//        val seasonName = constructSeasonName(season, data) ?: continue
//
//        println("${formatEnumName(seasonName)}(\"${seasonName}\", \"${season.uuid}\"),")
//    }
    data.forEach {
        println("${formatEnumName(it.tierName)}(\"${it.tierName}\", \"${it.tier}\"),")
    }
}

fun constructSeasonName(season: Season, seasons: List<Season>): String? {
    val seasonParent = season.parentUuid ?: return null
    val parent = seasons.find { it.uuid == seasonParent } ?: return null
    return "${parent.displayName} ${season.displayName}"
}

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
        .replace("°", "")
        .replace("__", "_")
        .removeSuffix("_")
}