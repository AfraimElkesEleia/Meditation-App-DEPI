package com.example.meditationapp

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {

    val ListType = object : NavType<List<Int>>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): List<Int>? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): List<Int> {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<Int>): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: List<Int>) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}