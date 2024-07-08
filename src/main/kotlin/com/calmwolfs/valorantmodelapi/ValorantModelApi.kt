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
import com.calmwolfs.valorantmodelapi.enums.PlayerTitleType
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

    private const val PROJECT_VERSION = "1.2.3"
    private const val BASE_URL = "https://valorant-api.com/v1"

    private val requestCache = mutableMapOf<String, Any>()

    fun logUnknownId(id: String?, type: Any) {
        id ?: return
        println("[ValorantModelApi-$PROJECT_VERSION] Unknown ${type.javaClass.simpleName}, id: $id")
    }

    // todo allow a way to request just one thing without requesting all of that type

    fun getAgents(force: Boolean = false) = sendRequestList<Agent>("agents?isPlayableCharacter=true", force)
    fun getAgent(agent: AgentType, force: Boolean = false) = getAgentById(agent.uuid, force)
    fun getAgentById(uuid: String, force: Boolean = false) = getAgents(force).find { it.uuid == uuid }

    fun getBuddies(force: Boolean = false) = sendRequestList<Buddy>("buddies", force)
    fun getBuddy(buddyType: BuddyType, force: Boolean = false) = getBuddyById(buddyType.uuid, force)
    fun getBuddyById(uuid: String, force: Boolean = false) = getBuddies(force).find { it.uuid == uuid }

    fun getStoreBundles(force: Boolean = false) = sendRequestList<StoreBundle>("bundles", force)
    fun getStoreBundle(storeBundle: StoreBundleType, force: Boolean = false) =
        getStoreBundleById(storeBundle.uuid, force)

    fun getStoreBundleById(uuid: String, force: Boolean = false) = getStoreBundles(force).find { it.uuid == uuid }

    fun getCeremonies(force: Boolean = false) = sendRequestList<Ceremony>("ceremonies", force)
    fun getCeremony(ceremony: CeremonyType, force: Boolean = false) = getCeremonyById(ceremony.uuid, force)
    fun getCeremonyById(uuid: String, force: Boolean = false) = getCeremonies(force).find { it.uuid == uuid }

    fun getCompetitiveRanks(force: Boolean = false) =
        sendRequestList<CompetitiveTier>("competitivetiers", force).last().tiers

    fun getCompetitiveRank(rank: CompetitiveRankType, force: Boolean = false) =
        getCompetitiveRanks(force).find { it.tier == rank.tier }

    fun getCompetitiveRankByTier(tier: Int, force: Boolean = false) =
        getCompetitiveRanks(force).find { it.tier == tier }

    fun getContentTiers(force: Boolean = false) = sendRequestList<ContentTier>("contenttiers", force)
    fun getContentTier(contentTier: ContentTierType, force: Boolean = false) =
        getContentTierById(contentTier.uuid, force)

    fun getContentTierById(uuid: String, force: Boolean = false) = getContentTiers(force).find { it.uuid == uuid }

    fun getCurrencies(force: Boolean = false) = sendRequestList<Currency>("currencies", force)
    fun getCurrency(currency: CurrencyType, force: Boolean = false) = getCurrencyById(currency.uuid, force)
    fun getCurrencyById(uuid: String, force: Boolean = false) = getCurrencies(force).find { it.uuid == uuid }

    fun getGamemodeEquippables(force: Boolean = false) =
        sendRequestList<GamemodeEquippable>("gamemodes/equippables", force)

    fun getGamemodeEquippable(gamemodeEquippable: GamemodeEquippableType, force: Boolean = false) =
        getGamemodeEquippableById(gamemodeEquippable.uuid, force)

    fun getGamemodeEquippableById(uuid: String, force: Boolean = false) =
        getGamemodeEquippables(force).find { it.uuid == uuid }

    fun getGamemodes(force: Boolean = false) = sendRequestList<Gamemode>("gamemodes", force)
    fun getGamemode(gamemode: GamemodeType, force: Boolean = false) = getGamemodeById(gamemode.uuid, force)
    fun getGamemodeById(uuid: String, force: Boolean = false) = getGamemodes(force).find { it.uuid == uuid }

    fun getGears(force: Boolean = false) = sendRequestList<Gear>("gear", force)
    fun getGear(gear: GearType, force: Boolean = false) = getGearById(gear.uuid, force)
    fun getGearById(uuid: String, force: Boolean = false) = getGears(force).find { it.uuid == uuid }

    fun getLevelBorders(force: Boolean = false) = sendRequestList<LevelBorder>("levelborders", force)
    fun getLevelBorder(levelBorder: LevelBorderType, force: Boolean = false) =
        getLevelBorderById(levelBorder.uuid, force)

    fun getLevelBorderById(uuid: String, force: Boolean = false) = getLevelBorders(force).find { it.uuid == uuid }

    fun getMaps(force: Boolean = false) = sendRequestList<ValorantMap>("maps", force)
    fun getMap(map: MapType, force: Boolean = false) = getMapById(map.uuid, force)
    fun getMapById(uuid: String, force: Boolean = false) = getMaps(force).find { it.uuid == uuid }

    fun getPlayerCards(force: Boolean = false) = sendRequestList<PlayerCard>("playercards", force)
    fun getPlayerCard(playerCard: PlayerCardType, force: Boolean = false) = getPlayerCardById(playerCard.uuid, force)
    fun getPlayerCardById(uuid: String, force: Boolean = false) = getPlayerCards(force).find { it.uuid == uuid }

    fun getPlayerTitles(force: Boolean = false) = sendRequestList<PlayerTitle>("playertitles", force)
    fun getPlayerTitle(playerTitle: PlayerTitleType, force: Boolean = false) =
        getPlayerTitleById(playerTitle.uuid, force)

    fun getPlayerTitleById(uuid: String, force: Boolean = false) = getPlayerTitles(force).find { it.uuid == uuid }

    fun getSeasons(force: Boolean = false) = sendRequestList<Season>("seasons", force)
    fun getSeason(season: SeasonType, force: Boolean = false) = getSeasonById(season.uuid, force)
    fun getSeasonById(uuid: String, force: Boolean = false) = getSeasons(force).find { it.uuid == uuid }

    fun getSprays(force: Boolean = false) = sendRequestList<Spray>("sprays", force)
    fun getSpray(spray: SprayType, force: Boolean = false) = getSprayById(spray.uuid, force)
    fun getSprayById(uuid: String, force: Boolean = false) = getSprays(force).find { it.uuid == uuid }

    fun getThemes(force: Boolean = false) = sendRequestList<Theme>("themes", force)
    fun getTheme(theme: ThemeType, force: Boolean = false) = getThemeById(theme.uuid, force)
    fun getThemeById(uuid: String, force: Boolean = false) = getThemes(force).find { it.uuid == uuid }

    fun getVersion(force: Boolean = false) = sendRequest<Version>("version", force)

    fun getWeapons(force: Boolean = false) = sendRequestList<Weapon>("weapons", force)
    fun getWeapon(weapon: WeaponType, force: Boolean = false) = getWeaponById(weapon.uuid, force)
    fun getWeaponById(uuid: String, force: Boolean = false) = getWeapons(force).find { it.uuid == uuid }

    // todo add events
    // todo add contracts

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
        val url = "$BASE_URL/${requestPath.replace(" ", "%20")}"
        val connection = URL(url).openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("User-Agent", "ValorantModelApi/$PROJECT_VERSION")

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
