package com.example.calculator.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.domain.model.CalculatorOperation
import com.example.calculator.presentation.ui.components.ButtonRow
import com.example.calculator.presentation.ui.components.CalcBtn

@Composable
fun PortraitCalculator(
    display: String,
    operationSymbol: String,
    onDigit: (String) -> Unit,
    onOperation: (CalculatorOperation) -> Unit,
    onEquals: () -> Unit,
    onClear: () -> Unit,
    onPercent: () -> Unit,
    onPlusMinus: () -> Unit
) {
    val buttonSpacing = 10.dp
    val cornerRadius = 24.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .systemBarsPadding()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = operationSymbol.ifEmpty { " " },
            color = Color.Gray,
            fontSize = 22.sp,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )

        Text(
            text = display,
            color = Color.White,
            fontSize = 56.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            maxLines = 1
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            ButtonRow(buttonSpacing, Modifier.weight(1f)) {
                CalcBtn("AC", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onClear() }
                CalcBtn("±", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onPlusMinus() }
                CalcBtn("%", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onPercent() }
                CalcBtn("÷", Color(0xFFFF9500), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onOperation(CalculatorOperation.Divide) }
            }

            ButtonRow(buttonSpacing, Modifier.weight(1f)) {
                CalcBtn("7", Color(0xFF333333), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onDigit("7") }
                CalcBtn("8", Color(0xFF333333), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onDigit("8") }
                CalcBtn("9", Color(0xFF333333), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onDigit("9") }
                CalcBtn("×", Color(0xFFFF9500), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onOperation(CalculatorOperation.Multiply) }
            }

            ButtonRow(buttonSpacing, Modifier.weight(1f)) {
                CalcBtn("4", Color(0xFF333333), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onDigit("4") }
                CalcBtn("5", Color(0xFF333333), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onDigit("5") }
                CalcBtn("6", Color(0xFF333333), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onDigit("6") }
                CalcBtn("-", Color(0xFFFF9500), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onOperation(CalculatorOperation.Subtract) }
            }

            ButtonRow(buttonSpacing, Modifier.weight(1f)) {
                CalcBtn("1", Color(0xFF333333), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onDigit("1") }
                CalcBtn("2", Color(0xFF333333), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onDigit("2") }
                CalcBtn("3", Color(0xFF333333), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onDigit("3") }
                CalcBtn("+", Color(0xFFFF9500), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onOperation(CalculatorOperation.Add) }
            }

            ButtonRow(buttonSpacing, Modifier.weight(1f)) {
                CalcBtn("0", Color(0xFF333333), Color.White, Modifier.weight(2f).fillMaxHeight(), cornerRadius) { onDigit("0") }
                CalcBtn(",", Color(0xFF333333), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onDigit(".") }
                CalcBtn("=", Color(0xFFFF9500), Color.White, Modifier.weight(1f).fillMaxHeight(), cornerRadius) { onEquals() }
            }
        }
    }
}
