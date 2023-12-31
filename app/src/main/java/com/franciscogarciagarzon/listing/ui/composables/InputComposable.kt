package com.franciscogarciagarzon.listing.ui.composables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.listing.ui.theme.ListingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputComposable(buttonAction: (String) -> Unit) {

    // need to manually import androidx.compose.runtime.getValue for remember to work
    var value by remember {
        mutableStateOf("")
    }

    Row(
        modifier =
        Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

        )
    {
        OutlinedTextField(
            value = value,
            onValueChange = { newText ->
                value = newText
            },
            trailingIcon = {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier
                        .clickable {
                            value = ""
                        }
                )
            },
            modifier = Modifier.weight(2.7f)
        )
        Button(
            onClick = {
                Log.d("InputComposable", "Button clicked, value = $value")
                buttonAction(value)
                value = ""
            },
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .weight(1f),
        ) {
            Text(text = "Enter")
        }
    }

}


@Preview(showBackground = true)
@Composable
fun InputComposablePreview() {
    val buttonAciton: (String) -> Unit = {}
    ListingTheme {
        InputComposable(buttonAciton)
    }
}