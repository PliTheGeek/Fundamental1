package com.example.fundamental1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fundamental1.R
import com.example.fundamental1.model.ListEventsItem
import com.example.fundamental1.viewmodel.EventViewModel

class FinishedFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventAdapter

    private val viewModel: EventViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_finished, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewFinished)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.finishedEvents.observe(viewLifecycleOwner, Observer { events ->
            adapter = EventAdapter(events, object : EventAdapter.OnItemClickListener {
                override fun onItemClick(event: ListEventsItem) {
                    val fragment = EventDetailFragment.newInstance(event.id!!)
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            })
            recyclerView.adapter = adapter
        })

        viewModel.fetchFinishedEvents()
        return view
    }
}