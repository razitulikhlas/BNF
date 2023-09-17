package com.razitulikhlas.banknagari.ui.petaCustomer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.razitulikhlas.core.domain.dashboard.officer.usecase.IOfficerUseCase

class MapsViewModel(private val useCase: IOfficerUseCase) : ViewModel()  {

    suspend fun getPetaBusiness() = useCase.getPetaBusiness().asLiveData()
}