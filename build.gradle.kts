// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
}

//// noinspection UseTomlInstead
//buildscript {
//    dependencies {
//        classpath("org.aspectj:aspectjtools:1.9.8")
//        classpath("org.aspectj:aspectjweaver:1.9.8")
//    }
//}