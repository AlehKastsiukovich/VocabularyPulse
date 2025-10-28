plugins {
    id("multiplatformConvention")
    alias(libs.plugins.googleKsp)
    alias(libs.plugins.room)
}

room {
    schemaDirectory("$projectDir/schemas")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)
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

android {
    namespace = "com.vocabulary.pulse.core.database"
}

dependencies {
    add("kspAndroid", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspJvm", libs.room.compiler)
}


