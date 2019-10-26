package de.ehmkah.projects.imgdiff

import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileSystem
import com.intellij.openapi.vfs.ex.dummy.DummyFileSystem
import org.intellij.images.fileTypes.impl.SvgFileType
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream
import javax.imageio.ImageIO


class ImgDiffVirtualFile(bufferedImage: BufferedImage) : VirtualFile() {

    val bufferedImage = bufferedImage

    override fun refresh(asynchronous: Boolean, recursive: Boolean, postRunnable: Runnable?) {
        TODO("anot implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLength(): Long {
        return 1000L;
    }

    override fun getFileSystem(): VirtualFileSystem {
        return DummyFileSystem()
    }

    override fun getPath(): String {
        return ""
    }

    override fun isDirectory(): Boolean {
        return false;
    }

    override fun getTimeStamp(): Long {
        return 20L;
    }

    override fun getName(): String {
        return "the name"
    }

    override fun contentsToByteArray(): ByteArray {
        val baos = ByteArrayOutputStream()
        ImageIO.write(bufferedImage, "png", baos)
        val bytes = baos.toByteArray()

        return bytes;
    }

    override fun isValid(): Boolean {
        return true;
    }

    override fun getInputStream(): InputStream {
        TODO("jnot implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getParent(): VirtualFile {
        return this;
    }

    override fun getChildren(): Array<VirtualFile> {
        TODO("lnot implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isWritable(): Boolean {
        TODO("mnot implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOutputStream(requestor: Any?, newModificationStamp: Long, newTimeStamp: Long): OutputStream {
        TODO("nnot implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFileType(): FileType {
        return SvgFileType.INSTANCE
    }
}