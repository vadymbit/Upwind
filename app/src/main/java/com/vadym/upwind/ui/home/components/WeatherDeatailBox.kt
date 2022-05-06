package com.vadym.upwind.ui.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vadym.upwind.ui.theme.Blue200

@Composable
fun WeatherDetailBox(
    @DrawableRes icon: Int,
    iconDescription: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = iconDescription,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp
                )
                .size(20.dp),
            tint = Blue200.copy(0.6f)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body2
        )
    }
}