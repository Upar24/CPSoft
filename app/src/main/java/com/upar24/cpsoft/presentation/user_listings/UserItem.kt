package com.upar24.cpsoft.presentation.user_listings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.upar24.cpsoft.domain.model.User
import com.upar24.cpsoft.presentation.destinations.UsersSubListScreenByCityDestination
import kotlin.math.exp


@Composable
fun UserItem(
    user: User,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment=Alignment.CenterVertically
    ){
        Text(
            text = user.name
        )
        Text(
            text = user.city
        )
    }
}

@Composable
@Destination
fun CitySelection(navigator: DestinationsNavigator, filterList:Boolean){
    val cityList = mutableListOf<String>(
        "Aceh","Medan","Padang",
        "Jambi","Bengkulu","Palembang",
        "Tangerang","Jakarta","Bandung",
        "Yogyakarta","Surabaya","Malang",
        "Tangerang Selatan"
    )
    var cityName: String by remember { mutableStateOf(cityList[0]) }
    var expanded by remember { mutableStateOf(false)}

    Box(Modifier.fillMaxWidth(),contentAlignment = Alignment.Center) {
        Row( modifier = Modifier
            .padding(24.dp)
            .clickable {
                expanded = !expanded
            }
            .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = cityName,fontSize = 18.sp,modifier = Modifier.padding(end = 8.dp))
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false}) {
                cityList.forEach { city ->
                    DropdownMenuItem(
                        onClick = {
                            if(filterList){
                                navigator.navigate(UsersSubListScreenByCityDestination(city))
                            }else{
                                expanded = false
                                cityName = city
                            }
                        }
                    ) {
                        Text(text = city)
                    }
                }
            }
        }
    }
}
