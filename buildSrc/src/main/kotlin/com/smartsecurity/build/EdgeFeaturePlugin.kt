package com.smartsecurity.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import org.yaml.snakeyaml.Yaml

/**
 * Gradle plugin that reads `edge_features.yaml` and exposes flags as
 * extra properties and build config fields.
 */
class EdgeFeaturePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val featureFile = File(project.rootDir, "edge_features.yaml")
        val yaml = Yaml()
        val map: Map<String, Any?> =
            if (featureFile.exists()) yaml.load(featureFile.readText()) else emptyMap()
        val features = map["features"] as? Map<String, Boolean> ?: emptyMap()
        features.forEach { (flag, enabled) ->
            val key = "HAS_${flag.uppercase()}"
            project.extensions.extraProperties.set(key, enabled)
        }
    }
}

