package com.example.hammerapp.domain.adapter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hammerapp.R
import com.example.hammerapp.data.PromoFirebaseData
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

class PromotionsRecyclerAdapter(_list: List<PromoFirebaseData>) : RecyclerView.Adapter<PromotionsRecyclerAdapter.PromotionsAdapter>() {

    var list = _list


    class PromotionsAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(promo: String){
            var photo = itemView.findViewById<ImageView>(R.id.promo_img)
            val radius = 5
            val margin = 5
            Picasso.get ().load(promo).into(photo);

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionsAdapter {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.promotions_recycler_item, parent, false)
        return PromotionsAdapter(view)
    }

    override fun onBindViewHolder(holder: PromotionsAdapter, position: Int) {
        holder.bind(list[position].photoUri)
    }

    override fun getItemCount(): Int {
      return list.size
    }

}