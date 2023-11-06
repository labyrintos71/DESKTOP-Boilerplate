package winapi

import com.sun.jna.platform.win32.User32
import com.sun.jna.platform.win32.WinDef

class WIN32Mouse {
    fun printCursor() {
        val p = WinDef.POINT()
        User32.INSTANCE.GetCursorPos(p)
        println("${p.x},${p.y}")
    }
    fun setCursor(x: Long,y: Long) {
        User32.INSTANCE.SetCursorPos(x, y)
    }
}