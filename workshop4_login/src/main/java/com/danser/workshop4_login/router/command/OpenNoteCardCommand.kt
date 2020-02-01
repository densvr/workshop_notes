package com.danser.workshop4_login.router.command

import android.app.Activity
import com.danser.workshop4_login.domain.Note
import com.danser.workshop4_login.feature.card.NoteCardActivity
import com.danser.workshop4_login.router.IRouter

class OpenNoteCardCommand(private val note: Note): RouterCommand {

    override fun perform(activity: Activity, router: IRouter) {
        router.openActivity(activity, NoteCardActivity::class.java)
    }
}
