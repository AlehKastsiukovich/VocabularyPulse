rootProject.name = "VocaPulse"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("multiplatform-convention-plugin")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(
    ":composeApp",
    ":feature:feature_home",
    ":feature:feature_add_word",
    ":core:navigation-api",
    ":core:navigation-compose",
    ":core:core-mvi",
    ":feature:feature_hello",
    ":core:database",
    ":core:data",
    ":core:common",
    ":feature:feature_card"
)

