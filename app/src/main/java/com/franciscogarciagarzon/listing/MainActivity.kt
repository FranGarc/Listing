package com.franciscogarciagarzon.listing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.franciscogarciagarzon.listing.ui.composables.EditableListComposable
import com.franciscogarciagarzon.listing.ui.composables.InputComposable
import com.franciscogarciagarzon.listing.ui.theme.ListingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            MainContent(
                //viewModel.elements.value doesn't register any changes, so we need to use collectAsState
                elements = viewModel.elements.collectAsState().value.toMutableStateList(),
                addAction = { element -> viewModel.addElementToList(element) },
                deleteAction = { index -> viewModel.delete(index) },
                editAction = { index, newElement -> viewModel.editElement(index, newElement) }
            )
        }
    }

}


@Composable
fun MainContent(
    elements: SnapshotStateList<String>,
    addAction: (String) -> Unit,
    editAction: (Int, String) -> Unit,
    deleteAction: (Int) -> Unit,
) {
    ListingTheme {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            InputComposable(addAction)
            EditableListComposable(
                elements = elements,
                editAction = editAction,
                deleteAction = deleteAction,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    ListingTheme {
        MainContent(
            elements = remember { mutableStateListOf<String>() },
            addAction = { _ -> },
            editAction = { _, _ -> },
            deleteAction = { _ -> },
        )
    }
}