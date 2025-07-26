package com.ecommerce.swift.utils.validations

sealed class LoginRegisterValidation {
    data object Valid : LoginRegisterValidation()
    data class Invalid(val error: String) : LoginRegisterValidation()
}

data class RegisterFieldsState(
    val email: LoginRegisterValidation,
    val password: LoginRegisterValidation
)