@Suppress("AndroidGradlePluginVersion") buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
        maven ( url = "https://dl.bintray.com/icerockdev/moko" )
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath ("com.squareup.sqldelight:gradle-plugin:1.4.3")
    }
}
group = "net.multipul.kmm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        maven( url = "https://dl.bintray.com/icerockdev/moko" )
    }
}
