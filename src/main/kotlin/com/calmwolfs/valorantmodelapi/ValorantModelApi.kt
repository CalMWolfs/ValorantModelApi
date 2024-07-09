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
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import java.io.IOException
import kotlin.collections.set

object ValorantModelApi {

    private const val PROJECT_VERSION = "1.2.3"
    private const val BASE_URL = "https://valorant-api.com/v1"

    private val client = HttpClient(OkHttp)

    private val requestCache = mutableMapOf<String, Any>()

    fun logUnknownId(id: String?, type: Any) {
        id ?: return
        println("[ValorantModelApi-$PROJECT_VERSION] Unknown ${type.javaClass.simpleName}, id: $id")
    }

    fun closeClient() {
        client.close()
    }

    // todo allow a way to request just one thing without requesting all of that type

    @JvmStatic
    @JvmOverloads
    fun getAgents(force: Boolean = false) = sendRequestList<Agent>("agents?isPlayableCharacter=true", force)

    @JvmStatic
    @JvmOverloads
    fun getAgent(agent: AgentType, force: Boolean = false) = getAgentById(agent.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getAgentById(uuid: String, force: Boolean = false) = getAgents(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getBuddies(force: Boolean = false) = sendRequestList<Buddy>("buddies", force)

    @JvmStatic
    @JvmOverloads
    fun getBuddy(buddyType: BuddyType, force: Boolean = false) = getBuddyById(buddyType.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getBuddyById(uuid: String, force: Boolean = false) = getBuddies(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getStoreBundles(force: Boolean = false) = sendRequestList<StoreBundle>("bundles", force)

    @JvmStatic
    @JvmOverloads
    fun getStoreBundle(storeBundle: StoreBundleType, force: Boolean = false) =
        getStoreBundleById(storeBundle.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getStoreBundleById(uuid: String, force: Boolean = false) = getStoreBundles(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getCeremonies(force: Boolean = false) = sendRequestList<Ceremony>("ceremonies", force)

    @JvmStatic
    @JvmOverloads
    fun getCeremony(ceremony: CeremonyType, force: Boolean = false) = getCeremonyById(ceremony.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getCeremonyById(uuid: String, force: Boolean = false) = getCeremonies(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getCompetitiveRanks(force: Boolean = false) =
        sendRequestList<CompetitiveTier>("competitivetiers", force).last().tiers

    @JvmStatic
    @JvmOverloads
    fun getCompetitiveRank(rank: CompetitiveRankType, force: Boolean = false) =
        getCompetitiveRanks(force).find { it.tier == rank.tier }

    @JvmStatic
    @JvmOverloads
    fun getCompetitiveRankByTier(tier: Int, force: Boolean = false) =
        getCompetitiveRanks(force).find { it.tier == tier }

    @JvmStatic
    @JvmOverloads
    fun getContentTiers(force: Boolean = false) = sendRequestList<ContentTier>("contenttiers", force)

    @JvmStatic
    @JvmOverloads
    fun getContentTier(contentTier: ContentTierType, force: Boolean = false) =
        getContentTierById(contentTier.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getContentTierById(uuid: String, force: Boolean = false) = getContentTiers(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getCurrencies(force: Boolean = false) = sendRequestList<Currency>("currencies", force)

    @JvmStatic
    @JvmOverloads
    fun getCurrency(currency: CurrencyType, force: Boolean = false) = getCurrencyById(currency.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getCurrencyById(uuid: String, force: Boolean = false) = getCurrencies(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getGamemodeEquippables(force: Boolean = false) =
        sendRequestList<GamemodeEquippable>("gamemodes/equippables", force)

    @JvmStatic
    @JvmOverloads
    fun getGamemodeEquippable(gamemodeEquippable: GamemodeEquippableType, force: Boolean = false) =
        getGamemodeEquippableById(gamemodeEquippable.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getGamemodeEquippableById(uuid: String, force: Boolean = false) =
        getGamemodeEquippables(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getGamemodes(force: Boolean = false) = sendRequestList<Gamemode>("gamemodes", force)

    @JvmStatic
    @JvmOverloads
    fun getGamemode(gamemode: GamemodeType, force: Boolean = false) = getGamemodeById(gamemode.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getGamemodeById(uuid: String, force: Boolean = false) = getGamemodes(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getGears(force: Boolean = false) = sendRequestList<Gear>("gear", force)

    @JvmStatic
    @JvmOverloads
    fun getGear(gear: GearType, force: Boolean = false) = getGearById(gear.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getGearById(uuid: String, force: Boolean = false) = getGears(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getLevelBorders(force: Boolean = false) = sendRequestList<LevelBorder>("levelborders", force)

    @JvmStatic
    @JvmOverloads
    fun getLevelBorder(levelBorder: LevelBorderType, force: Boolean = false) =
        getLevelBorderById(levelBorder.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getLevelBorderById(uuid: String, force: Boolean = false) = getLevelBorders(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getMaps(force: Boolean = false) = sendRequestList<ValorantMap>("maps", force)

    @JvmStatic
    @JvmOverloads
    fun getMap(map: MapType, force: Boolean = false) = getMapById(map.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getMapById(uuid: String, force: Boolean = false) = getMaps(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getPlayerCards(force: Boolean = false) = sendRequestList<PlayerCard>("playercards", force)

    @JvmStatic
    @JvmOverloads
    fun getPlayerCard(playerCard: PlayerCardType, force: Boolean = false) = getPlayerCardById(playerCard.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getPlayerCardById(uuid: String, force: Boolean = false) = getPlayerCards(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getPlayerTitles(force: Boolean = false) = sendRequestList<PlayerTitle>("playertitles", force)

    @JvmStatic
    @JvmOverloads
    fun getPlayerTitle(playerTitle: PlayerTitleType, force: Boolean = false) =
        getPlayerTitleById(playerTitle.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getPlayerTitleById(uuid: String, force: Boolean = false) = getPlayerTitles(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getSeasons(force: Boolean = false) = sendRequestList<Season>("seasons", force)

    @JvmStatic
    @JvmOverloads
    fun getSeason(season: SeasonType, force: Boolean = false) = getSeasonById(season.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getSeasonById(uuid: String, force: Boolean = false) = getSeasons(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getSprays(force: Boolean = false) = sendRequestList<Spray>("sprays", force)

    @JvmStatic
    @JvmOverloads
    fun getSpray(spray: SprayType, force: Boolean = false) = getSprayById(spray.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getSprayById(uuid: String, force: Boolean = false) = getSprays(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getThemes(force: Boolean = false) = sendRequestList<Theme>("themes", force)

    @JvmStatic
    @JvmOverloads
    fun getTheme(theme: ThemeType, force: Boolean = false) = getThemeById(theme.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getThemeById(uuid: String, force: Boolean = false) = getThemes(force).find { it.uuid == uuid }

    @JvmStatic
    @JvmOverloads
    fun getVersion(force: Boolean = false) = sendRequest<Version>("version", force)

    @JvmStatic
    @JvmOverloads
    fun getWeapons(force: Boolean = false) = sendRequestList<Weapon>("weapons", force)

    @JvmStatic
    @JvmOverloads
    fun getWeapon(weapon: WeaponType, force: Boolean = false) = getWeaponById(weapon.uuid, force)

    @JvmStatic
    @JvmOverloads
    fun getWeaponById(uuid: String, force: Boolean = false) = getWeapons(force).find { it.uuid == uuid }

    // todo add events
    // todo add contracts

    @JvmStatic
    fun clearCache() {
        requestCache.clear()
    }

    @Throws(IOException::class)
    private inline fun <reified T : Any> sendRequest(requestPath: String, force: Boolean): T = runBlocking {
        if (!force && requestCache.containsKey(requestPath)) {
            return@runBlocking requestCache[requestPath] as T
        }
        val responseJsonObject = getRawJsonResponse(requestPath)
        val result = GsonUtils.gson.fromJson(responseJsonObject.getAsJsonObject("data"), T::class.java)
        requestCache[requestPath] = result
        return@runBlocking result
    }

    @Throws(IOException::class)
    @Suppress("UNCHECKED_CAST")
    private inline fun <reified T : Any> sendRequestList(requestPath: String, force: Boolean): List<T> = runBlocking {
        if (!force && requestCache.containsKey(requestPath)) {
            return@runBlocking requestCache[requestPath] as List<T>
        }
        val responseJsonObject = getRawJsonResponse(requestPath)
        val data = responseJsonObject.getAsJsonArray("data")
        val result = mutableListOf<T>()
        data.forEach {
            result.add(GsonUtils.gson.fromJson(it, T::class.java))
        }
        requestCache[requestPath] = result
        return@runBlocking result
    }

    @Throws(IOException::class)
    private suspend fun getRawJsonResponse(requestPath: String): JsonObject {
        val url = "$BASE_URL/${requestPath.replace(" ", "%20")}"

        val response = client.request(url) {
            method = HttpMethod.Get
            header("User-Agent", "ValorantModelApi/$PROJECT_VERSION")
        }

        if (response.status != HttpStatusCode.OK) {
            throw IOException("Unknown error response code: ${response.status.value}")
        }

        val responseBody = response.bodyAsText()
        return GsonUtils.gson.fromJson(responseBody, JsonObject::class.java)
    }
}
