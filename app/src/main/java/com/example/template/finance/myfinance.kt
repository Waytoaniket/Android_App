package com.example.template.finance

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.core.view.get
import androidx.core.view.marginStart
import com.example.template.APICalls
import com.example.template.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

import kotlinx.android.synthetic.main.updation_income.view.*
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.activity_finance.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.*
import kotlin.collections.ArrayList


class myfinance : AppCompatActivity() {
    lateinit var options: Spinner
    lateinit var options1: Spinner
    lateinit var option2: Spinner
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finance)
        supportActionBar?.title="Category"

//        this is for button effect on button-all when it enter in
        button_all.setBackgroundResource(R.drawable.button_left_on_click)
        val textView=findViewById<TextView>(button_all.id)
        textView.setTextColor(Color.parseColor("#ffffff"))

//        this to get today date
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())




        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val myresponse=APICalls().get("http://192.168.43.149:8000/cbo/payments/")
        Log.d("response11",myresponse)
        val data=JSONObject(myresponse)
        Log.d("response","${data.getString("data")}")
        val mydata = data.optJSONArray("data")?.let { 0.until(it.length()).map { i->it.optJSONObject(i)}}
            Log.d("response","$mydata")
//


        val adapter= GroupAdapter<GroupieViewHolder>()
        recycle_for_updation_of_income.adapter=adapter
        addALLitem(adapter,mydata)



        adapter.setOnItemClickListener { item, view ->
            val intent=Intent(view.context, Edit::class.java)
            startActivity(intent)
        }



        button_all.setOnClickListener {
            it.setBackgroundResource(R.drawable.button_left_on_click)
            val textView=findViewById<TextView>(button_all.id)
            textView.setTextColor(Color.parseColor("#ffffff"))
            val textView2=findViewById<TextView>(button_monthly.id)
            textView2.setTextColor(Color.parseColor("#009788"))
            val textView3=findViewById<TextView>(button_weekly.id)
            textView3.setTextColor(Color.parseColor("#009788"))
            val textView4=findViewById<TextView>(button_yearly.id)
            textView4.setTextColor(Color.parseColor("#009788"))
            button_monthly.setBackgroundResource(R.drawable.button_effect)
            button_weekly.setBackgroundResource(R.drawable.button_effect)
            button_yearly.setBackgroundResource(R.drawable.button_right_effect)
            adapter.clear()
            addALLitem(adapter,mydata)
            val layout = layout_handel as LinearLayout
            layout.removeAllViews()


        }
        button_weekly.setOnClickListener {
            it.setBackgroundResource(R.drawable.button_effect_on_click)
            val textView=findViewById<TextView>(button_weekly.id)
            textView.setTextColor(Color.parseColor("#ffffff"))
            val textView2=findViewById<TextView>(button_monthly.id)
            textView2.setTextColor(Color.parseColor("#009788"))
            val textView3=findViewById<TextView>(button_all.id)
            textView3.setTextColor(Color.parseColor("#009788"))
            val textView4=findViewById<TextView>(button_yearly.id)
            textView4.setTextColor(Color.parseColor("#009788"))
            button_all.setBackgroundResource(R.drawable.button_left_effect)
            button_monthly.setBackgroundResource(R.drawable.button_effect)
            button_yearly.setBackgroundResource(R.drawable.button_right_effect)
            adapter.clear()
            addTodayitem(adapter,mydata,currentDate)
            val layout = layout_handel as LinearLayout
            layout.removeAllViews()

        }
        button_monthly.setOnClickListener {
            it.setBackgroundResource(R.drawable.button_effect_on_click)
            val textView=findViewById<TextView>(button_monthly.id)
            textView.setTextColor(Color.parseColor("#ffffff"))
            val textView2=findViewById<TextView>(button_all.id)
            textView2.setTextColor(Color.parseColor("#009788"))
            val textView3=findViewById<TextView>(button_weekly.id)
            textView3.setTextColor(Color.parseColor("#009788"))
            val textView4=findViewById<TextView>(button_yearly.id)
            textView4.setTextColor(Color.parseColor("#009788"))
            button_all.setBackgroundResource(R.drawable.button_left_effect)
            button_yearly.setBackgroundResource(R.drawable.button_right_effect)
            button_weekly.setBackgroundResource(R.drawable.button_effect)
//            layout_handel.dispatchSystemUiVisibilityChanged(R.layout.spinner_of_month)
            val layout = layout_handel as LinearLayout
            val child = layoutInflater.inflate(R.layout.activity_spinner_of_month,null)
            layout.removeAllViews()
            layout.addView(child)
//            options = findViewById(R.id.Spinner_of_month)
//
//            val option = listOf("1","2","3","4","5","6","7","8")
//            options.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,option)
////
            options =findViewById(R.id.Spinner_of_month)
            options1 = findViewById(R.id.Spinner_of_year)
            val options_of_month = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
            options.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options_of_month)
            options.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Toast.makeText(applicationContext,""+options_of_month.get(position),Toast.LENGTH_SHORT).show()
                    get_month_and_year(position)

                }
            }


            val options_of_year = Year_List(mydata)
            options1.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options_of_year)



        }

        button_yearly.setOnClickListener {
            it.setBackgroundResource(R.drawable.button_right_effect_on_click)
            val textView=findViewById<TextView>(button_yearly.id)
            textView.setTextColor(Color.parseColor("#ffffff"))
            val textView2=findViewById<TextView>(button_monthly.id)
            textView2.setTextColor(Color.parseColor("#009788"))
            val textView3=findViewById<TextView>(button_weekly.id)
            textView3.setTextColor(Color.parseColor("#009788"))
            val textView4=findViewById<TextView>(button_all.id)
            textView4.setTextColor(Color.parseColor("#009788"))
            button_all.setBackgroundResource(R.drawable.button_left_effect)
            button_monthly.setBackgroundResource(R.drawable.button_effect)
            button_weekly.setBackgroundResource(R.drawable.button_effect)

            val layout = layout_handel as LinearLayout
            val child = layoutInflater.inflate(R.layout.spinner_of_year, null)
            layout.removeAllViews()
            layout.addView(child)
            option2 =findViewById(R.id.Spinner_of_year1)
            val options_of_year = Year_List(mydata)
            option2.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options_of_year)


        }
        Addition_of_income.setOnClickListener {
            Log.d("Newmessage","done")
            val intent =Intent(this, addition_of_income::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.nav_bar,menu)

        return super.onCreateOptionsMenu(menu)
    }


}
class UserItem(val Family_Id:Int,val username:String,val Money:Double,val Payment_Method:String): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        Log.d("NewMessage", "enter into user")
        viewHolder.itemView.Family_Id.text = Family_Id.toString()
        viewHolder.itemView.Amount_Paid.text = Money.toString()
        viewHolder.itemView.Payment_Method.text = Payment_Method
        viewHolder.itemView.Username.text = username

        Log.d("NewMessage", "")


    }

    override fun getLayout(): Int {
        return R.layout.updation_income
    }

}

 fun addALLitem( adapter:GroupAdapter<GroupieViewHolder>,data:List<JSONObject>?){

    for (i in 0..(data!!.size-1)) {
         adapter.add(UserItem(data[i]["Family_id"] as Int, data[i]["fname"] as String, data[i]["Amount"] as Double, "Cash"))

     }
}
fun addTodayitem( adapter:GroupAdapter<GroupieViewHolder>,data:List<JSONObject>?,currentDate:String){

    for (i in 0..(data!!.size-1)) {
        val str = data[i]["timestamp"].toString()
        val separate1 = str.split("\\s".toRegex())[0]
        if (separate1==currentDate){
            adapter.add(UserItem(data[i]["Family_id"] as Int, data[i]["fname"] as String, data[i]["Amount"] as Double, "Cash"))
        }

    }
}
fun Year_List(data:List<JSONObject>?):List<String>{
    val list_of_year1= arrayListOf<String>()
    for (i in 0..(data!!.size-1)) {
        val str = data[i]["timestamp"].toString()
        val separate1 = str.split("\\s".toRegex())[0]
        Log.d("separate2",separate1[0].toString()+separate1[1]+separate1[2]+separate1[3])
        list_of_year1.add(separate1[0].toString()+separate1[1]+separate1[2]+separate1[3])
    }
    Log.d("separate5",list_of_year1.distinct().toString())
    return list_of_year1.distinct()
}
class get_month_and_year{

    constructor(month: Int){

    }
    constructor(year: String){

    }
}

