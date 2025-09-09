plugins {
    id("multiplatformConvention")
}

dependencies {
    debugImplementation(compose.uiTooling)
}

android {
    namespace = "com.vocabulary.pulse.feature.home"
}



