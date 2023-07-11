package com.franciscogarciagarzon.listing.ui.composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.listing.ui.theme.ListingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableListItemComposable(
    originalValue: String,
    index: Int,
    editAction: (Int, String) -> Unit,
    deleteAction: (Int) -> Unit,
) {
    var newValue by remember {
        mutableStateOf(originalValue)
    }
    Row(
        modifier =
        Modifier
            .padding(horizontal = 5.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

        )
    {
        TextField(
            modifier = Modifier
                .padding(start = 5.dp)
                .weight(3f),
            value = newValue,
            onValueChange = { newText ->
                newValue = newText
            },
            trailingIcon = {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "save changes",
                    modifier = Modifier
                        .clickable {
                            Log.d("EditableListItemComposable", "Edit button clicked: newValue = $newValue")
                            editAction(index, newValue)
                        }
                )
            }
        )
        IconButton(
            onClick = {deleteAction(index)},
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(0.5f),
            content = { Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Delete this item",
                tint = Color.Red
            ) }
        )
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun EditableListItemComposablePreview() {
    ListingTheme {
        EditableListItemComposable("Apple", 1, {_, _ ->}, {})
    }
}