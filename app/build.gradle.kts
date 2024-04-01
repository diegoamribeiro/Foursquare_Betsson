import java.io.FileNotFoundException
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.navigationSafeArgs)
    alias(libs.plugins.hiltAndroidPlugin)
    kotlin("kapt")
}

val apiKeyProperties: Properties by lazy {
    val apiKeyPropertiesFile = rootProject.file("apiKey.properties")
    if (!apiKeyPropertiesFile.exists()) {
        throw FileNotFoundException("Could not find apiKey.properties at ${apiKeyPropertiesFile.absolutePath}")
    }
    Properties().apply {
        load(apiKeyPropertiesFile.inputStream())
    }
}

android {
    namespace = "com.dmribeiro87.foursquarebetsson"
    compileSdk = 34

    android.buildFeatures.buildConfig = true

    defaultConfig {
        applicationId = "com.dmribeiro87.foursquarebetsson"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        buildConfigField("String", "API_KEY", "${apiKeyProperties["API_KEY"]}")
        buildConfigField("String", "BASE_URL", "${apiKeyProperties["BASE_URL"]}")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions("product", "side")

    productFlavors {

        create("coffee") {
            dimension = "product"
            applicationIdSuffix = ".coffee"
            manifestPlaceholders["appName"] = "CoffeeApp"
            buildConfigField("String", "CATEGORY_ID", "\"13035\"")
            buildConfigField("int", "RADIUS", "1000")
        }
        create("cocktail") {
            dimension = "product"
            applicationIdSuffix = ".cocktail"
            manifestPlaceholders["appName"] = "CocktailApp"
            buildConfigField("String", "CATEGORY_ID", "\"13009\"")
            buildConfigField("int", "RADIUS", "3000")
        }
        create("client") {
            dimension = "side"
            manifestPlaceholders["appNameSuffix"] = ""
        }
    }

    sourceSets {
        // Configuração para o flavor coffee
        getByName("coffee") {
            manifest.srcFile("src/coffee/AndroidManifest.xml")
            java.srcDir("src/coffee/java")
            res.srcDir("src/coffee/res")
            // Adicione outras pastas de recursos e código, se necessário
        }

        // Configuração para o flavor cocktail
        getByName("cocktail") {
            manifest.srcFile("src/cocktail/AndroidManifest.xml")
            java.srcDir("src/cocktail/java")
            res.srcDir("src/cocktail/res")
            // Adicione outras pastas de recursos e código, se necessário
        }

        // Configuração para outros flavors como client e admin, se necessário
        getByName("client") {
            manifest.srcFile("src/client/AndroidManifest.xml")
            // Configurações adicionais conforme necessário
        }

    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.adapter.rxjava2)
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    // Koin
    implementation (libs.koin.android)

    // Architectural Components
    implementation (libs.androidx.lifecycle.viewmodel.ktx)

    // Kotlin Extensions and Coroutines support for Room
    implementation (libs.androidx.room.ktx)

    // Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation (libs.androidx.lifecycle.viewmodel.ktx.v261)
    implementation (libs.androidx.lifecycle.runtime.ktx.v261)
    implementation (libs.androidx.lifecycle.livedata.ktx)

    //Jetpack Navigation Component
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)

    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    // Glide
    implementation (libs.glide)
    annotationProcessor(libs.glideCompiler)
}