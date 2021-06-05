package com.example.hammerapp.domain.adapter.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hammerapp.R
import com.example.hammerapp.data.FoodFirebaseData
import com.squareup.picasso.Picasso


class FoodRecyclerAdapter(_list: ArrayList<FoodFirebaseData>) : RecyclerView.Adapter<FoodRecyclerAdapter.FoodAdapter>() {

    var list = _list


    class FoodAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: FoodFirebaseData){
            var txtTitle = itemView.findViewById<TextView>(R.id.food_txt_title)
            var txtDesc = itemView.findViewById<TextView>(R.id.food_txt_desc)
            var txtPrice = itemView.findViewById<TextView>(R.id.food_txt_price)
            var photo = itemView.findViewById<ImageView>(R.id.food_img)
            txtTitle.setText(data.TextTitle)
            txtDesc.setText(data.TextDesc)
            txtPrice.setText(data.Price)
            Picasso.get ().load(data.photoUri).into(photo);


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_recycler_item, parent, false)
        return FoodAdapter(view)
    }

    override fun onBindViewHolder(holder: FoodAdapter, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
      return list.size
    }

}