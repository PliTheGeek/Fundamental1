package com.example.fundamental1.ui.upcoming

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fundamental1.R
import com.example.fundamental1.model.ListEventsItem
import com.example.fundamental1.ui.EventAdapter

class UpcomingFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventAdapter

    private val viewModel: UpcomingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upcoming, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewUpcoming)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = EventAdapter(emptyList(), object : EventAdapter.OnItemClickListener {
            override fun onItemClick(event: ListEventsItem) {
                event.id?.let {
                    val action = UpcomingFragmentDirections.actionUpcomingToDetail(it)
                    findNavController().navigate(action)
                } ?: run {
                    Log.e("UpcomingFragment", "Event ID is null")
                }
            }
        })
        recyclerView.adapter = adapter

        viewModel.upcomingEvents.observe(viewLifecycleOwner, Observer { events ->
            adapter.updateEvents(events)
        })

        viewModel.fetchUpcomingEvents()
        return view
    }
}