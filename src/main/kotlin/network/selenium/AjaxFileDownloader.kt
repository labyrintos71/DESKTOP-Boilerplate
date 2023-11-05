package network.selenium

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.time.Duration

class AjaxFileDownloader (private val driver: WebDriver) {
    //use org.seleniumhq.selenium:selenium-java
    init {
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15)) // maybe you need a different timeout
    }

    @Throws(IOException::class)
    fun download(url: String?): InputStream {
        val script = "var url = arguments[0];" +
                "var callback = arguments[arguments.length - 1];" +
                "var xhr = new XMLHttpRequest();" +
                "xhr.open('GET', url, true);" +
                "xhr.responseType = \"arraybuffer\";" +  //force the HTTP response, response-type header to be array buffer
                "xhr.onload = function() {" +
                "  var arrayBuffer = xhr.response;" +
                "  var byteArray = new Uint8Array(arrayBuffer);" +
                "  callback(byteArray);" +
                "};" +
                "xhr.send();"
        val response = (driver as JavascriptExecutor).executeAsyncScript(script, url)
        // Selenium returns an Array of Long, we need byte[]
        val byteList = response as ArrayList<Long>
        val bytes = ByteArray(byteList.size)
        for (i in byteList.indices) {
            bytes[i] = byteList[i].toByte()
        }
        return ByteArrayInputStream(bytes)
    }
}