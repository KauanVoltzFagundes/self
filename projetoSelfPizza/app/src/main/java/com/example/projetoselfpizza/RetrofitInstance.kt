import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Item(val id: Int, val url: String)

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<Item>>
}


object RetrofitInstance {
    private const val BASE_URL = "http://192.168.1.18:3000/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
