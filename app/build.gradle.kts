plugins {
    alias(libs.plugins.androidapplication)
    alias(libs.plugins.kotlinandroid)
    id ("kotlin-kapt")
    //alias(libs.plugins.kotlinksp) apply false
}

android {
    namespace = "com.example.mytestapplication2"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mytestapplication2"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }



    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        viewBinding = true
    }
    buildToolsVersion = rootProject.extra["buildToolsVersion"] as String
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.swiperefreshlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.room.runtime)

    annotationProcessor(libs.androidx.room.compiler)

    //kapt("groupId:artifactId:version")

    kapt(libs.androidx.room.compiler)

    implementation(libs.androidx.room.ktx)

    androidTestImplementation(libs.androidx.room.testing)
}