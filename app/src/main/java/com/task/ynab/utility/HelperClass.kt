package com.task.ynab.utility

import java.math.BigInteger
import java.security.MessageDigest

 class HelperClass {
  fun md5Encript(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}