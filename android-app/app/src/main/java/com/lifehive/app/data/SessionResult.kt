package com.lifehive.app.data

sealed class SessionResult {
    object Valid : SessionResult()
    object Invalid : SessionResult()
}
