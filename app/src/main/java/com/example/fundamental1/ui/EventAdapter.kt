package com.example.fundamental1.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fundamental1.R
import com.example.fundamental1.model.ListEventsItem

class EventAdapter(
    private var events: List<ListEventsItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(event: ListEventsItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event, listener)
    }

    override fun getItemCount(): Int = events.size

    fun updateEvents(newEvents: List<ListEventsItem>) {
        events = newEvents
        notifyDataSetChanged()
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: ListEventsItem, listener: OnItemClickListener) {
            // Bind event data to views
            itemView.setOnClickListener {
                listener.onItemClick(event)
            }
        }
    }
}