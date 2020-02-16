package com.danser.workshop4_login.domain

import java.io.Serializable

data class Note(
    val id: String,
    val title: String,
    val text: String
) : Serializable
