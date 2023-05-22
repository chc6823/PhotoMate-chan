buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath(libs.agp)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.kotlin.serialization)
        classpath(libs.hilt.plugin)
        classpath(libs.navigation.safeargs)
        classpath(libs.firebase.crashlytics.gradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://devrepo.kakao.com/nexus/content/groups/public/")
        maven(url = "https://naver.jfrog.io/artifactory/maven/")
    }
}

subprojects {
    afterEvaluate {
        project.apply("$rootDir/gradle/common.gradle")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
