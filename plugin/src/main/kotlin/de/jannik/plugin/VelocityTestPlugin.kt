package de.jannik.plugin

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import de.jannik.testlib.Test

@Plugin(
    id = "test-velocity",
    name = "Test Velocity Implementation",
    version = "RELEASE-0.1",
    description = "Test plugin with shading Coroutines and library-project",
    authors = ["rlqu"]
)
class VelocityTestPlugin @Inject constructor(val server: ProxyServer) {

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        val test = Test()

        /**
         * Won't throw an error!
         */
        CoroutineScope(Dispatchers.Default).launch {
            test.testFunction("ROOT")
        }

    }
}