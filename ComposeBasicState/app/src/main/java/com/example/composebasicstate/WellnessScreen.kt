package com.example.composebasicstate

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasicstate.ui.theme.ComposeBasicStateTheme

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        StatefulCounter()

        val list = remember { getWellnessTasks().toMutableStateList() }
        WellnessTasksList(list = list, onCloseTask = { task -> list.remove(task) })
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun WellnessScreenPreview() {
    ComposeBasicStateTheme {
        WellnessScreen()
    }
}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }