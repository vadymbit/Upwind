package com.vadym.upwind.ui.weatherlist.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    hint: String = "Search your city there",
    onQueryChange: (String) -> Unit,
    onClearText: () -> Unit
) {
    var showClearIcon by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalTextInputService.current
    val focusManager = LocalFocusManager.current

    showClearIcon = query.isNotEmpty()

    OutlinedTextField(
        value = query,
        onValueChange = {
            onQueryChange(it)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = showClearIcon,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(onClick = { onClearText() }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear entered text",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        },
        maxLines = 1,
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hideSoftwareKeyboard()
                focusManager.clearFocus()
            }
        ),
        placeholder = {
            Text(text = hint)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        modifier = modifier
    )
}