package com.vadym.upwind.ui.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.BaselineShift

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsBar(
    options: Array<String>,
    settingName: String,
    selectedOptionText: String,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    //val selectedOptionText by rememberSaveable { mutableStateOf(initialValue) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(
            text = settingName,
            modifier = Modifier.weight(2f)
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.weight(2f)
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                textStyle = MaterialTheme.typography.body1.copy(baselineShift = BaselineShift(-0.25f)),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = LocalContentColor.current.copy(alpha = 0.5f),
                    trailingIconColor = Color.White,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEachIndexed { index, option ->
                    DropdownMenuItem(
                        onClick = {
                            //selectedOptionText = option
                            expanded = false
                            onClick(index)
                        }
                    ) {
                        Text(
                            text = option,
                            modifier = Modifier.alpha(0.5f)
                        )
                    }
                }
            }
        }
    }
}