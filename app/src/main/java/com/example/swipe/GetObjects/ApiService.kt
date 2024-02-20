// Establishing Connection with the API for fetching data(s).

import com.example.swipe.GetObjects.ModelItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val _url = "https://app.getswipe.in/api/public/"

private val retrofit = Retrofit.Builder().baseUrl(_url)
    .addConverterFactory(GsonConverterFactory.create()).build()

val ModelServices = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("get")
    suspend fun ModelResponse() : ArrayList<ModelItem>

 }



//                  FUNCTION TO CHECK SUCCESSFUL OR UNSUCCESSFULL CONNECTION
//suspend fun main(){
//    try{
//        val response = ModelServices.ModelResponse()
//        for (x in response){
//            println("Name: ${x.product_name} Price: ${x.price} Tax: ${x.tax} ")
//        }
//    }catch (e:Error){
//        println(e.message)
//    }
//}

