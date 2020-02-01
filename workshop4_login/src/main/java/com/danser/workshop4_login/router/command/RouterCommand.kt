package com.danser.workshop4_login.router.command

import android.app.Activity
import com.danser.workshop4_login.router.IRouter

interface RouterCommand {
    fun perform(activity: Activity, router: IRouter)
}
