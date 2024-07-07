package com.calmwolfs.valorantmodelapi.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Modifier

object GsonUtils {

    val gson: Gson by lazy {
        GsonBuilder().setPrettyPrinting()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC, Modifier.VOLATILE)
            .create()
    }
}