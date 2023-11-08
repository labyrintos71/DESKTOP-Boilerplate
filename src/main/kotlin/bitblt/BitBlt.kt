package bitblt

import com.sun.jna.Native
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.GDI32
import com.sun.jna.platform.win32.User32
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.platform.win32.WinUser

class BitBlt {
    init {
        try {
            val map = getHWNDMap()

            // 그림판 핸들 윈도우(HWND) 가져오기 (미리 그림판을 열어둬야 함)
            val paintHWND = map["MSPaintApp"] ?: throw Exception("그림판을 켠 후 다시 시도해주세요.")

            // 메모장 핸들 윈도우(HWND) 가져오기 (미리 메모장을 열어둬야 함)
            val notepadHWND = map["Notepad"] ?: throw Exception("메모장을 켠 후 다시 시도해주세요.")
            val paintHDC = User32.INSTANCE.GetDC(paintHWND)
            val notepadHDC = User32.INSTANCE.GetDC(notepadHWND)

            // 그림판 300, 300 위치에 메모장의 0~200, 0~100 영역을 고속복사
            GDI32.INSTANCE.BitBlt(paintHDC, 300, 300, 200, 100, notepadHDC, 0, 0, WinGDIConf.SRCCOPY)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getHWNDMap(): HashMap<String, WinDef.HWND?> {
        /**
         * 윈도우 클래스 네임 문자열(String)을 put 하면 윈도우 핸들 윈도우(HWND)를 리턴하는 HashMap.
         * HashMap에는 첫번째 발견된 핸들 윈도우(HWND)만 저장하며, 클래스 네임이 중복될 경우 무시한다.
         * @return
         */
        val hwndMap = HashMap<String, WinDef.HWND?>()
        User32.INSTANCE.EnumWindows(object : WinUser.WNDENUMPROC {
            var count = 0
            override fun callback(hWnd: WinDef.HWND, arg1: Pointer): Boolean {
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
                    val buff = StringBuffer()
                    buff.append("번호:" + ++count)
                    buff.append(",텍스트:$wText")
                    buff.append("," + "위치:(")
                    buff.append(rectangle.left.toString() + "," + rectangle.top + ")~(")
                    buff.append(rectangle.right.toString() + "," + rectangle.bottom)
                    buff.append("),클래스네임:$clsName")
                    println(buff.toString())

                    // HashMap에는 첫번째 발견된 핸들 윈도우(HWND)만 저장하며, 클래스 네임이 중복될 경우 무시한다.
                    if (hwndMap[clsName] == null) {
                        hwndMap[clsName] = hWnd
                    }
                }
                return true
            }
        }, null)
        return hwndMap
    }


    /**
     * 핸들 윈도우(HWND)의 클래스 네임을 얻는다.
     *
     * @param hWnd
     * @return
     */
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
}
