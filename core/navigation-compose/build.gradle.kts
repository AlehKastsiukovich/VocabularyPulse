plugins {
    id("multiplatformConvention")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.navigationApi)
        }
    }
}

android {
    namespace = "com.vocabulary.pulse.core.navigation.compose"
}
