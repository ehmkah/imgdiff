package de.ehmkah.projects.imgdiff

import com.intellij.diff.DiffContext
import com.intellij.diff.requests.ContentDiffRequest
import com.intellij.diff.requests.DiffRequest
import com.intellij.diff.tools.binary.ThreesideBinaryDiffViewer

class ImgDiffDiffViewer(context: DiffContext, request: DiffRequest) : ThreesideBinaryDiffViewer(context, request) {

}

fun canShowRequest(request: DiffRequest): Boolean {
    if (request is ContentDiffRequest) {
        return request.contents.size == 2
    }

    return false
}