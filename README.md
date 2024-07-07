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
    implementation("com.github.CalMWolfs:ValorantModelApi:1.0.2")
}
```

## Introduction

The api requests are not threaded, so you will need to set that up yourself.
