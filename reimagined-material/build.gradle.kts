plugins {
    `android-library-config`
    `publishing-config`
}

android {
    namespace = "${project.group}.reimagined.material"
}

dependencies {
    api(project(":reimagined"))
    api(Libs.AndroidX.Compose.Material)
    api(Libs.AndroidX.Compose.UiUtil)
}