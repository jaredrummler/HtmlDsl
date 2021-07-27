plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(30)
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        // https://github.com/gradle/kotlin-dsl-samples/issues/443
        map { it.java.srcDir("src/${it.name}/kotlin") }
    }
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.AndroidX.ktx)
    testImplementation(Dependencies.Testing.junit)
}

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")
