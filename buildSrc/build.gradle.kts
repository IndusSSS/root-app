plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.yaml:snakeyaml:2.2")
    testImplementation(kotlin("test"))
    testImplementation(gradleTestKit())
}

gradlePlugin {
    plugins {
        create("edgeFeature") {
            id = "com.smartsecurity.edge-feature"
            implementationClass = "com.smartsecurity.build.EdgeFeaturePlugin"
        }
    }
}

