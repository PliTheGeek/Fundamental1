package com.example.fundamental1.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.fundamental1.R


class DetailFragment : Fragment() {

    companion object {
        private const val ARG_EVENT_ID = "event_id"

        fun newInstance(eventId: Int): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle().apply {
                putInt(ARG_EVENT_ID, eventId)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    private val viewModel: DetailViewModel by viewModels()

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



        return view
    }
}