package com.example.appnote.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appnote.ui.component.BottomBar
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.BrightnessMedium
import com.example.appnote.ui.component.TopBar
import com.example.appnote.ui.viewmodel.NoteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    var darkModeEnabled by remember { mutableStateOf(false) }
    //var favoriteEnabled by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopBar(

                onActionClick = {
                    // Navigate to settings or profile
                }
            )
        },
        bottomBar = {
            BottomBar(navController) // ⬅ call it here
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Security
            item {
                SettingsItem(
                    icon = Icons.Default.Lock,
                    title = "Security",
                    subtitle = "Manage your password and permissions",
                    onClick = {
                        // Navigate to Security page
                        //navController.navigate("security")
                    }
                )
            }

            // Favorites
            item {
                SettingsItem(
                    icon = Icons.Default.Favorite,
                    title = "Favorites",
                    subtitle = "Manage favorite notes",

                    onClick = {}
                )
            }

            // Dark Mode
            item {
                SettingsItem(
                    icon = Icons.Default.DarkMode ,
                            title = "Dark Mode",
                    subtitle = "Enable dark theme",
                    trailing = {
                        Switch(
                            checked = darkModeEnabled,
                            onCheckedChange = { darkModeEnabled = it }
                        )
                    },
                    onClick = {}
                )
            }

            // Favorites
            item {
                SettingsItem(
                    icon = Icons.Default.AccountCircle,
                    title = "Log out",
                    subtitle = "Sign out from your account",
                    onClick = {
                        // handle logout here
                    }
                )
            }

        }
    }
}

@Composable
fun SettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String? = null,
    trailing: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(28.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            if (subtitle != null) {
                Text(text = subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        if (trailing != null) {
            trailing()
        }
    }
}

