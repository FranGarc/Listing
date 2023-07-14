package com.franciscogarciagarzon.listing

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franciscogarciagarzon.listing.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    // because of this simple case, we just need to initialise it and collect wherever we need to get the changes
    private val _elements: MutableStateFlow<MutableList<String>> = MutableStateFlow(mutableStateListOf<String>())
    val elements: StateFlow<List<String>> = _elements

    fun addElementToList(element: String) {
        viewModelScope.launch {
            repository.doDatabaseCall()
        }
        val tempList = _elements.value.toMutableList()
        tempList.add(element)
        _elements.value = tempList
    }

    fun editElement(index: Int, element: String) {
        val tempList = _elements.value.toMutableList()
        tempList[index] = element
        _elements.value = tempList
    }

    fun delete(index: Int) {
        val tempList = _elements.value.toMutableList()
        tempList.removeAt(index)
        _elements.value = tempList
    }
}