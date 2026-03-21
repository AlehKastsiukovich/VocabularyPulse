plugins {
    id("multiplatformConvention")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.navigationApi)
            implementation(projects.core.navigationEntryApi)
            implementation(libs.jetbrains.compose.navigation)
        }
    }
}

android {
    namespace = "com.vocabulary.pulse.core.navigation.compose"
}
