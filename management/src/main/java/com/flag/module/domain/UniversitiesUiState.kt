package com.flag.module.domain

import com.flag.module.data.models.University


data class UniversitiesUiState(
    var universitiesList: MutableList<University> = mutableListOf(),
    var isErrorConnection: Boolean = false,
    var isLoading: Boolean = false,
    var errorMessage: String?=null,
    var universityClicked: University?=null
)

