package com.calmwolfs.valorantmodelapi

import com.calmwolfs.valorantmodelapi.models.Agent
import com.calmwolfs.valorantmodelapi.models.Buddy
import com.calmwolfs.valorantmodelapi.models.Ceremony
import com.calmwolfs.valorantmodelapi.models.CompetitiveTiers
import com.calmwolfs.valorantmodelapi.models.StoreBundle
import com.calmwolfs.valorantmodelapi.models.PlayerCard
import com.calmwolfs.valorantmodelapi.models.ContentTier
import com.calmwolfs.valorantmodelapi.models.Currency
import com.calmwolfs.valorantmodelapi.models.Gamemode
import com.calmwolfs.valorantmodelapi.models.GamemodeEquippable
import com.calmwolfs.valorantmodelapi.models.Gear
import com.calmwolfs.valorantmodelapi.models.Season
import com.calmwolfs.valorantmodelapi.models.Spray
import com.calmwolfs.valorantmodelapi.models.Theme
import com.calmwolfs.valorantmodelapi.models.PlayerTitle
import com.calmwolfs.valorantmodelapi.models.ValorantMap
import com.calmwolfs.valorantmodelapi.models.Version
import com.calmwolfs.valorantmodelapi.models.Weapon
import com.calmwolfs.valorantmodelapi.utils.GsonUtils
import com.google.gson.JsonObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object ValorantModelApi {

    private val projectVersion = System.getProperty("project.version")
    private const val BASE_URL = "https://valorant-api.com/v1"

    private val requestCache = mutableMapOf<String, Any>()

    // todo allow requesting a single value instead of all, could also introduce coroutines to get all very quickly


    fun getAgents(force: Boolean = false) = sendRequestList<Agent>("agents?isPlayableCharacter=true", force)
    fun getBuddies(force: Boolean = false) = sendRequestList<Buddy>("buddies", force)
    fun getBundles(force: Boolean = false) = sendRequestList<StoreBundle>("bundles", force)
    fun getCeremonies(force: Boolean = false) = sendRequestList<Ceremony>("ceremonies", force)
    fun getCompetitiveTiers(force: Boolean = false) = sendRequestList<CompetitiveTiers>("competitivetiers", force)
    // want the latest one to get all ranks
    fun getContentTiers(force: Boolean = false) = sendRequestList<ContentTier>("contenttiers", force)
    // todo add contracts
    fun getCurrencies(force: Boolean = false) = sendRequestList<Currency>("currencies", force)
    // todo add events
    fun getGamemodeEquippable(force: Boolean = false) = sendRequestList<GamemodeEquippable>("gamemodes/equippables", force)
    fun getGamemodes(force: Boolean = false) = sendRequestList<Gamemode>("gamemodes", force)
    fun getGear(force: Boolean = false) = sendRequestList<Gear>("gear", force)
    // todo add level borders
    fun getMaps(force: Boolean = false) = sendRequestList<ValorantMap>("maps", force)
    fun getPlayerCards(force: Boolean = false) = sendRequestList<PlayerCard>("playercards", force)
    fun getPlayerTitles(force: Boolean = false) = sendRequestList<PlayerTitle>("playertitles", force)
    fun getSeasons(force: Boolean = false) = sendRequestList<Season>("seasons", force)
    fun getSprays(force: Boolean = false) = sendRequestList<Spray>("sprays", force)
    fun getThemes(force: Boolean = false) = sendRequestList<Theme>("themes", force)
    fun getVersion(force: Boolean = false) = sendRequest<Version>("version", force)
    fun getWeapons(force: Boolean = false) = sendRequestList<Weapon>("weapons", force)

    fun clearCache() {
        requestCache.clear()
    }

    @Throws(IOException::class)
    private inline fun <reified T : Any> sendRequest(requestPath: String, force: Boolean): T {
        if (!force && requestCache.containsKey(requestPath)) {
            return requestCache[requestPath] as T
        }
        val responseJsonObject = getRawJsonResponse(requestPath)
        val result = GsonUtils.gson.fromJson(responseJsonObject.getAsJsonObject("data"), T::class.java)
        requestCache[requestPath] = result
        return result
    }

    @Throws(IOException::class)
    @Suppress("UNCHECKED_CAST")
    private inline fun <reified T : Any> sendRequestList(requestPath: String, force: Boolean): List<T> {
        if (!force && requestCache.containsKey(requestPath)) {
            return requestCache[requestPath] as List<T>
        }
        val responseJsonObject = getRawJsonResponse(requestPath)
        val data = responseJsonObject.getAsJsonArray("data")
        val result = mutableListOf<T>()
        data.forEach {
            result.add(GsonUtils.gson.fromJson(it, T::class.java))
        }
        requestCache[requestPath] = result
        return result
    }

    @Throws(IOException::class)
    private fun getRawJsonResponse(requestPath: String): JsonObject {
        val url = "$BASE_URL/$requestPath"
        val connection = URL(url).openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("User-Agent", "ValorantModelApi/$projectVersion")

        connection.connect()

        when (val responseCode = connection.responseCode) {
            200 -> Unit
            else -> {
                throw IOException("Unknown error response code: $responseCode")
            }
        }

        val response = connection.inputStream.bufferedReader().use { it.readText() }
        return GsonUtils.gson.fromJson(response, JsonObject::class.java)
    }
}
