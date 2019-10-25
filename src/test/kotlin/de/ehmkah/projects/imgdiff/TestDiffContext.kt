package de.ehmkah.projects.imgdiff

import com.intellij.diff.DiffContext
import com.intellij.openapi.project.Project

class TestDiffContext : DiffContext() {
    override fun isWindowFocused(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isFocusedInWindow(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestFocusInWindow() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProject(): Project? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}