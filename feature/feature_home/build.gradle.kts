plugins {
    id("multiplatformConvention")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.navigationApi)
            implementation(projects.core.navigationEntryApi)

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
