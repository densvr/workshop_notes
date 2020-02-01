package com.danser.workshop4_login.router

import android.app.Activity
import android.content.Intent
import android.os.Bundle

interface IRouter {
    fun <T : Activity> openActivity(activity: Activity, clazz: Class<T>, args: Bundle = Bundle())
}

object Router : IRouter {

    override fun <T : Activity> openActivity(activity: Activity, clazz: Class<T>, args: Bundle) {
        val intent = Intent(activity, clazz)
        intent.putExtras(args)
        activity.startActivity(intent)
    }
}
