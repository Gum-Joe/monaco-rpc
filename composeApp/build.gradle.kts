import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                cssSupport {
                    enabled.set(true)
                }
            }
        }
        binaries.executable()
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
        }
        val wasmJsMain by getting {
            dependencies {
                implementation(npm("monaco-editor", "0.45.0"))
                implementation(npm("monaco-editor-webpack-plugin", "7.1.0"))
            }
        }
    }
}

