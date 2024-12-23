package com.example.fundamental1.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fundamental1.R
import com.example.fundamental1.model.ListEventsItem

class EventAdapter(
    private val eventList: List<ListEventsItem>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(event: ListEventsItem)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventImage: ImageView = itemView.findViewById(R.id.eventImage)
        val eventName: TextView = itemView.findViewById(R.id.eventName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = eventList[position]
        holder.eventName.text = event.name
        Glide.with(holder.itemView.context).load(event.imageLogo).into(holder.eventImage)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(event)
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}