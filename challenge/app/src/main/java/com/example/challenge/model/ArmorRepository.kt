package com.example.challenge.model

import kotlinx.coroutines.*
import kotlinx.serialization.ImplicitReflectionSerializer
import java.net.URL
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.parseList

@ImplicitReflectionSerializer
object ArmorRepository {
    suspend fun fetchArmorData(scope: CoroutineScope): List<ArmorPiece> {
        return try {
            val armorData = scope.async(Dispatchers.Default) {
                fetchArmorDataImpl()
            }

            armorData.await()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun fetchArmorDataImpl(): List<ArmorPiece> {
        val rawContent = URL("https://mhw-db.com/armor").readText()
        // lenient for ex: damageDragon="100+5%"
        return Json(JsonConfiguration(isLenient = true)).parseList<ArmorPiece>(rawContent)
    }
}