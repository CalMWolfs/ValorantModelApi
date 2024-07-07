package com.calmwolfs.valorantmodelapi.models

data class Weapon(
    val uuid: String,
    val displayName: String,
    val category: String,
    val defaultSkinUuid: String,
    val killStreamIcon: String,
    val assetPath: String,
    val weaponStats: WeaponStats,
    val shopData: WeaponShopData,
    val skins: List<WeaponSkin>
)

data class WeaponStats(
    val fireRate: Float,
    val magazineSize: Int,
    val runSpeedMultiplier: Float,
    val equipTimeSeconds: Float,
    val reloadTimeSeconds: Float,
    val firstBulletAccuracy: Float,
    val shotgunPelletCount: Int,
    val wallPenetration: String,
    val feature: String,
    val fireMode: String,
    val altFireType: String,
    val adsStats: WeaponAdsStats,
    val altShotgunStats: WeaponsShotgunStats,
    val airBurstStats: WeaponsBurstStats?,
    val damageRanges: List<WeaponDamageRange>
)

data class WeaponsShotgunStats(
    val shotgunPelletCount: Int,
    val burstRate: Float
)

data class WeaponsBurstStats(
    val shotgunPelletCount: Int,
    val burstsDistance: Float
)

data class WeaponShopData(
    val cost: Int,
    val category: String,
    val categoryText: String,
    val gridPosition: WeaponShopDataGridPosition,
    val image: String,
    val newImage: String,
    val newImage2: String,
    val assetPath: String
)

data class WeaponDamageRange(
    val rangeStartsMeters: Float,
    val rangeEndMeters: Float,
    val headDamage: Float,
    val bodyDamage: Float,
    val legDamage: Float
)

data class WeaponAdsStats(
    val zoomMultiplier: Float,
    val fireRate: Float,
    val runSpeedMultiplier: Float,
    val burstCount: Int,
    val firstBulletAccuracy: Float
)

data class WeaponShopDataGridPosition(
    val row: Int,
    val column: Int
)

data class WeaponSkin(
    val uuid: String,
    val displayName: String,
    val themeUuid: String,
    val contentTierUuid: String,
    val displayIcon: String,
    val assetPath: String,
    val chromas: List<WeaponSkinChroma>,
    val levels: List<WeaponSkinLevel>
)

data class WeaponSkinChroma(
    val uuid: String,
    val displayName: String,
    val displayIcon: String,
    val fullRender: String,
    val swatch: Any,
    val assetPath: String
)

data class WeaponSkinLevel(
    val uuid: String,
    val displayName: String,
    val levelItem: Any,
    val displayIcon: String,
    val assetPath: String
)