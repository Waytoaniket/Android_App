package com.example.template

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.template.finance.myfinance
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        finance_button.setOnClickListener {
            val intent= Intent(this, myfinance::class.java)
            startActivity(intent)
        }


    }
}
public class APICalls {

    var return_response = ""

    public fun get(url: String) : String {


        // Creating Client
        var client = OkHttpClient()

        // Creating request
        var request = Request.Builder()
            .url(url)
            .build()

        Log.d("request","request_build")

        // Setting the connection
//        client.newCall(request).enqueue(
//            object : Callback {
//
//                override fun onFailure(call: Call, e: IOException) {
//                    return_response  = "Error"
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    try {
//                        val json = JSONObject(response.body()!!.toString())
//                        Log.d("JSON", json.toString())
//                        return_response = json.toString()
//
//                    }catch( e : Exception){
//                        return_response  = "Error"
//                    }
//                }
//
//
//            }
//        )

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Error")
            try {
                return response.body()!!.string()
            } catch (e: Exception) {
                Log.d("Error", e.toString())
                return "Error"}
        }
    }
    fun readjson(j: String){
        var json = JSONObject(j.toString())
        Log.d("JSON R" , json.getString("feedback"))

    }
}
public fun post(url: String, json : String) : String {
    var client = OkHttpClient()

    // Post body
    val mediaType = MediaType.parse("application/json; charset=utf-8")
    val body = RequestBody.create(mediaType, json)
    Log.d("hello",mediaType.toString())

    // Creating request
    var request = Request.Builder()
        .url(url).post(body)
        .build()

    Log.d("request","request_build")

    client.newCall(request).execute().use { response ->
        Log.d("hello",response.body()!!.string())
        if (!response.isSuccessful) throw IOException("Error1")
        try {
            return response.body()!!.string()
        } catch (e: Exception) {
            Log.d("Error", e.toString())
            return "Error"
        }

    }
}
public fun put(url: String, json : String) : String {
    var client = OkHttpClient()

    // Post body
    val mediaType = MediaType.parse("application/json; charset=utf-8")
    val body = RequestBody.create(mediaType, json)
    Log.d("hello",mediaType.toString())

    // Creating request
    var request = Request.Builder()
        .url(url).put(body)
        .build()

    Log.d("request","request_build")

    client.newCall(request).execute().use { response ->
        Log.d("hello",response.body()!!.string())
        if (!response.isSuccessful) throw IOException("Error1")
        try {
            return response.body()!!.string()
        } catch (e: Exception) {
            Log.d("Error", e.toString())
            return "Error"
        }

    }
}
public fun delete(url: String, json : String) : String {
    var client = OkHttpClient()

    // Post body
    val mediaType = MediaType.parse("application/json; charset=utf-8")
    val body = RequestBody.create(mediaType, json)
    Log.d("hello",mediaType.toString())

    // Creating request
    var request = Request.Builder()
        .url(url).delete(body)
        .build()

    Log.d("request","request_build")

    client.newCall(request).execute().use { response ->
        Log.d("hello",response.body()!!.string())
        if (!response.isSuccessful) throw IOException("Error1")
        try {
            return response.body()!!.string()
        } catch (e: Exception) {
            Log.d("Error", e.toString())
            return "Error"
        }

    }
}
