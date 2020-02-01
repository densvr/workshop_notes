package com.danser.workshop4_login.domain

import java.io.Serializable

data class Note(
    val title: String,
    val text: String
) : Serializable
