package hooking

import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.NativeHookException

class HookTest {
    fun run(){

        try {
            GlobalScreen.registerNativeHook()
        } catch (ex: NativeHookException) {
            System.err.println("There was a problem registering the native hook.")
            System.err.println(ex.message)
            System.exit(1)
        }
        GlobalScreen.addNativeMouseListener(MouseHooker())
        GlobalScreen.addNativeKeyListener(KeyHooker())
    }
}