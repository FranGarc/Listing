package com.franciscogarciagarzon.listing

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.franciscogarciagarzon.listing.ui.composables.EditableListComposable
import com.franciscogarciagarzon.listing.ui.composables.InputComposable
import com.franciscogarciagarzon.listing.ui.theme.ListingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }

}


@Composable
fun MainContent() {
    ListingTheme {

        // lists aren't delegates to lists, hence no "by" for this
        val elements: SnapshotStateList<String> = remember { mutableStateListOf<String>() }
        val buttonAction: (String) -> Unit = { enteredText ->
            elements.add(enteredText)
            Log.d("MainContent", "Button Clicked, current list: ${elements.toList()}")
        }
        // A surface container using the 'background' color from the theme
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            InputComposable(buttonAction)
            EditableListComposable(elements = elements)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    ListingTheme {
        MainContent()
    }
}