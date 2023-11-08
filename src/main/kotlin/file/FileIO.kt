package file

import java.io.File
import java.io.InputStream
import java.util.Arrays

class FileIO {
    fun writeByInputStream(inputStream: InputStream, filePath: String) {
        //저장할 파일
        val saveFile = File(filePath)

        saveFile.outputStream().use { fileOutput ->
            inputStream.copyTo(fileOutput)
        }
    }

    fun writeFileByTest(path: String, text: String) {
        val file = File(path)
        file.writeText(text)
        //  file.appendText("hello")
    }

    fun printFileLists(path: String) {
        val files = File(path).listFiles()        // 지정해 준 경로 내의 파일을 리스트로 생성
        println(files.contentToString())
    }

    fun copyFile(originPath: String, copyPath: String) {
        File(originPath).copyTo(File(copyPath))
    }
    
}
