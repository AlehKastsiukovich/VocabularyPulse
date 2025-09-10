plugins {
    id("multiplatformConvention")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
        }
    }
}

android {
    namespace = "com.vocabulary.pulse.core.navigation.api"
}
