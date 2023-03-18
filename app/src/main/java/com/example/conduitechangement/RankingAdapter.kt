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

class RankingAdapter(val context: Context, var data:List<Ranking>): RecyclerView.Adapter<ViewHolderRanking>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRanking {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ranking_layout, parent , false)
        return ViewHolderRanking(view)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onBindViewHolder(holder: ViewHolderRanking, position: Int) {
        val n = position + 1
        holder.apply {
            num.text = n.toString()
            nom.text = data[position].nom
            ptn.text = data[position].pnts.toString()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }



}


class ViewHolderRanking(val view : View) : RecyclerView.ViewHolder(view) {
    var num = view.findViewById(R.id.textView) as TextView
    var nom = view.findViewById(R.id.textView2) as TextView
    var ptn = view.findViewById(R.id.textView4) as TextView
}