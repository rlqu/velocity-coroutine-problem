package de.jannik.testlib

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Test {

    suspend fun testFunction(plugin: String) {

        withContext(Dispatchers.IO) {
            println("Test function invoked successfully on IO thread! [Plugin: $plugin]")
        }

    }

}