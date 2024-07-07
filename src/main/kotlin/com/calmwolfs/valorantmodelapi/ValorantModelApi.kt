package com.calmwolfs.valorantmodelapi

import com.calmwolfs.valorantmodelapi.models.Agent
import com.calmwolfs.valorantmodelapi.models.Buddy
import com.calmwolfs.valorantmodelapi.models.Card
import com.calmwolfs.valorantmodelapi.models.ContentTier
import com.calmwolfs.valorantmodelapi.models.Currency
import com.calmwolfs.valorantmodelapi.models.Gamemode
import com.calmwolfs.valorantmodelapi.models.GamemodeEquippable
import com.calmwolfs.valorantmodelapi.models.Season
import com.calmwolfs.valorantmodelapi.models.Spray
import com.calmwolfs.valorantmodelapi.models.Theme
import com.calmwolfs.valorantmodelapi.models.Title
import com.calmwolfs.valorantmodelapi.models.ValorantMap
import com.calmwolfs.valorantmodelapi.models.Version
import com.calmwolfs.valorantmodelapi.models.Weapon
import com.calmwolfs.valorantmodelapi.utils.GsonUtils
import com.google.gson.JsonObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class ValorantModelApi{

    private val projectVersion = System.getProperty("project.version")
    private val baseUrl = "https://valorant-api.com/v1"

    fun getAgents() = sendRequestList<Agent>("agents")
    fun getBuddies() = sendRequestList<Buddy>("buddies")
    fun getCards() = sendRequestList<Card>("playercards")
    fun getContentTiers() = sendRequestList<ContentTier>("contenttiers")
    fun getCurrencies() = sendRequestList<Currency>("currencies")
    fun getGamemodes() = sendRequestList<Gamemode>("gamemodes")
    fun getGamemodeEquippable() = sendRequestList<GamemodeEquippable>("gamemodes/equippables")
    fun getMaps() = sendRequestList<ValorantMap>("maps")
    fun getSeasons() = sendRequestList<Season>("seasons")
    fun getThemes() = sendRequestList<Theme>("themes")
    fun getTitles() = sendRequestList<Title>("playertitles")
    fun getWeapons() = sendRequestList<Weapon>("weapons")
    fun getVersion() = sendRequest<Version>("version")
    fun getSprays() = sendRequestList<Spray>("sprays")


    @Throws(IOException::class)
    private inline fun <reified T> sendRequest(requestPath: String): T {
        val responseJsonObject = getRawJsonResponse(requestPath)
        return GsonUtils.gson.fromJson(responseJsonObject.getAsJsonObject("data"), T::class.java)
    }

    @Throws(IOException::class)
    private inline fun <reified T> sendRequestList(
        requestPath: String,
    ): List<T> {
        val responseJsonObject = getRawJsonResponse(requestPath)
        val data = responseJsonObject.getAsJsonArray("data")
        val result = mutableListOf<T>()
        data.forEach {
            result.add(GsonUtils.gson.fromJson(it, T::class.java))
        }
        return result
    }

    @Throws(IOException::class)
    private fun getRawJsonResponse(requestPath: String): JsonObject {
        val url = "$baseUrl/$requestPath"

        val connection = URL(url).openConnection() as HttpURLConnection
        println("connecting to $url")

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
