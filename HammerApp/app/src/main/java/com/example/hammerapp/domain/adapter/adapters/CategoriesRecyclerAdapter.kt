package com.example.hammerapp.domain.adapter.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hammerapp.R

class CategoriesRecyclerAdapter(_list:List<String>) : RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoriesAdapter>() {

    var list = _list


    class CategoriesAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: String) {
            var btn = itemView.findViewById<TextView>(R.id.categories_button)
            btn.setText(get)


            if(get.equals("Пицца")) {
                btn.setTextColor(itemView.resources.getColor(R.color.pink))
                btn.setBackgroundColor(itemView.resources.getColor(R.color.btn_selected_pink))
                btn.background = itemView.resources.getDrawable(R.drawable.categories_background_drawable_selected)

            }else{
                btn.background = itemView.resources.getDrawable(R.drawable.categories_background_drawable)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_recycler_item, parent, false)
        return CategoriesAdapter(view)
    }

    override fun onBindViewHolder(holder: CategoriesAdapter, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
      return list.size
    }

}