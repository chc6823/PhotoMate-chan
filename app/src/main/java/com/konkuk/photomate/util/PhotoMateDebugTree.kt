package com.konkuk.photomate.util

import timber.log.Timber

class PhotoMateDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "${element.fileName}:${element.lineNumber}#${element.methodName}"
    }
}
