package com.example.calculator.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.domain.model.CalculatorOperation
import com.example.calculator.presentation.ui.LandscapeCalculator
import com.example.calculator.presentation.ui.PortraitCalculator

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    val state = viewModel.state

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    val operationSymbol = when (state.operation) {
        is CalculatorOperation.Add -> "+"
        is CalculatorOperation.Subtract -> "-"
        is CalculatorOperation.Multiply -> "×"
        is CalculatorOperation.Divide -> "÷"
        null -> ""
    }

    if (isLandscape) {
        LandscapeCalculator(
            display = state.display,
            operationSymbol = operationSymbol,
            onDigit = viewModel::onDigit,
            onOperation = viewModel::onOperation,
            onEquals = viewModel::onEquals,
            onClear = viewModel::onClear,
            onPercent = viewModel::onPercent,
            onPlusMinus = viewModel::onPlusMinus
        )
    } else {
        PortraitCalculator(
            display = state.display,
            operationSymbol = operationSymbol,
            onDigit = viewModel::onDigit,
            onOperation = viewModel::onOperation,
            onEquals = viewModel::onEquals,
            onClear = viewModel::onClear,
            onPercent = viewModel::onPercent,
            onPlusMinus = viewModel::onPlusMinus
        )
    }
}
