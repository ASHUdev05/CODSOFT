package com.ashudev05.todo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ashudev05.todo.ui.theme.AppTypography

@Composable
fun TableRow(
    label: String,
    modifier: Modifier = Modifier,
    color: Color,
    hasArrow: Boolean = false,
    isDestructive: Boolean = false,
    detail: (@Composable RowScope.() -> Unit)? = null,
) {
    val textColor = if (isDestructive) MaterialTheme.colorScheme.error else color

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = AppTypography.bodyMedium,
            color = textColor,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        detail?.invoke(this)
    }
}