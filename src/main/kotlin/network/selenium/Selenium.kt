package network.selenium

import com.sun.jna.platform.win32.User32
import file.FileIO
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import winapi.WIN32Handle
import java.io.File

class Selenium {
    private val useHeadless = false
    fun run() {
// System.setProperty("webdriver.chrome.driver", "D:/Git/Kotlin/selenium_exam/lib/chromedriver2.exe")
        val options: ChromeOptions = ChromeOptions()
        //창 안띄우기
        if (useHeadless) {
            options.addArguments("headless")
            options.addArguments("window-size=1920,1080")
            options.addArguments("--disable-gpu")
            //https://www.zenrows.com/blog/bypass-cloudflare#purpose-of-bypassing-cloudflare-waiting-room
            //cf 거의 우회 불가
            options.addArguments("--no-sandbox")
            options.addArguments("--start-maximized")
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
            options.addArguments("lang=ko_KR")
        }

        val driver: ChromeDriver = ChromeDriver(options)
        val downloader: AjaxFileDownloader = AjaxFileDownloader(driver)
        try {
            driver.get("https://cosplaytele.com/shipper/")
            User32.INSTANCE.CloseWindow(User32.INSTANCE.GetForegroundWindow())

            //유저에이전트 가져오는 소스
            // println(driver.executeScript("return navigator.userAgent"))

            var title = driver.title
            title = title.substring(0, title.lastIndexOf("photo"))
            title = title.substring(0, title.lastIndexOf('-')).replace(" ", "")
            println(title)
/*
            //폴더 생성
            //Files.createDirectory(Paths.get("c:\\Workspace\\${title}"))
            val resultMkdir: Boolean = File("c:\\Workspace\\${title}").mkdir()
            //이미지 리스트 받아오기
            val imglist = driver.findElements(By.className("gallery-item"))

            for ((index, item) in imglist.withIndex()) {
                println(item.findElement(By.tagName("a")).getAttribute("href"))
                FileIO().writeByInputStream(
                    downloader.download(imglist.get(0).findElement(By.tagName("a")).getAttribute("href")),
                    "C:\\Workspace\\${title}\\${title}_${index + 1}.webp"
                )
            }
*/

//        val doc: WebElement = driver.findElement(
//            By.xpath("/html/body/div[2]/section/div/div[2]/div[2]/div/div/div[2]/div/div[5]/div[1]/div/div/div[1]/div")
//        )
            //scroll
            //.execute_script("window.scrollTo(0, 700)")

            //페[이지이동
            //get,navigate().to

            //키입력
            //sendkeys
        } catch (e: Exception) {
            println("error")
            e.printStackTrace()
        } finally {
            if (!useHeadless) driver.close()
            driver.quit()
        }

    }
}