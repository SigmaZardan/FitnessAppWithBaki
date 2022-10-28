package com.bibek.thirtydaysofexercise

import ThirtyDaysOfExerciseTheme
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bibek.thirtydaysofexercise.data.exercises
import com.bibek.thirtydaysofexercise.model.Exercise


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirtyDaysOfExerciseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FitnessApp()
                }
            }
        }
    }
}

@Composable
fun FitnessApp(modifier : Modifier = Modifier){
    Scaffold(
   topBar =  { FitnessAppBar() }

    ) {

        LazyColumn(
            modifier = modifier.background(MaterialTheme.colors.background)  ){
            items(exercises){
                FitnessItem(exercise = it)

            }


        }


    }



}

@Composable
fun FitnessItem(exercise : Exercise,modifier :  Modifier = Modifier ) {

    var touched by remember {mutableStateOf(false)}
    Card(
        modifier = modifier
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 8.dp),
        elevation = 2.dp
    ) {

        Column(
            modifier = modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
                )

        ) {

            Text(
                text = " Day ${exercise.day}",
                style = MaterialTheme.typography.h1

            )
            FitnessTitleImageAndDescription(
                exercise.titleRes,
                exercise.imageRes,
                exercise.descriptionRes ,
                touched = touched ,
                onClick =  { touched = !touched}
            )


        }
    }


}

@Composable
fun FitnessTitleImageAndDescription(
    @StringRes title : Int,
    @DrawableRes image : Int,
    @StringRes description : Int ,
    touched : Boolean ,
    onClick : () -> Unit ,

    modifier : Modifier = Modifier
) {

    Column() {
    
        Text(
            text = stringResource(title) ,
            style = MaterialTheme.typography.h2,
            modifier = modifier.padding(start = 9.dp)
        )

        IconButton(onClick = onClick) {
            Image(
                painter = painterResource(id = image),
                modifier = modifier
                    .padding(start = 9.dp)
                    .fillMaxWidth(),
                contentDescription = null ,
            )
        }

        if(touched) {

            Text(
                text = stringResource(description) ,
                modifier = modifier.padding(start = 9.dp),


                )

        }

    }

}

@Composable
fun FitnessAppBar(modifier : Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background) ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(64.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(50)),
            painter = painterResource(R.drawable.baki_1),
            contentScale = ContentScale.Crop,

            contentDescription = null
        )
        Text(
            text = stringResource(R.string.app_name) ,
            style = MaterialTheme.typography.h1
        )
    }
}












@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThirtyDaysOfExerciseTheme {

            FitnessApp()

    }
}