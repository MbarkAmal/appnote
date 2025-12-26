package com.example.appnote.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appnote.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onActionClick: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo1), // your logo
                    contentDescription = "App Logo",
                    modifier = Modifier.size(50.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "レイジーノート",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp
                    )
                )

            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Settings"
                )
            }
        }
    )
}
