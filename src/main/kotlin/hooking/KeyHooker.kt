package hooking

import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.NativeHookException
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener


class KeyHooker : NativeKeyListener {
    override fun nativeKeyPressed(e: NativeKeyEvent) {
        println("Key Pressed: " + NativeKeyEvent.getKeyText(e.keyCode))

        if (e.keyCode === NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook()
            } catch (nativeHookException: NativeHookException) {
                nativeHookException.printStackTrace()
            }
        }
    }

    override fun nativeKeyReleased(e: NativeKeyEvent) {
        println("Key Released: " + NativeKeyEvent.getKeyText(e.keyCode))
    }

    override fun nativeKeyTyped(e: NativeKeyEvent) {
        println("Key Typed: " + NativeKeyEvent.getKeyText(e.keyCode))
    }
}
