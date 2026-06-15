package com.example.calculator.domain.usecase

import com.example.calculator.domain.model.CalculatorOperation
import com.example.calculator.domain.model.CalculatorState

class CalculateUseCase {

    operator fun invoke(state: CalculatorState): CalculatorState {
        val secondOperand = state.display.toDoubleOrNull() ?: 0.0
        val result = when (state.operation) {
            is CalculatorOperation.Add -> state.firstOperand + secondOperand
            is CalculatorOperation.Subtract -> state.firstOperand - secondOperand
            is CalculatorOperation.Multiply -> state.firstOperand * secondOperand
            is CalculatorOperation.Divide -> {
                if (secondOperand != 0.0) state.firstOperand / secondOperand
                else Double.NaN
            }
            null -> secondOperand
        }

        return state.copy(
            display = formatResult(result),
            operation = null,
            isNewInput = true
        )
    }

    private fun formatResult(result: Double): String {
        return if (result.isNaN()) {
            "Error"
        } else if (result == result.toLong().toDouble()) {
            result.toLong().toString()
        } else {
            result.toString()
        }
    }
}
