package com.smartsecurity.rootapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Holds UI state for [MainActivity].
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    data class UiState(
        val jwtValid: Boolean = false,
        val queued: Int = 0,
        val sent: Int = 0,
        val cpu: Float = 0f,
        val battery: Int = 0
    )
}
