package dev.sindrenm.spikes.compose.nestedscroll.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MainScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            item { Header() }

            items(100) {
                ListItem(
                    text = { Text("List item #$it") }
                )
            }
        }
    }
}

@Composable
private fun Header() {
    Column {
        Box {
            CurvedBackground()

            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.onPrimary) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 20.dp),
                    onClick = {},
                ) {
                    Icon(Icons.Default.Menu, contentDescription = null)
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = AvatarTopPadding)
                    .size(AvatarSize)
                    .clip(CircleShape)
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxSize(),
                    imageVector = Icons.Outlined.AccountCircle,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
        }

        Text(
            text = "Mr. X",
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 20.dp)
                .align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
private fun CurvedBackground() {
    val curveHeight = 20.dp

    val primary = MaterialTheme.colors.primary
    val surface = MaterialTheme.colors.surface

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(AvatarTopPadding + (AvatarSize / 2) + (curveHeight / 2))
            .drawBehind {
                drawRect(primary)

                drawArc(
                    color = surface,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = true,
                    topLeft = Offset(0f, (AvatarTopPadding + (AvatarSize / 2)).toPx()),
                    size = size.copy(height = curveHeight.toPx()),
                )
            },
    )
}

private val AvatarTopPadding = 20.dp
private val AvatarSize = 72.dp
