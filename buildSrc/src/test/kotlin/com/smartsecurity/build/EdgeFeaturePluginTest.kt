package com.smartsecurity.build

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertEquals

class EdgeFeaturePluginTest {
    @Test
    fun `applies flags from YAML`() {
        val project = ProjectBuilder.builder().build()
        project.projectDir.resolve("edge_features.yaml").writeText(
            "features:\n  ai_advanced: true\n  kiosk_mode: false"
        )
        project.plugins.apply(EdgeFeaturePlugin::class.java)
        assertEquals(true, project.extra["HAS_AI_ADVANCED"])
        assertEquals(false, project.extra["HAS_KIOSK_MODE"])
    }
}

