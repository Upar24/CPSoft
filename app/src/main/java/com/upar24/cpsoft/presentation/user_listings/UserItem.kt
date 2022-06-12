package com.upar24.cpsoft.presentation.user_listings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
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
    ) {
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = user.city,
                    style = MaterialTheme.typography.body1,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "(${user.phoneNumber})",
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
    DividerItem()
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
@Composable
fun DividerItem(){
    Divider(
        color = Color.Blue,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .width(1.dp)
    )
}
