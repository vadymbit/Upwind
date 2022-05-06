package com.vadym.upwind.ui.weatherlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.vadym.upwind.model.SearchBarState
import com.vadym.upwind.ui.weatherlist.components.SearchBar
import com.vadym.upwind.ui.weatherlist.components.WeatherBox
import org.koin.androidx.compose.getViewModel

@Composable
fun WeatherListScreen(
    viewModel: WeatherListViewModel = getViewModel(),
    navigateHome: () -> Unit
) {
    val searchBarState by viewModel.searchBarState.collectAsState(SearchBarState())
    val weatherList by viewModel.weatherList.collectAsState()
    var editMode by rememberSaveable { mutableStateOf(false) }
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            SearchBar(
                modifier = Modifier.weight(1f),
                searchBarState.text,
                onQueryChange = { query ->
                    viewModel.onQueryChange(query)
                },
                onClearText = {
                    viewModel.clearSearchField()
                }
            )
            IconButton(onClick = { editMode = !editMode }) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit weather"
                )
            }
        }
        AnimatedVisibility(
            visible = searchBarState.citiesList.isNotEmpty(),
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            LazyColumn {
                items(searchBarState.citiesList.size) {
                    val city = searchBarState.citiesList[it]
                    val cityName: String = if (searchBarState.citiesList[it].region.isNotEmpty()) {
                        "${city.name}, ${city.region}, ${city.country}"
                    } else {
                        "${city.name}, ${city.country}"
                    }
                    Text(
                        text = cityName,
                        modifier = Modifier.clickable {
                            viewModel.getCityWeather(
                                city = city
                            )
                        }
                    )
                }
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 16.dp)
        ) {
            items(weatherList.size) {
                WeatherBox(
                    isCloseVisible = editMode,
                    onCloseClick = { viewModel.deleteCityWeather(it) },
                    weatherModel = weatherList[it],
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colors.surface)
                        .alpha(0.7f)
                        .aspectRatio(1f)
                        .clickable {
                            viewModel.displayWeatherOnHomeScreen(weatherList[it].cityId)
                            navigateHome()
                        }
                        .padding(10.dp)
                )
            }
        }
    }
}