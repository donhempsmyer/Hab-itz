// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidapplication) apply false
    alias(libs.plugins.kotlinandroid) apply false
    kotlin("kapt") version "2.1.0"



}
val buildToolsVersion by extra("34.0.0")
