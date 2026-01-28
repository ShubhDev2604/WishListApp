package com.lifehive.app.validators

object PasswordValidator {

    private val PASSWORD_REGEX =
        Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$")

    fun validate(password: String): Any {
        if (password.isBlank()) {
            return ValidationResult.Invalid("Password is required")
        }

        if (password.length < 8) {
            return ValidationResult.Invalid("Password must be at least 8 characters")
        }

        if (!PASSWORD_REGEX.matches(password)) {
            return ValidationResult.Invalid(
                "Password must contain uppercase, lowercase, number and special character"
            )
        }

        return ValidationResult.Valid
    }
}
