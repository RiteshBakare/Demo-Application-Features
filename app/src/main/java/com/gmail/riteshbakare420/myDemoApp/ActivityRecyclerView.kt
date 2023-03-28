package com.gmail.riteshbakare420.myDemoApp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.riteshbakare420.myDemoApp.RecyclerView.Food
import com.gmail.riteshbakare420.myDemoApp.RecyclerView.FoodAdapter

class ActivityRecyclerView : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList : ArrayList<Food>
    private lateinit var foodAdapter: FoodAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        recyclerView = findViewById(R.id.myRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        foodList = ArrayList()

        foodList.add(Food(R.drawable.pizza,"Pizza"))
        foodList.add(Food(R.drawable.burger,"Burger"))
        foodList.add(Food(R.drawable.pasta,"Pasta"))
        foodList.add(Food(R.drawable.fries,"French Fries"))
        foodList.add(Food(R.drawable.sandwithc,"sandWitch"))
        foodList.add(Food(R.drawable.coffe,"Coffee"))
        foodList.add(Food(R.drawable.pizza,"Pizza"))
        foodList.add(Food(R.drawable.burger,"Burger"))
        foodList.add(Food(R.drawable.pasta,"Pasta"))
        foodList.add(Food(R.drawable.fries,"French Fries"))
        foodList.add(Food(R.drawable.sandwithc,"sandWitch"))
        foodList.add(Food(R.drawable.coffe,"Coffee"))
        foodList.add(Food(R.drawable.pizza,"Pizza"))
        foodList.add(Food(R.drawable.burger,"Burger"))
        foodList.add(Food(R.drawable.pasta,"Pasta"))
        foodList.add(Food(R.drawable.fries,"French Fries"))
        foodList.add(Food(R.drawable.sandwithc,"sandWitch"))
        foodList.add(Food(R.drawable.coffe,"Coffee"))


        foodAdapter = FoodAdapter(foodList)
        recyclerView.adapter = foodAdapter

    }


}