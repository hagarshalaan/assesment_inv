package com.flag.invadems.presentation.universities

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flag.module.data.models.University

import com.flag.module.data.repository.UniversityRepository
import com.flag.module.domain.UniversitiesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversitiesViewModel @Inject constructor(private val repository: UniversityRepository) : ViewModel() {


    private val _uiState = MutableLiveData<UniversitiesUiState>()
    val uiState: LiveData<UniversitiesUiState> = _uiState
    fun onUniversityItemClick(university: University) {
        _uiState.value = _uiState.value?.copy(
            universityClicked = university,
        )
    }
    init {
        _uiState.value = UniversitiesUiState(isLoading = false, errorMessage = null)
    }

    fun searchUniversities(country: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value?.copy(isLoading = true, errorMessage = null)

                val universities = repository.searchUniversities(country)

                _uiState.value = _uiState.value?.copy(
                    universitiesList = universities,
                    isLoading = false,
                    errorMessage = null
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(
                    isLoading = false,
                    errorMessage = "Failed to load universities: ${e.message}"
                )
            }
        }
    }
}
