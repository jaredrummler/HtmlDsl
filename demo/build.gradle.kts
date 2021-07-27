plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.jaredrummler.htmldsl.demo"
        minSdkVersion(28)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets {
        map { set ->
            // https://github.com/gradle/kotlin-dsl-samples/issues/443
            set.java.srcDir("src/${set.name}/kotlin")
        }
    }
}

dependencies {
    implementation(project(":library"))
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.AndroidX.navigation)
    implementation(Dependencies.AndroidX.navigationKtx)
    implementation(Dependencies.AndroidX.ktx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.constraint)
    implementation(Dependencies.Google.material)
    implementation(Dependencies.Github.smartTabLayout)
    implementation(Dependencies.Github.smartTabLayoutUtils)
    implementation(Dependencies.BlackSquircle.editor)
    implementation(Dependencies.BlackSquircle.kotlin)
    implementation(Dependencies.BlackSquircle.html)
    implementation(Dependencies.Github.Bump.glide)
    annotationProcessor(Dependencies.Github.Bump.compiler)
}
