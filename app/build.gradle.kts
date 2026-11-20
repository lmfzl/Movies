import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
}

val localProperties = Properties().apply {
    val propertiesFile = rootProject.file("local.properties")
    if (propertiesFile.exists()) {
        propertiesFile.inputStream().use { load(it) }
    }
}
val poiskinoApiKey = localProperties.getProperty("POISKINO_API_KEY", "")

android {
    namespace = "com.example.movies"
    compileSdk {
        version = release(37)
    }

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.movies"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "POISKINO_API_KEY", "\"$poiskinoApiKey\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.okhttp)

    implementation(libs.glide)

    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(libs.room.rxjava3)

    implementation(libs.rxandroid)
    implementation(libs.rxjava)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.retrofit2.adapter.rxjava3)

    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
}
