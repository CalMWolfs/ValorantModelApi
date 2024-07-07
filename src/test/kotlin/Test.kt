import com.calmwolfs.valorantmodelapi.ValorantModelApi

fun main(args: Array<String>) {
    val start1 = System.currentTimeMillis()
    val agents1 = ValorantModelApi.getAgents().map { it.displayName }
    println(agents1)
    println("first done in ${System.currentTimeMillis() - start1}ms")

    val start2 = System.currentTimeMillis()
    val agents2 = ValorantModelApi.getAgents().map { it.displayName }
    println(agents2)
    println("second done in ${System.currentTimeMillis() - start2}ms")

    val start3 = System.currentTimeMillis()
    val agents3 = ValorantModelApi.getAgents(true).map { it.displayName }
    println(agents3)
    println("third done in ${System.currentTimeMillis() - start3}ms")
}