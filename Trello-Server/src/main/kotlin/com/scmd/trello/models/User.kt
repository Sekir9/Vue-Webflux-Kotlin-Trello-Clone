package com.scmd.trello.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    val id: String,
    val name: String,
    val email: String,
    val password: String
)