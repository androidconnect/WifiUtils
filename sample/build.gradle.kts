plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    kotlin("android")
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK_VERSION
    //buildToolsVersion("30.0.3")
    defaultConfig {
        applicationId = Artifact.ARTIFACT_GROUP + ".sample"
        minSdk = AndroidConfig.MIN_SDK_VERSION
        targetSdk = AndroidConfig.TARGET_SDK_VERSION
        versionCode = Artifact.VERSION_CODE
        versionName = Artifact.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file(property("KEYSTORE_PATH").toString())
            storePassword = property("KEYSTORE_PASSWORD").toString()
            keyAlias = property("KEY_ALIAS").toString()
            keyPassword = property("KEY_PASSWORD").toString()
        }
    }

    buildTypes {

        debug {
            isDefault = true
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isShrinkResources = BuildTypeDebug.isShrinkResources
            isDebuggable = BuildTypeDebug.isDebuggable
        }
        release {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isShrinkResources = BuildTypeRelease.isShrinkResources
            isDebuggable = BuildTypeRelease.isDebuggable
            signingConfig = signingConfigs.getByName("release")
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
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    addAppModuleDependencies()
    //addTestDependencies()
}
