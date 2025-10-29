plugins {
    id("multiplatformConvention")
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.googleKsp)
    alias(libs.plugins.room)
}

room {
    schemaDirectory("$projectDir/schemas")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.navigationApi)
            implementation(projects.core.coreMvi)
            implementation(projects.core.data)
            implementation(libs.koin.viewmodel)
        }
        androidMain.dependencies {
            implementation(libs.room.runtime)
        }
        iosMain.dependencies {
            implementation(libs.room.runtime)
        }
        jvmMain.dependencies {
            implementation(libs.room.runtime)
        }
    }
}

dependencies {
    add("kspAndroid", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspJvm", libs.room.compiler)

    debugImplementation(compose.uiTooling)
}

android {
    namespace = "com.vocabulary.pulse.feature.add"
}
