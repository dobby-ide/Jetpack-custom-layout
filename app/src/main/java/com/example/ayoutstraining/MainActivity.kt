package com.example.ayoutstraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import com.example.ayoutstraining.ui.theme.AyoutsTrainingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AyoutsTrainingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun MyCustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
    ) {
    Layout(
        modifier = modifier,
        content = content

    ) {
        measurables:List<Measurable>,//list of all elements included in the layout
            constraints: Constraints ->
        val placeables = measurables.map{measurable->
            measurable.measure(constraints)
        }
        val height = placeables.sumOf{it.height}
        val width = placeables.maxOf{it.width*2}
        layout(width,height){
            var y = 0
            var number = 0

            placeables.forEach{
                placeable ->
                var placeableOdd= placeables[number]
                if(placeable===placeableOdd){
                    placeable.placeRelative(x = 0,y = y)
                    number+=2
                    y += placeable.height
                }else{
                    placeable.placeRelative(x = 120,y = y)
                    y += placeable.height
                }


            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyCustomLayout() {
        Text(text = "HELLO WORLD")
        Text(text = "HELLO WORLD")
        Text(text = "HELLO WORLD")
        Text(text = "HELLO WORLD")
        Text(text = "HELLO WORLD")
    }
}