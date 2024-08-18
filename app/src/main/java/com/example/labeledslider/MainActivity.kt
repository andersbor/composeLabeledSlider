package com.example.labeledslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.labeledslider.ui.theme.LabeledSliderTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class) // TopAppBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabeledSliderTheme {
                Scaffold(
                    //modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = { Text(text = "Labeled Slider") })
                    }
                )
                { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        var sliderPosition by remember { mutableFloatStateOf(0f) }
                        LabeledSlider(
                            label = "Bottom",
                            location = Location.RIGHT,
                            value = sliderPosition,
                            onValueChange = { sliderPosition = it }
                        )
                        Text(text = "Position $sliderPosition")
                    }
                }
            }
        }
    }
}

enum class Location {
    TOP, BOTTOM, LEFT, RIGHT
}

@Composable
fun LabeledSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    label: String,
    location: Location = Location.TOP,
    modifier: Modifier = Modifier
) {
    if (location == Location.TOP || location == Location.BOTTOM) {
        Column(modifier = modifier) {
            if (location == Location.TOP) {
                Text(text = label)
                Slider(value = value, onValueChange = onValueChange)
            } else {
                Slider(value = value, onValueChange = onValueChange)
                Text(text = label)
            }
        }
    } else {
        Row(
            modifier = modifier, verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (location == Location.LEFT) {
                Text(text = label)
                Slider(value = value, onValueChange = onValueChange)
            } else {
                // https://github.com/JetBrains/compose-multiplatform/issues/1765
                Slider(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.weight(1f)
                )
                Text(text = label)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LabeledSliderPreviewTop() {
    LabeledSliderTheme {
        LabeledSlider(
            value = 0.5f,
            onValueChange = {},
            label = "Top",
            location = Location.TOP
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LabeledSliderPreviewBottom() {
    LabeledSliderTheme {
        LabeledSlider(
            value = 0.5f,
            onValueChange = {},
            label = "Bottom",
            location = Location.BOTTOM
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LabeledSliderPreviewLeft() {
    LabeledSliderTheme {
        LabeledSlider(
            value = 0.5f,
            onValueChange = {},
            label = "Left",
            location = Location.LEFT
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LabeledSliderPreviewRight() {
    LabeledSliderTheme {
        LabeledSlider(
            value = 0.5f,
            onValueChange = {},
            label = "Right",
            location = Location.RIGHT
        )
    }
}