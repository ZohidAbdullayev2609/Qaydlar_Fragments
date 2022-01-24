package com.example.qaydlarfragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qaydlarfragments.R
import com.example.qaydlarfragments.model.User
import com.example.qaydlarfragments.utils.MySharedPrefernce.init
import kotlinx.android.synthetic.main.item_user.view.*

class MyRecycleAdapter(private val list: ArrayList<User>, private val onClick: OnClick) :
    RecyclerView.Adapter<MyRecycleAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val editText: TextView = itemView.findViewById(R.id.tv_title)
        val editText2: TextView = itemView.findViewById(R.id.tv_description)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION)
                onClick.onClickListener(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.editText.text = list[position].title
        holder.editText2.text = list[position].description
    }

    override fun getItemCount(): Int = list.size

    interface OnClick {
        fun onClickListener(position: Int)
    }

}
