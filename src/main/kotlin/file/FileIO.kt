package file

import java.io.File
import java.io.InputStream

class FileIO {
    fun writeByInputStream(inputStream: InputStream, filePath: String) {
        //저장할 파일
        val saveFile = File(filePath)

        saveFile.outputStream().use { fileOutput ->
            inputStream.copyTo(fileOutput)
        }
    }
}