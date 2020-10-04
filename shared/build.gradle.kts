import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("com.squareup.sqldelight")
}
group = "net.multipul.kmm"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}

dependencies {
    commonMainImplementation("dev.icerock.moko:mvvm:0.8.0")
    // temporary fix of https://youtrack.jetbrains.com/issue/KT-41083
    commonMainImplementation("dev.icerock.moko:resources:0.13.0")
    commonMainImplementation("dev.icerock.moko:parcelize:0.4.0")
    commonMainImplementation("dev.icerock.moko:graphics:0.4.0")
}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    sourceSets {
        val commonMain by getting{
            dependencies {
                implementation("com.squareup.sqldelight:runtime:1.4.3")
                implementation("io.ktor:ktor-client-android:1.3.2")
//                implementation("dev.icerock.moko:mvvm:0.8.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.2.1")
                implementation("com.squareup.sqldelight:android-driver:1.4.3")
                implementation("io.ktor:ktor-client-android:1.3.2")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")
            }
        }
        val iosMain by getting{
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.4.3")
//                implementation("io.ktor:ktor-client-ios:1.3.2")
//                implementation("io.ktor:ktor-client-core-native:1.3.2")
            }
        }
        val iosTest by getting
    }
}

sqldelight {
    database("Database") {
        packageName = "net.multipul.kmm"
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)