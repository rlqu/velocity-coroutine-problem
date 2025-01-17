# Velocity Coroutine Problem
### Project overview
I have a library and in that project, there's one 'Test' class which contains a suspend function called 'testFunction'. This function simply takes a string to append the console output on plugin load and prints that output with the Dispatchers.IO scope:

```kt
suspend fun testFunction(plugin: String) {
    withContext(Dispatchers.IO) {
        println("Test function invoked successfully on IO thread! [Plugin: $plugin]")
    }
}
```
<br>

### Plugin (root)
In the plugin module, I have a Velocity plugin that's called 'test-velocity'. Here, I shade the coroutines library and the classes from the library-module and as soon as the ProxyInitializeEvent gets fired for that, I call the testFunction from the library with the plugin argument 'ROOT' as it's the root project, which provides some dependencies by shading it.

```kt
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
```
<br>

### Plugin (depending)
The third module, the 'depending-plugin' module, is just another Velocity plugin that contains the same dependencies as the 'test-velocity' plugin, but shades nothing. It depends on the 'test-velocity' plugin so there should be the necessary dependencies loaded as the 'depending-plugin' starts. The main class of the 'dependending-plugin' is exactly the same as the one from the 'test-velocity' plugin, but with the plugin name 'DEPENDING' as the argument for the library test function.

```kt
@Subscribe
fun onProxyInitialization(event: ProxyInitializeEvent) {
    val test = Test()

    /**
    * Won't throw an error!
    */
    CoroutineScope(Dispatchers.Default).launch {
        test.testFunction("DEPENDING")
    }

}
```

### Error
Whenever I try to start the proxy, the 'root' plugin works but the 'depending' plugin throws the following error:

```

[14:53:10 ERROR]: Exception in thread "DefaultDispatcher-worker-2" java.lang.NoSuchMethodError: 'java.lang.Object de.jannik.testlib.Test.testFunction(java.lang.String, kotlin.coroutines.Continuation)'
[14:53:10 ERROR]:      at de.jannik.depending.VelocityDependingTestPlugin$onProxyInitialization$1.invokeSuspend(VelocityDependingTestPlugin.kt:32)
[14:53:10 ERROR]:      at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
[14:53:10 ERROR]:      at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:108)
[14:53:10 ERROR]:      at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:584)
[14:53:10 ERROR]:      at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:793)
[14:53:10 ERROR]:      at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:697)
[14:53:10 ERROR]:      at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:684)
[14:53:10 ERROR]:      Suppressed: kotlinx.coroutines.internal.DiagnosticCoroutineContextException: [StandaloneCoroutine{Cancelling}@95d1e10, Dispatchers.Default]

```
