package network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class Ktor {
    suspend fun getLink() {
        val client = HttpClient(CIO)
        val response: HttpResponse =
            client.get("https://cosplaytele.com/wp-content/uploads/2023/06/ArtGravia-Inkyung-Shipper-94_result-scaled.webp") {
                headers {


                    append("Referer", "https://cosplaytele.com/?s=%EC%9D%B8%EA%B2%BD")
                    append(HttpHeaders.AcceptEncoding, "gzip, deflate, br")
                    append(HttpHeaders.AcceptLanguage, "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6")
                    append(
                        HttpHeaders.Accept,
                        "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"
                    )
                    append(
                        HttpHeaders.UserAgent,
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36"
                    )

                }
            }
        // val body: String = response.body()
        println(response.status)
        client.close()
    }
}