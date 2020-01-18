package com.danser.workshop4_login

import android.app.Application
import dagger.Component


// Definition of the Application graph
@Component
interface ApplicationComponent {
}

class NotesApplication : Application() {

    val appComponent = DaggerApplicationComponent.create()


}
