package de.ehmkah.projects.imgdiff

import com.intellij.diff.DiffManagerImpl
import com.intellij.diff.DiffTool

class ImgDiffManagerImpl : DiffManagerImpl() {

    override fun getDiffTools(): MutableList<DiffTool> {
        val diffTools = super.getDiffTools()
        diffTools.add(0, ImgDiffOriginalBackgroundBinaryDiffTool.instance)
        diffTools.add(1, ImgDiffWhiteBackgroundBinaryDiffTool.instance)
        diffTools.add(2, ImgDiffBlinkingDiffTool.instance)

        return diffTools;
    }
}