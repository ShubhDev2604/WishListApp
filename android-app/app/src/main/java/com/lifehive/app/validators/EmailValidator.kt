package com.lifehive.app.validators

object EmailValidator {

    private val EMAIL_REGEX =
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

    fun validate(email: String): Any {
        if (email.isBlank()) {
            return ValidationResult.Invalid("Email is required")
        }

        if (!EMAIL_REGEX.matches(email)) {
            return ValidationResult.Invalid("Invalid email format")
        }

        return ValidationResult.Valid
    }
}
