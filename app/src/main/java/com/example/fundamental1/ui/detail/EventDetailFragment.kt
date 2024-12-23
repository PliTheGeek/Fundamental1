package com.example.fundamental1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.fundamental1.R
import com.example.fundamental1.viewmodel.EventViewModel

class EventDetailFragment : Fragment() {

    companion object {
        private const val ARG_EVENT_ID = "event_id"

        fun newInstance(eventId: Int): EventDetailFragment {
            val fragment = EventDetailFragment()
            val bundle = Bundle().apply {
                putInt(ARG_EVENT_ID, eventId)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    private val viewModel: EventViewModel by viewModels()

    private var eventId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            eventId = it.getInt(ARG_EVENT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_event_detail, container, false)

        eventId?.let {
            viewModel.fetchEventDetails(it)
        }

        viewModel.eventDetails.observe(viewLifecycleOwner, { response ->
            response.event?.let { event ->
                view.findViewById<TextView>(R.id.eventName).text = event.name
                view.findViewById<TextView>(R.id.eventOwner).text = event.ownerName
                view.findViewById<TextView>(R.id.eventTime).text = event.beginTime
                view.findViewById<TextView>(R.id.eventQuota).text = (event.quota - event.registrants).toString()
                view.findViewById<TextView>(R.id.eventDescription).text = event.description
                Glide.with(this).load(event.imageLogo).into(view.findViewById(R.id.eventImage))
            }
        })

        return view
    }
}