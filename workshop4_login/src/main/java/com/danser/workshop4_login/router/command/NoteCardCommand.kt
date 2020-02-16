package com.danser.workshop4_login.router.command

import android.app.Activity
import com.danser.workshop4_login.feature.card.NoteCardActivity
import com.danser.workshop4_login.feature.card.NoteCardArguments
import com.danser.workshop4_login.feature.card.NoteCardFragment
import com.danser.workshop4_login.router.IRouter

class NoteCardCommand(private val args: NoteCardArguments) : RouterCommand {

    override fun perform(activity: Activity, router: IRouter) {
        router.openActivity(
            activity = activity,
            clazz = NoteCardActivity::class.java,
            args = NoteCardFragment.createArgs(args)
        )
    }
}
