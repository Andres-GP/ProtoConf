package com.protompany.protoconf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.protompany.protoconf.R
import com.protompany.protoconf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleListener: ScheduleListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    var listConference = ArrayList<Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false))

    override fun getItemCount() = listConference.size

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConference[position] as Conference

        holder.tvConferenceTopic.text = conference.title
        holder.tvConferenceAuthor.text = conference.author

        val simpleDateFormat = SimpleDateFormat("HH::mm")

        val cal = Calendar.getInstance()
        cal.time = conference.dateTime
        val hourFormat = simpleDateFormat.format(conference.dateTime)
        holder.tvConferenceHour.text = hourFormat

        holder.itemView.setOnClickListener {
            scheduleListener.onConferenceClicked(conference, position)
        }
    }

    fun updateData(data: List<Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvConferenceTopic = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceTopic)
        val tvConferenceAuthor = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceAuthor)
        val tvConferenceHour = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceHour)
        //TODO images of authors
        val tvItemAuthorImage = itemView.findViewById<TextView>(R.id.ivItemAuthorImage)
    }

}