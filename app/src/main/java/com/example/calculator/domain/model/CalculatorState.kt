package com.example.calculator.domain.model

data class CalculatorState(
    val display: String = "0",
    val firstOperand: Double = 0.0,
    val operation: CalculatorOperation? = null,
    val isNewInput: Boolean = true
)
