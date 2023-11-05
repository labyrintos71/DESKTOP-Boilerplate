package winapi

import com.sun.jna.Native
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.User32
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.platform.win32.WinUser

class WIN32Handle {

    fun getHandles() {
        User32.INSTANCE.EnumWindows(object : WinUser.WNDENUMPROC {
            var count = 0
            override fun callback(hWnd: WinDef.HWND?, arg1: Pointer?): Boolean {
                val windowText = CharArray(1024)
                User32.INSTANCE.GetWindowText(hWnd, windowText, 1024)
                val wText = Native.toString(windowText)
                val rectangle = WinDef.RECT()
                User32.INSTANCE.GetWindowRect(hWnd, rectangle)
                if (wText.isEmpty() || !(User32.INSTANCE.IsWindowVisible(hWnd) && rectangle.left > -32000)) {
                    return true
                }
                val clsName = getClassNameFromHandle(hWnd)
                if (clsName.isNotEmpty()) {
                    var str = "";
                    str = "index : ${++count}, 클래스네임 : $clsName, 텍스트 : $wText"+'\n'+"위치 : ${rectangle.left},${rectangle.top},${rectangle.right},${rectangle.bottom},"

                    println(str)

                }
                return true
            }
        }, null)
    }

    fun getClassNameFromHandle(hWnd: WinDef.HWND?): String {
        if (hWnd == null) {
            return ""
        }
        // 핸들의 클래스 네임 얻기
        val c = CharArray(512)
        User32.INSTANCE.GetClassName(hWnd, c, 512)
        return String(c).trim { it <= ' ' }
    }
}