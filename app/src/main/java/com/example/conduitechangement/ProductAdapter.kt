package com.example.conduitechangement

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(val context: Context, var data:List<Feed>): RecyclerView.Adapter<ViewHolderProduct>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProduct {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_layout, parent , false)
        return ViewHolderProduct(view)
    }

    override fun onBindViewHolder(holder: ViewHolderProduct, position: Int) {
        holder.apply {
            nom.text = data[position].nom
            Glide.with(context).load(url + data[position].img).into(img)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }



}


class ViewHolderProduct(val view : View) : RecyclerView.ViewHolder(view) {
    var img = view.findViewById(R.id.image) as ImageView
    var nom = view.findViewById(R.id.user) as TextView
}