import com.calmwolfs.valorantmodelapi.ValorantModelApi

fun main(args: Array<String>) {
    val agents = ValorantModelApi.getAgents()
    println(agents)
}