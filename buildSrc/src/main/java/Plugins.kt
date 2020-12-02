private object PluginsVersions {
    const val GRADLE_ANDROID = "3.5.3"
    const val GOOGLE_SERVICE = "4.3.3"
    const val KOTLIN = "1.3.61"
    const val NAVIGATION = "2.2.1"
    const val JACOCO = "0.16.0"
    const val KTLINT = "0.36.0"
    const val DETEKT = "1.2.2"
}

object Plugins {
    const val GOOGLE_SERVICES = "com.google.gms:google-services:${PluginsVersions.GOOGLE_SERVICE}"
    const val BUILD_TOOLS = "com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}"
    const val KOTLIN ="org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}"
    const val KOTLIN_EXTENSIONS ="org.jetbrains.kotlin:kotlin-android-extensions:${PluginsVersions.KOTLIN}"
    const val SAFE_ARGS ="androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}"
    const val JACOCO ="com.vanniktech:gradle-android-junit-jacoco-plugin:${PluginsVersions.JACOCO}"
    const val KTLINT ="com.pinterest:ktlint:${PluginsVersions.KTLINT}"
    const val DETEKT ="io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginsVersions.DETEKT}"
}
