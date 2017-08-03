package com.ppandroid.im.utils

object TextUtils {
    fun isEmpty(vararg str: String): Boolean {
        for (string in str) {
            if (string == null || string.isEmpty()) {
                return true
            } else {
                continue
            }
        }
        return false

    }

}
