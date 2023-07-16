package com.franciscogarciagarzon.listing

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franciscogarciagarzon.listing.domain.entities.Element
import com.franciscogarciagarzon.listing.domain.entities.InvalidElementException
import com.franciscogarciagarzon.listing.domain.usecases.AddElementUseCase
import com.franciscogarciagarzon.listing.domain.usecases.GetElementsUseCase
import com.franciscogarciagarzon.listing.ui.ListingEvent
import com.franciscogarciagarzon.listing.ui.ListingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getElementsUseCase: GetElementsUseCase,
    private val addElementUseCase: AddElementUseCase,
) : ViewModel() {

    // because of this simple case, we just need to initialise it and collect wherever we need to get the changes
//    private val _elements: MutableStateFlow<MutableList<Element>> //= MutableStateFlow(mutableStateListOf<String>())
//    val elements: StateFlow<List<Element>> = _elements
    private val _errorState = MutableStateFlow(ListingState.ErrorState())
    val errorState: StateFlow<ListingState.ErrorState> = _errorState
    private val _state = mutableStateOf(ListingState.ListState())
    val state: State<ListingState.ListState> = _state
    private var getElementsJob: Job? = null

    init {
        getElements()
    }

    private fun getElements() {
        getElementsJob?.cancel()
        getElementsJob = getElementsUseCase()
            .onEach { elements ->
                _state.value = state.value.copy(
                    elements = elements
                )
            }
            .launchIn(viewModelScope)
    }


    fun onEvent(event: ListingEvent) {
        when (event) {
            is ListingEvent.AddElement -> {
                addElementToList(event.value)
            }

            is ListingEvent.DeleteElement,
            is ListingEvent.EditElement -> onError("Functionality currently not available")

            is ListingEvent.ErrorEvent -> onError(event.errorMessage)
        }
    }

    fun addElementToList(element: String) {
        viewModelScope.launch {
            try {
                addElementUseCase(element = Element(value = element))
            } catch (e: InvalidElementException) {
                Log.e("MainViewModel", "Exception: ${e.message}")
                onError("Exception: ${e.message}")
            }
        }
    }

    fun onError(errorMessage: String) {
        viewModelScope.launch {
            _errorState.emit(ListingState.ErrorState(errorMessage = errorMessage))
        }
    }

    fun editElement(index: Int, element: String) {
    }

    fun delete(index: Int) {
    }
}