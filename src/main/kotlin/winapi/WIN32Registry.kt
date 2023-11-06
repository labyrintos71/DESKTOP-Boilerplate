package winapi

import com.sun.jna.platform.win32.Advapi32Util
import com.sun.jna.platform.win32.WinReg


class WIN32Registry {

    fun get(value: String) {
        //registry, 소스 분개 및 테스트 필요
        // Read a string 읽기
        val productName = Advapi32Util.registryGetStringValue(
            WinReg.HKEY_CURRENT_USER, "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run", "GochigoAlimi"
        )
        System.out.printf("Product Name: %s\n", productName)
    }

    fun get(value: Int) {
        // Read an int (& 0xFFFFFFFFL for large unsigned int) 읽기
        val timeout = Advapi32Util.registryGetIntValue(
            WinReg.HKEY_CURRENT_USER,
            "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Windows",
            "ShutdownWarningDialogTimeout"
        )
        System.out.printf(
            "Shutdown Warning Dialog Timeout: %d (%d as unsigned long)\n",
            timeout,
            timeout.toLong() and 0xFFFFFFFFL
        )
    }

    fun write(value: String){
        // Create a key and write a string 생성
        Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, "SOFTWARE\\~~")
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, "", "", "")
    }

    fun delete(value: String){
        // Delete a key 삭제
        Advapi32Util.registryDeleteKey(WinReg.HKEY_CURRENT_USER, "SOFTWARE\\~~")
    }
}