package com.franciscogarciagarzon.listing.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franciscogarciagarzon.listing.ui.theme.ListingTheme


@Composable
fun EditableListComposable(
    elements: SnapshotStateList<String>,
    editAction: (Int, String) -> Unit,
    deleteAction: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        state = rememberLazyListState()
    ) {
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally),
                    text = "List Title",
                    fontSize = 22.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.height(height = 8.dp)) // extra space below the heading
        }
        itemsIndexed(elements) { _index, item ->
            EditableListItemComposable(
                originalValue = item,
                index = _index,
                editAction = { pos, newValue -> editAction(pos, newValue) },
                deleteAction = { pos -> deleteAction(pos) }
            )
        }
    }

}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun EditableListComposablePreview() {
    val fruits = mutableStateListOf<String>("Apple", "Mango", "Banana", "Orange", "Watermelon", "Papaya", "other fruit", "yet another fruit")
    ListingTheme {
        EditableListComposable(
            elements = fruits,
            deleteAction = { _ -> },
            editAction = { _, _ -> }
        )
    }
}