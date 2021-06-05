package com.example.hammerapp.domain.adapter.loaders

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.AsyncTaskLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.RecyclerView
import com.example.hammerapp.data.FoodFirebaseData
import com.example.hammerapp.domain.adapter.adapters.FoodRecyclerAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


    class FoodLoader(_context: Context, _recyclerView: RecyclerView) :
        LoaderManager.LoaderCallbacks<ArrayList<FoodFirebaseData>> {
        var context = _context
        var recycler = _recyclerView
        override fun onCreateLoader(
            id: Int,
            args: Bundle?
        ): AsyncTaskLoader<ArrayList<FoodFirebaseData>> {
            return AsyncFoodLoader(context)
        }

        override fun onLoadFinished(
            loader: Loader<ArrayList<FoodFirebaseData>>,
            data: ArrayList<FoodFirebaseData>?
        ) {
            if (!data.isNullOrEmpty()) {

                var adapter = FoodRecyclerAdapter(data!!)
                recycler.adapter = adapter

            } else {
            }

        }

        override fun onLoaderReset(loader: Loader<ArrayList<FoodFirebaseData>>) {

        }

    }

    class AsyncFoodLoader(context: Context) : AsyncTaskLoader<ArrayList<FoodFirebaseData>>(context) {

        override fun loadInBackground(): ArrayList<FoodFirebaseData> {
            var mRootRef = FirebaseDatabase.getInstance("https://hammerapp-63f37-default-rtdb.europe-west1.firebasedatabase.app/")
                .reference;
            var mCategoryRef = mRootRef.child("data").child("Food")
            mCategoryRef.addValueEventListener(object : ValueEventListener {
                //  print
                var values: ArrayList<FoodFirebaseData> = ArrayList()
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        val c: FoodFirebaseData = snapshot.getValue(FoodFirebaseData::class.java)!!
                        values.add(c)
                    }
                    deliverResult(values)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("EERORR")
                }

            })


            return ArrayList()!!
        }

        override fun onStartLoading() {
            super.onStartLoading()
            forceLoad()
        }

        override fun onStopLoading() {
            super.onStopLoading()
        }

        override fun deliverResult(data: ArrayList<FoodFirebaseData>?) {
            super.deliverResult(data)
        }

        override fun onForceLoad() {
            super.onForceLoad()
        }

    }
