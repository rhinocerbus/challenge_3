package com.example.challenge.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL

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

    @UnstableDefault
    private fun fetchArmorDataImpl(): List<ArmorPiece> {
        val rawContent = URL("https://mhw-db.com/armor").readText()
        // lenient for ex: damageDragon="100+5%"
        val config = JsonConfiguration(isLenient = true)
        return Json(config).parse(ArmorPiece.serializer().list, rawContent)
    }
}