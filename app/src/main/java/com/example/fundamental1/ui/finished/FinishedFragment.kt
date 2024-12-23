package com.example.fundamental1.ui.finished

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

class FinishedFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventAdapter

    private val viewModel: FinishedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_finished, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewFinished)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = EventAdapter(emptyList(), object : EventAdapter.OnItemClickListener {
            override fun onItemClick(event: ListEventsItem) {
                event.id?.let {
                    val action = FinishedFragmentDirections.actionFinishedToDetail(it)
                    findNavController().navigate(action)
                } ?: run {
                    Log.e("FinishedFragment", "Event ID is null")
                }
            }
        })
        recyclerView.adapter = adapter

        viewModel.finishedEvents.observe(viewLifecycleOwner, Observer { events ->
            adapter.updateEvents(events)
        })

        viewModel.fetchFinishedEvents()
        return view
    }
}