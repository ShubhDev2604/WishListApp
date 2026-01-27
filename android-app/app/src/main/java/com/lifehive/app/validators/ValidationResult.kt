package com.lifehive.app.validators

sealed class ValidationResult {
    object Valid
    data class Invalid(val message: String)
}
