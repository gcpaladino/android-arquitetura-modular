package com.marvel.desafioandroid.dataLayer.extension

import java.security.MessageDigest

fun String.toMD5() =
    MessageDigest
        .getInstance("MD5")
        .digest(toByteArray())
        .toHex()

fun ByteArray.toHex() = joinToString("") {
    "%02x".format(it)
}