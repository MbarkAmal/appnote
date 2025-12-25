package com.example.appnote.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.android.datatransport.Event



@Composable
fun BottomBar(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        tonalElevation = 6.dp,
        containerColor = MaterialTheme.colorScheme.surface
    ) {

        NavigationBarItem(
            selected = currentRoute == "calendar",
            onClick = {
                if (currentRoute != "calendar") {
                    navController.navigate("calendar")
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.DateRange, //
                    contentDescription = "Calendar",
                    modifier = Modifier.size(22.dp)
                )
            },
            label = { Text("Calendar", fontSize = 11.sp) }
        )


        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = {
                if (currentRoute != "home") {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp)      // center slightly bigger
                )
            },
            label = { Text("Home", fontSize = 12.sp) }
        )

        NavigationBarItem(
            selected = currentRoute == "settings",
            onClick = {
                if (currentRoute != "settings") {
                    navController.navigate("settings")
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier.size(22.dp)
                )
            },
            label = { Text("Settings", fontSize = 11.sp) }
        )
    }
}
