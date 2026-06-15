package com.example.calculator.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.model.CalculatorOperation
import com.example.calculator.domain.model.CalculatorState
import com.example.calculator.domain.usecase.CalculateUseCase

class CalculatorViewModel : ViewModel() {

    private val calculateUseCase = CalculateUseCase()

    var state by mutableStateOf(CalculatorState())
        private set

    fun onDigit(digit: String) {
        state = if (state.isNewInput) {
            state.copy(display = digit, isNewInput = false)
        } else {
            val newDisplay = if (state.display == "0" && digit != ".") digit
            else state.display + digit
            state.copy(display = newDisplay)
        }
    }

    fun onOperation(op: CalculatorOperation) {
        state = state.copy(
            firstOperand = state.display.toDoubleOrNull() ?: 0.0,
            operation = op,
            isNewInput = true
        )
    }

    fun onEquals() {
        state = calculateUseCase(state)
    }

    fun onClear() {
        state = CalculatorState()
    }

    fun onPercent() {
        val value = state.display.toDoubleOrNull() ?: 0.0
        state = state.copy(display = (value / 100).toString())
    }

    fun onPlusMinus() {
        val value = state.display.toDoubleOrNull() ?: 0.0
        state = state.copy(display = (-value).toString())
    }
}
