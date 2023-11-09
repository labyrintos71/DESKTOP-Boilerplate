package hooking

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener
import com.github.kwhat.jnativehook.mouse.NativeMouseListener

class MouseHooker : NativeMouseInputListener {
    override fun nativeMouseClicked(e: NativeMouseEvent) {
        println("Mouse Clicked: " + e.clickCount)
    }

    override fun nativeMousePressed(e: NativeMouseEvent) {
        println("Mouse Pressed: " + e.button)
    }

    override fun nativeMouseReleased(e: NativeMouseEvent) {
        println("Mouse Released: " + e.button)
    }

    override fun nativeMouseMoved(e: NativeMouseEvent) {
        println("Mouse Moved: " + e.x + ", " + e.y)
    }

    override fun nativeMouseDragged(e: NativeMouseEvent) {
        println("Mouse Dragged: " + e.x + ", " + e.y)
    }
}