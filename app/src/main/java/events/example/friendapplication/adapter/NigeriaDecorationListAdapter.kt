package events.app.friendapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import events.app.friendapplication.NigeriaDecorationDetailsActivity
import events.app.friendapplication.R
import events.app.friendapplication.model.NigeriaDecoration
import events.app.friendapplication.uitel.loadImage


class NigeriaDecorationListAdapter (var mContext: Context,
                                    var nigeriadecorationList:List<NigeriaDecoration>):
    RecyclerView.Adapter<NigeriaDecorationListAdapter.ListViewHolder>()
{
    inner class ListViewHolder(var v: View): RecyclerView.ViewHolder(v){
        var imgT = v.findViewById<ImageView>(R.id.teacherImageView)
        var nameT = v. findViewById<TextView>(R.id.nameTextView)
        var descriT = v. findViewById<TextView>(R.id.descriptionTextView)
        var seeMoreBtn = v.findViewById<TextView>(R.id.seeMoreBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.nigeria_decoration_item,parent,false)
        return ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val newList = nigeriadecorationList[position]
        holder.nameT.text = newList.name
        holder.descriT.text = newList.description
        holder.imgT.loadImage(newList.imageUrl)
        holder.v.setOnClickListener {

            val name = newList.name
            val descrip = newList.description
            val imgUri = newList.imageUrl

            val mIntent = Intent(mContext, NigeriaDecorationDetailsActivity::class.java)
            mIntent.putExtra("NAMET",name)
            mIntent.putExtra("DESCRIT",descrip)
            mIntent.putExtra("IMGURI",imgUri)
            mContext.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int = nigeriadecorationList.size
}


