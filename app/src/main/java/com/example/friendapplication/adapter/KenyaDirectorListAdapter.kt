package com.example.friendapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.friendapplication.KenyaDirectorDetailsActivity
import com.example.friendapplication.NigeriaDirectorDetailsActivity
import com.example.friendapplication.R
import com.example.friendapplication.model.KenyaDirector
import com.example.friendapplication.model.NigeriaDirector
import com.example.friendapplication.uitel.loadImage

class KenyaDirectorListAdapter (var mContext: Context, var kenyadirectorList:List<KenyaDirector>):
    RecyclerView.Adapter<KenyaDirectorListAdapter.ListViewHolder>()
{
    inner class ListViewHolder(var v: View): RecyclerView.ViewHolder(v){
        var imgT = v.findViewById<ImageView>(R.id.teacherImageView)
        var nameT = v. findViewById<TextView>(R.id.nameTextView)
        var descriT = v. findViewById<TextView>(R.id.descriptionTextView)
        var seeMoreBtn = v.findViewById<TextView>(R.id.seeMoreBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.kenya_director_item,parent,false)
        return ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val newList = kenyadirectorList[position]
        holder.nameT.text = newList.name
        holder.descriT.text = newList.description
        holder.imgT.loadImage(newList.imageUrl)
        holder.v.setOnClickListener {

            val name = newList.name
            val descrip = newList.description
            val imgUri = newList.imageUrl

            val mIntent = Intent(mContext, KenyaDirectorDetailsActivity::class.java)
            mIntent.putExtra("NAMET",name)
            mIntent.putExtra("DESCRIT",descrip)
            mIntent.putExtra("IMGURI",imgUri)
            mContext.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int = kenyadirectorList.size
}


