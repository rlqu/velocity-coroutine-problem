package de.jannik.depending

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Dependency
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import de.jannik.testlib.Test

@Plugin(
    id = "test-depending-velocity",
    name = "Test Depending Velocity Implementation",
    version = "RELEASE-0.1",
    description = "Test plugin without shading but depending on the other plugin which shades its dependencies",
    authors = ["rlqu"],
    dependencies = [Dependency(id = "test-velocity")]
)
class VelocityDependingTestPlugin @Inject constructor(val server: ProxyServer) {

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        val test = Test()

        /**
         * Will throw an error!
         */
        CoroutineScope(Dispatchers.Default).launch {
            test.testFunction("DEPENDING")
        }

    }
}