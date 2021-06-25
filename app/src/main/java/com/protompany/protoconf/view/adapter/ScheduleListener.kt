package com.protompany.protoconf.view.adapter

import com.protompany.protoconf.model.Conference


interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}