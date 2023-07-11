package com.franciscogarciagarzon.listing

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    private val _elements = mutableStateListOf<String>()
    val elements: SnapshotStateList<String> = _elements

    fun addElementToList(element: String) {
        _elements.add(element)
    }

    fun editElement(index: Int, element: String) {
        _elements[index] = element
    }

    fun delete(index: Int) {
        _elements.removeAt(index)
    }
}