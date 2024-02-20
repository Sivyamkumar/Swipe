package com.example.swipe.GetObjects

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.swipe.R
import com.example.swipe.uploadobjects.MainActivity2

//import com.example.swipe.uploadobjects.MainActivity


//How the screen looks like when you load the data from server to UI.
@SuppressLint("SuspiciousIndentation")
@Composable
fun ModelScreen(){

    val recipeViewModel : MainViewModel = viewModel()
    val viewstate by recipeViewModel.ModelState

        Box(modifier = Modifier.fillMaxSize()){
            when{
                viewstate.loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                viewstate.error != null ->{
                    Text(text = "ERROR OCCURRED")
                }
                else -> {
                    CategoryScreen(
                        categories = viewstate.list)
                }
            }

    }
}

// How the group of Data(s) should look like.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(categories:List<ModelItem>){

    val context = LocalContext.current
    var isTextFieldOpen by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }

    val filteredCategories = if (textFieldValue.isEmpty()) {
        categories
    } else {
        categories.filter { it.product_name.contains(textFieldValue, ignoreCase = true) }
    }

    LazyVerticalGrid(GridCells.Fixed(2),modifier = Modifier.fillMaxSize()){
        items(filteredCategories){
                category ->
            CategoryItem(category = category)
        }
    }

//    Searching object in the Data(s).
    Button(
        onClick = {isTextFieldOpen = !isTextFieldOpen},
        colors =  ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = Modifier
            .absoluteOffset(x = 270.dp, y = 600.dp)
            .height(100.dp)
            .width(100.dp),
        content = {
            Image(
                painter = painterResource(id = R.drawable.img_2),
                contentDescription = "Search button",
            )
        }
    )

    if (isTextFieldOpen) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            placeholder = { Text("Enter text" , color = Color.White)},
            modifier = Modifier
                .width(200.dp)
                .padding(horizontal = 8.dp)
                .absoluteOffset(x = 100.dp, y = 625.dp)
                .background(Color.Gray), maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
            )
        )
    }


//Navigating to the screen where one can add their own data.
    Button(
        onClick = {
            val intent = Intent(context, MainActivity2::class.java)
            context.startActivity(intent)
        },
        colors =  ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = Modifier
            .absoluteOffset(x = 270.dp, y = 670.dp)
            .height(100.dp)
            .width(100.dp),
        content = {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Image button",
            )
        }
    )

}

//how each items looks like
@Composable
fun CategoryItem(category: ModelItem){
    Column(modifier = Modifier
        .padding(8.dp)
        .wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            if(!category.image.isNullOrEmpty()){
                rememberAsyncImagePainter(model = category.image)
            }else{
                rememberAsyncImagePainter(model = R.drawable.image)
            }
            ,contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .width(100.dp)
                .aspectRatio(1f)
        )

        Row{
            Text(text = "Product: ",
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.ExtraBold),
                modifier =Modifier.padding(top=4.dp)
            )
            Text(text = (category.product_name),
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(top=4.dp)
            )
        }
        Row{
            Text(text = "Price: ₹",
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.ExtraBold),
                modifier =Modifier.padding(top=4.dp)
            )
            Text(text = category.price.toString(),
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.SemiBold),
                modifier =Modifier.padding(top=4.dp)
            )
        }
        Row{
            Text(text = "TAX: ₹",
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.ExtraBold),
                modifier =Modifier.padding(top=4.dp)
            )
            Text(text = category.tax.toString(),
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.SemiBold),
                modifier =Modifier.padding(top=4.dp)
            )
        }
    }
}


