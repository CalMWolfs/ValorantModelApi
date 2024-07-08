package com.calmwolfs.valorantmodelapi

import com.calmwolfs.valorantmodelapi.enums.AgentType
import com.calmwolfs.valorantmodelapi.enums.BuddyType
import com.calmwolfs.valorantmodelapi.enums.CeremonyType
import com.calmwolfs.valorantmodelapi.enums.CompetitiveRankType
import com.calmwolfs.valorantmodelapi.enums.ContentTierType
import com.calmwolfs.valorantmodelapi.enums.CurrencyType
import com.calmwolfs.valorantmodelapi.enums.GamemodeEquippableType
import com.calmwolfs.valorantmodelapi.enums.GamemodeType
import com.calmwolfs.valorantmodelapi.enums.GearType
import com.calmwolfs.valorantmodelapi.enums.LevelBorderType
import com.calmwolfs.valorantmodelapi.enums.MapType
import com.calmwolfs.valorantmodelapi.enums.PlayerCardType
import com.calmwolfs.valorantmodelapi.enums.SeasonType
import com.calmwolfs.valorantmodelapi.enums.SprayType
import com.calmwolfs.valorantmodelapi.enums.StoreBundleType
import com.calmwolfs.valorantmodelapi.enums.ThemeType
import com.calmwolfs.valorantmodelapi.enums.WeaponType
import com.calmwolfs.valorantmodelapi.models.Agent
import com.calmwolfs.valorantmodelapi.models.Buddy
import com.calmwolfs.valorantmodelapi.models.Ceremony
import com.calmwolfs.valorantmodelapi.models.CompetitiveTier
import com.calmwolfs.valorantmodelapi.models.ContentTier
import com.calmwolfs.valorantmodelapi.models.Currency
import com.calmwolfs.valorantmodelapi.models.Gamemode
import com.calmwolfs.valorantmodelapi.models.GamemodeEquippable
import com.calmwolfs.valorantmodelapi.models.Gear
import com.calmwolfs.valorantmodelapi.models.LevelBorder
import com.calmwolfs.valorantmodelapi.models.PlayerCard
import com.calmwolfs.valorantmodelapi.models.PlayerTitle
import com.calmwolfs.valorantmodelapi.models.Season
import com.calmwolfs.valorantmodelapi.models.Spray
import com.calmwolfs.valorantmodelapi.models.StoreBundle
import com.calmwolfs.valorantmodelapi.models.Theme
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

    fun getAgents(force: Boolean = false) = sendRequestList<Agent>("agents?isPlayableCharacter=true", force)
    fun getAgent(agent: AgentType, force: Boolean = false) = sendRequest<Agent>("agents/${agent.uuid}", force)
    fun getAgentById(uuid: String, force: Boolean = false) = sendRequest<Agent>("agents/$uuid", force)

    fun getBuddies(force: Boolean = false) = sendRequestList<Buddy>("buddies", force)
    fun getBuddy(buddyType: BuddyType, force: Boolean = false) = sendRequest<Buddy>("buddies/${buddyType.uuid}", force)
    fun getBuddyById(uuid: String, force: Boolean = false) = sendRequest<Buddy>("buddies/$uuid", force)

    fun getStoreBundles(force: Boolean = false) = sendRequestList<StoreBundle>("bundles", force)
    fun getStoreBundle(storeBundle: StoreBundleType, force: Boolean = false) =
        sendRequest<StoreBundle>("bundles/${storeBundle.uuid}", force)

    fun getStoreBundleById(uuid: String, force: Boolean = false) = sendRequest<StoreBundle>("bundles/$uuid", force)

    fun getCeremonies(force: Boolean = false) = sendRequestList<Ceremony>("ceremonies", force)
    fun getCeremony(ceremony: CeremonyType, force: Boolean = false) =
        sendRequest<Ceremony>("ceremonies/${ceremony.uuid}", force)

    fun getCeremonyById(uuid: String, force: Boolean = false) = sendRequest<Ceremony>("ceremonies/$uuid", force)

    fun getCompetitiveRanks(force: Boolean = false) =
        sendRequestList<CompetitiveTier>("competitivetiers", force).last().tiers

    fun getCompetitiveRank(rank: CompetitiveRankType, force: Boolean = false) =
        getCompetitiveRanks().find { it.tier == rank.tier }

    fun getCompetitiveRankByTier(tier: Int, force: Boolean = false) = getCompetitiveRanks().find { it.tier == tier }

    fun getContentTiers(force: Boolean = false) = sendRequestList<ContentTier>("contenttiers", force)
    fun getContentTier(contentTier: ContentTierType, force: Boolean = false) =
        sendRequest<ContentTier>("contenttiers/${contentTier.uuid}", force)

    fun getContentTierById(uuid: String, force: Boolean = false) = sendRequest<ContentTier>("contenttiers/$uuid", force)

    fun getCurrencies(force: Boolean = false) = sendRequestList<Currency>("currencies", force)
    fun getCurrency(currency: CurrencyType, force: Boolean = false) =
        sendRequest<Currency>("currencies/${currency.uuid}", force)

    fun getCurrencyById(uuid: String, force: Boolean = false) = sendRequest<Currency>("currencies/$uuid", force)

    fun getGamemodeEquippable(force: Boolean = false) =
        sendRequestList<GamemodeEquippable>("gamemodes/equippables", force)

    fun getGamemodeEquippable(gamemodeEquippable: GamemodeEquippableType, force: Boolean = false) =
        sendRequest<GamemodeEquippable>("gamemodes/equippables/${gamemodeEquippable.uuid}", force)

    fun getGamemodeEquippableById(uuid: String, force: Boolean = false) =
        sendRequest<GamemodeEquippable>("gamemodes/equippables/$uuid", force)

    fun getGamemodes(force: Boolean = false) = sendRequestList<Gamemode>("gamemodes", force)
    fun getGamemode(gamemode: GamemodeType, force: Boolean = false) =
        sendRequest<Gamemode>("gamemodes/${gamemode.uuid}", force)

    fun getGamemodeById(uuid: String, force: Boolean = false) = sendRequest<Gamemode>("gamemodes/$uuid", force)

    fun getGear(force: Boolean = false) = sendRequestList<Gear>("gear", force)
    fun getGear(gear: GearType, force: Boolean = false) = sendRequest<Gear>("gear/${gear.uuid}", force)
    fun getGearById(uuid: String, force: Boolean = false) = sendRequest<Gear>("gear/$uuid", force)

    fun getLevelBorders(force: Boolean = false) = sendRequestList<LevelBorder>("levelborders", force)
    fun getLevelBorder(levelBorder: LevelBorderType, force: Boolean = false) =
        sendRequest<LevelBorder>("levelborders/${levelBorder.uuid}", force)

    fun getLevelBorderById(uuid: String, force: Boolean = false) = sendRequest<LevelBorder>("levelborders/$uuid", force)

    fun getMaps(force: Boolean = false) = sendRequestList<ValorantMap>("maps", force)
    fun getMap(map: MapType, force: Boolean = false) = sendRequest<ValorantMap>("maps/${map.uuid}", force)
    fun getMapById(uuid: String, force: Boolean = false) = sendRequest<ValorantMap>("maps/$uuid", force)

    fun getPlayerCards(force: Boolean = false) = sendRequestList<PlayerCard>("playercards", force)
    fun getPlayerCard(playerCard: PlayerCardType, force: Boolean = false) =
        sendRequest<PlayerCard>("playercards/${playerCard.uuid}", force)

    fun getPlayerCardById(uuid: String, force: Boolean = false) = sendRequest<PlayerCard>("playercards/$uuid", force)

    fun getPlayerTitles(force: Boolean = false) = sendRequestList<PlayerTitle>("playertitles", force)
    fun getPlayerTitle(playerTitle: PlayerCardType, force: Boolean = false) =
        sendRequest<PlayerTitle>("playertitles/${playerTitle.uuid}", force)

    fun getPlayerTitleById(uuid: String, force: Boolean = false) = sendRequest<PlayerTitle>("playertitles/$uuid", force)

    fun getSeasons(force: Boolean = false) = sendRequestList<Season>("seasons", force)
    fun getSeason(season: SeasonType, force: Boolean = false) = sendRequest<Season>("seasons/${season.uuid}", force)
    fun getSeasonById(uuid: String, force: Boolean = false) = sendRequest<Season>("seasons/$uuid", force)

    fun getSprays(force: Boolean = false) = sendRequestList<Spray>("sprays", force)
    fun getSpray(spray: SprayType, force: Boolean = false) = sendRequest<Spray>("sprays/${spray.uuid}", force)
    fun getSprayById(uuid: String, force: Boolean = false) = sendRequest<Spray>("sprays/$uuid", force)

    fun getThemes(force: Boolean = false) = sendRequestList<Theme>("themes", force)
    fun getTheme(theme: ThemeType, force: Boolean = false) = sendRequest<Theme>("themes/${theme.uuid}", force)
    fun getThemeById(uuid: String, force: Boolean = false) = sendRequest<Theme>("themes/$uuid", force)

    fun getVersion(force: Boolean = false) = sendRequest<Version>("version", force)

    fun getWeapons(force: Boolean = false) = sendRequestList<Weapon>("weapons", force)
    fun getWeapon(weapon: WeaponType, force: Boolean = false) = sendRequest<Weapon>("weapons/${weapon.uuid}", force)
    fun getWeaponById(uuid: String, force: Boolean = false) = sendRequest<Weapon>("weapons/$uuid", force)

    // todo add events
    // todo add contracts

    fun clearCache() {
        requestCache.clear()
    }

    fun clearCache(path: String) {
        requestCache.remove(path)
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
