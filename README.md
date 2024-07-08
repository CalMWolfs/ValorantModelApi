# ValorantModelApi

Kotlin API Wrapper for https://valorant-api.com

It is free to use and open source.

## Installation

Add this to your dependencies in your build.gradle.kts file:

```kts
repositories {
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("com.github.CalMWolfs:ValorantModelApi:1.2.1")
}
```

```maven
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
	    <groupId>com.github.CalMWolfs</groupId>
	    <artifactId>ValorantModelApi</artifactId>
	    <version>1.2.1</version>
	</dependency>
```

## Introduction

The api requests are not threaded, so you will need to set that up yourself.

All results are cached, if you want to force update data, add true as the first argument

If you want to fully clear the cache, use `ValorantModelApi.clearCache()`

```kt
// not force updated
val agents = ValorantModelApi.getAgents()

// force updated
val agents = ValorantModelApi.getAgents(true)
```

You can also use enum values to get the data for one specific thing. 

The enums will be updated periodically but as fallback if you know the id you can search by that.

### Example: Getting all agent names

Kotlin
```kotlin
fun main(args: Array<String>) {
    val agents = ValorantModelApi.getAgents().map { it.displayName }
    println(agents)
}
```

### Example: Getting just the Split Map

Kotlin
```kotlin
fun main(args: Array<String>) {
    val split = ValorantModelApi.getMap(MapType.SPLIT)
    println(split)
}
```