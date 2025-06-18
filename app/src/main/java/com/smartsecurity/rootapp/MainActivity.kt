package com.smartsecurity.rootapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Minimal root-app UI displaying telemetry status.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    Text(
        text = "JWT: ${'$'}{if (state.jwtValid) "valid" else "expired"} | queued: ${'$'}{state.queued} | sent: ${'$'}{state.sent} | CPU: ${'$'}{state.cpu}°C | Bat: ${'$'}{state.battery}%"
    )
}
