plugins {
    //todo create separate convention for modules which shpuldn't depend on compose
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
