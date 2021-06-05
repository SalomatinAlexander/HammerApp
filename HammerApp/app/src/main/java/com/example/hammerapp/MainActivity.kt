package com.example.hammerapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hammerapp.domain.adapter.adapters.CategoriesRecyclerAdapter
import com.example.hammerapp.domain.adapter.adapters.PromotionsRecyclerAdapter
import com.example.hammerapp.domain.adapter.loaders.FoodLoader
import com.example.hammerapp.domain.adapter.loaders.PromoLoader


class MainActivity : AppCompatActivity() {
    lateinit var recycler_food: RecyclerView
    lateinit var recycler_categories: RecyclerView
    lateinit var recycler_promotions: RecyclerView
    lateinit var  categoryList:ArrayList<String>
    val REQUEST_CODE_PERMISSION_READ = 0
    val REQUEST_CODE_PERMISSION_WRITE = 1
    val REQUEST_CODE_PERMISSION_LOCATION = 2
    val REQUEST_CODE_PERMISSION_LOCATION_COARSE = 3
    val LOADER_FOOD_ID = 1
    val LOADER_PROMO_ID =2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPermisson()
        init()

        var bundle = Bundle()
        recycler_categories.adapter = CategoriesRecyclerAdapter(categoryList)
        recycler_categories.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        recycler_promotions.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.HORIZONTAL, false
        )
        recycler_food = findViewById(R.id.recycler_view_food)
        recycler_food.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        supportLoaderManager.initLoader(LOADER_FOOD_ID, bundle, FoodLoader(this, recycler_food))
        supportLoaderManager.initLoader(
            LOADER_PROMO_ID,
            bundle,
            PromoLoader(this, recycler_promotions)
        )
    }
    fun getPermisson(){
        val permissionRead =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val permissionWrite =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val permissionLocation =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionLocationCoarse =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (permissionRead == PackageManager.PERMISSION_GRANTED
            && permissionWrite == PackageManager.PERMISSION_GRANTED
            && permissionLocation == PackageManager.PERMISSION_GRANTED
            && permissionLocationCoarse == PackageManager.PERMISSION_GRANTED
        ) {
        } else {

            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE_PERMISSION_READ
            )
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE_PERMISSION_WRITE
            )
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_PERMISSION_LOCATION
            )
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_CODE_PERMISSION_LOCATION_COARSE
            )

        }

    }
    fun init(){
        categoryList = arrayListOf<String>("Пицца", "Комбо", "Десерты", "Напитки")
        recycler_categories = findViewById(R.id.categories_recycler_id)
        recycler_promotions = findViewById(R.id.promo_recycler_id)

    }
        }


