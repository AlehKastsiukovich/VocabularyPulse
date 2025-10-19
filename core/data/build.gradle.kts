plugins {
    id("multiplatformConvention")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.database)
        }
    }
}

android {
    namespace = "com.vocabulary.pulse.core.data"
}


