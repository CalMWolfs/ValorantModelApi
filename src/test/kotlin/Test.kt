import com.calmwolfs.valorantmodelapi.ValorantModelApi

fun main(args: Array<String>) {
    val api = ValorantModelApi()
    val agents = api.getBuddies()
    println(agents)
}