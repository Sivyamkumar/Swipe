package com.example.swipe.uploadobjects

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swipe.R

lateinit var mapp:Map<String,String>

@Composable
fun Posting(viewModel:Modelview){

    var confirm by remember {mutableStateOf(false)}

    val context = LocalContext.current
    var product_name by remember {mutableStateOf("")}
    var tax by remember {mutableStateOf("")}
    var product_type by remember {mutableStateOf("")}
    var price by remember {mutableStateOf("")}

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 20.dp)) {
        heading()

        OutlinedTextField(
            placeholder = { Text(text = "Enter Product Name") },
            onValueChange = { it ->
                product_name = it
            },
            value = product_name,
            label = { Text("Product Name") },
            modifier = Modifier
                .padding(start=20.dp,top=20.dp,end=20.dp, bottom = 0.dp)
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color.Blue,
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
            )
        )

        OutlinedTextField(
            placeholder = { Text(text = "Enter Product Type") },
            onValueChange = { it ->
                product_type = it
            },
            value = product_type,
            label = { Text("Product Type") },
            modifier = Modifier
                .padding(start=20.dp,top=20.dp,end=20.dp, bottom = 0.dp)
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color.Blue,
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
            )
        )

        OutlinedTextField(
            placeholder = { Text(text = "Enter Price") },
            onValueChange = { it ->
                price = it
            },
            value = price,
            label = { Text("Price") },
            modifier = Modifier
                .padding(start=20.dp,top=20.dp,end=20.dp, bottom = 0.dp)
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color.Blue,
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
            )
        )

        OutlinedTextField(
            placeholder = { Text(text = "Enter Tax") },
            onValueChange = { it ->
                tax = it
            },
            value = tax,
            label = { Text("Tax") },
            modifier = Modifier
                .padding(start=20.dp,top=20.dp,end=20.dp, bottom = 0.dp)
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color.Blue,
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
            )
        )


        Button(onClick = {
            if(product_name == ""){
                Toast.makeText(context,"Invalid Product Name",Toast.LENGTH_LONG).show()
            }
            else if(product_type == ""){
                Toast.makeText(context,"Invalid Product Type",Toast.LENGTH_LONG).show()
            }
            else if(price.toString() == ""){
                Toast.makeText(context,"Invalid Price",Toast.LENGTH_LONG).show()
            }
            else if(tax.toString() == ""){
                tax = "0.0"
            }else {
                var map: Map<String,String> = mutableMapOf(
                    "product_name" to product_name,
                    "product_type" to product_type,
                    "price" to price,
                    "tax" to tax
                )
                mapp=map
                confirm=true

            }
        }
            , modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 30.dp)) {
            Text(text = "Post Data", color = Color.Black)
        }

        if(confirm){
            Confirmation(confirm, mapp,viewModel)
        }
    }
}

@Composable
fun Confirmation(showconfirm:Boolean,map: Map<String, String>,viewModel: Modelview){

    var show by remember {mutableStateOf(showconfirm)}

    var res by remember {mutableStateOf(false)}

    val context = LocalContext.current

    var product = map["product_name"]
    var type = map["product_type"]
    var price = map["price"]
    var tax = map["tax"]

    if (show) {
        AlertDialog(
            onDismissRequest = { show = false },
            title = { Text("Confirm Submission",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black)
            },
            text = {
                Column{
                    Text("Sure?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black)
                    Text("Product Name: $product",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                        , modifier = Modifier.padding(top = 10.dp)
                    )
                    Text("Product Type: $type",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                        , modifier = Modifier.padding(top = 10.dp)
                    )
                    Text("Price: $price",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                        , modifier = Modifier.padding(top = 10.dp)
                    )
                    Text("Tax: $tax",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                        , modifier = Modifier.padding(top = 10.dp)
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.postData(map)
                    show = false
                    res=true
                },colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Green)) {
                    Text("Submit",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
//                        ,modifier = Modifier.background(Color("#34eb40".toLong()))
                    )
                }
            },
            dismissButton = {
                Button(onClick = {
                    val intent = Intent(context, MainActivity2::class.java)
                    context.startActivity(intent)
                    show = false
                },
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Red)) {
                    Text("Cancel",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
            }
        )
    }

    if(res){
        Success(map = map, state = true)
    }
}

@Composable
fun Success(map: Map<String,String>,state:Boolean){

    var product = map["product_name"]
    var type = map["product_type"]
    var price = map["price"]
    var tax = map["tax"]

    var showDialog by remember {mutableStateOf(state)}

    if(showDialog){
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Success :)", color = Color.Green,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text("Product:  $product", color = Color.Black, modifier = Modifier.padding(top = 10.dp))
                    Text("Type:     $type", color = Color.Black, modifier = Modifier.padding(top = 10.dp))
                    Text("Price:    $price", color = Color.Black, modifier = Modifier.padding(top = 10.dp))
                    Text("Tax:      $tax", color = Color.Black, modifier = Modifier.padding(top = 10.dp))
                }
            },
            confirmButton = {
                TextButton(onClick = { showDialog=false }) {
                    Text("OK", color = Color.Black)
                }
            }
        )
    }
}

@Composable
fun heading(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),Arrangement.SpaceEvenly ) {
        Text(
            text = "Swipe",
            modifier = Modifier
                .padding(start = 30.dp),
            fontSize = 40.sp, fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(top = 15.dp, start = 0.dp))
    }
}

@Composable
fun ImageLoading(){
    Column {
        
    }
}

@Composable
@Preview
fun UI(){
    Posting(Modelview())
}




