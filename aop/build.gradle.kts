plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.eeman.aop"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

// noinspection UseTomlInstead
dependencies {
    api("org.aspectj:aspectjrt:1.9.25.1")

    // Only needed for weaving
//    compileOnly("org.aspectj:aspectjtools:1.9.25.1")
    // Needed at runtime for woven classes
//    implementation("org.aspectj:aspectjweaver:1.9.25.1")
//    api("org.aspectj:aspectjweaver:1.9.25.1")
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
}

// this way wc add a separate gradle
apply(from = "../aspect.gradle") // apply(from = "../aspect.gradle.kts")
