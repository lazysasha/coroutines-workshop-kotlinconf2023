package com.kotlinconf.workshop

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class User(val name: String)