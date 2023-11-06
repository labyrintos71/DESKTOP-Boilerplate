package hooking

import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.NativeHookException
import kotlin.system.exitProcess

// implementation("com.github.kwhat:jnativehook:2.2.2")
class HookTest {
    fun run(){
        try {
            GlobalScreen.registerNativeHook()
        } catch (ex: NativeHookException) {
            System.err.println("There was a problem registering the native hook.")
            System.err.println(ex.message)
            exitProcess(1)
        }
        GlobalScreen.addNativeMouseListener(MouseHooker())
        GlobalScreen.addNativeKeyListener(KeyHooker())
    }
}