plugins {
    id("multiplatformConvention")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.navigationApi)

            implementation(libs.koin.viewmodel)
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

android {
    namespace = "com.vocabulary.pulse.feature.home"
}
