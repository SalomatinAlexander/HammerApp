package com.example.hammerapp.domain.adapter.loaders

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.AsyncTaskLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.RecyclerView
import com.example.hammerapp.data.FoodFirebaseData
import com.example.hammerapp.data.PromoFirebaseData
import com.example.hammerapp.domain.adapter.adapters.FoodRecyclerAdapter
import com.example.hammerapp.domain.adapter.adapters.PromotionsRecyclerAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class PromoLoader(_context: Context, _recyclerView: RecyclerView) :
    LoaderManager.LoaderCallbacks<ArrayList<PromoFirebaseData>> {
    var context = _context
    var recycler = _recyclerView
    override fun onCreateLoader(
        id: Int,
        args: Bundle?
    ): AsyncTaskLoader<ArrayList<PromoFirebaseData>> {
        return AsyncPromoLoader(context)
    }

    override fun onLoadFinished(
        loader: Loader<ArrayList<PromoFirebaseData>>,
        data: ArrayList<PromoFirebaseData>?
    ) {
        if (!data.isNullOrEmpty()) {

            var adapter = PromotionsRecyclerAdapter(data!!)
            recycler.adapter = adapter

        } else {
        }

    }

    override fun onLoaderReset(loader: Loader<ArrayList<PromoFirebaseData>>) {

    }

}

class AsyncPromoLoader(context: Context) : AsyncTaskLoader<ArrayList<PromoFirebaseData>>(context) {

    override fun loadInBackground(): ArrayList<PromoFirebaseData> {
        var mRootRef = FirebaseDatabase.getInstance("https://hammerapp-63f37-default-rtdb.europe-west1.firebasedatabase.app/")
            .reference;
        var mCategoryRef = mRootRef.child("data").child("Promotions")
        mCategoryRef.addValueEventListener(object : ValueEventListener {
            var values: ArrayList<PromoFirebaseData> = ArrayList()
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val c: PromoFirebaseData = snapshot.getValue(PromoFirebaseData::class.java)!!
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

    override fun deliverResult(data: ArrayList<PromoFirebaseData>?) {
        super.deliverResult(data)
    }

    override fun onForceLoad() {
        super.onForceLoad()
    }

}


