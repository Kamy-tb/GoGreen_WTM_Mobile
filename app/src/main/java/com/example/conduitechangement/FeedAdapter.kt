package com.example.conduitechangement

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.OutputStream
import java.util.*

class FeedAdapter(val context: Context, var data:List<Feed>): RecyclerView.Adapter<ViewHolderFeed>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFeed {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_layout, parent , false)
        return ViewHolderFeed(view)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onBindViewHolder(holder: ViewHolderFeed, position: Int) {
        holder.apply {
            nom.text = data[position].nom
            Glide.with(context).load(url + data[position].img).into(img)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }



}


class ViewHolderFeed(val view : View) : RecyclerView.ViewHolder(view) {
    var img = view.findViewById(R.id.image) as ImageView
    var nom = view.findViewById(R.id.user) as TextView
}