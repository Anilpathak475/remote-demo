package com.servify.remoterconstrol

import android.app.Application
import android.util.Log

import java.util.HashMap

import io.cobrowse.core.CobrowseIO

class Remotepplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CobrowseIO.instance().license("C-gVfWMm0HN0dQ")
        val customData = HashMap<String, Any>()
        customData["user_id"] = "5311"
        customData["user_name"] = "Anil"
        customData["user_email"] = "amil@theprocedure.in"

        customData["device_name"] = "One plus3t"
        CobrowseIO.instance().customData(customData)

        CobrowseIO.instance().start(this)
    }
}
