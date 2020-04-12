package de.ehmkah.projects.imgdiff

import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileSystem
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream
import javax.imageio.ImageIO

/**
 * @param baseFile - a file where diff depends on use for delivery valid points to jump to
 */
class ImgDiffVirtualFile(val bufferedImage: BufferedImage, val baseFile: VirtualFile) : VirtualFile() {

    override fun refresh(asynchronous: Boolean, recursive: Boolean, postRunnable: Runnable?) {
        TODO("anot implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLength(): Long {
        return 1000L
    }

    override fun getFileSystem(): VirtualFileSystem {
        return baseFile.fileSystem
    }

    override fun getPath(): String {
        return baseFile.path
    }

    override fun isDirectory(): Boolean {
        return baseFile.isDirectory
    }

    override fun getTimeStamp(): Long {
        return baseFile.timeStamp
    }

    override fun getName(): String {
        return "diff.png"
    }

    override fun contentsToByteArray(): ByteArray {
        val baos = ByteArrayOutputStream()
        ImageIO.write(bufferedImage, "png", baos)
        val bytes = baos.toByteArray()

        return bytes
    }

    override fun isValid(): Boolean {
        return true
    }

    override fun getInputStream(): InputStream {
        TODO("jnot implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getParent(): VirtualFile {
        return baseFile.parent
    }

    override fun getChildren(): Array<VirtualFile> {
        TODO("lnot implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isWritable(): Boolean {
        return false
    }

    override fun getOutputStream(requestor: Any?, newModificationStamp: Long, newTimeStamp: Long): OutputStream {
        TODO("nnot implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFileType(): FileType {
        return baseFile.fileType
    }
}