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
fun LandscapeCalculator(
    display: String,
    operationSymbol: String,
    onDigit: (String) -> Unit,
    onOperation: (CalculatorOperation) -> Unit,
    onEquals: () -> Unit,
    onClear: () -> Unit,
    onPercent: () -> Unit,
    onPlusMinus: () -> Unit
) {
    val buttonSpacing = 6.dp
    val cornerRadius = 16.dp

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .systemBarsPadding()
            .padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = operationSymbol.ifEmpty { " " },
                color = Color.Gray,
                fontSize = 16.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp)
            )
            Text(
                text = display,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                maxLines = 1
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                ButtonRow(buttonSpacing, Modifier.weight(1f)) {
                    CalcBtn("AC", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), cornerRadius, 16.sp) { onClear() }
                    CalcBtn("±", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), cornerRadius, 16.sp) { onPlusMinus() }
                    CalcBtn("%", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), cornerRadius, 16.sp) { onPercent() }
                }
                ButtonRow(buttonSpacing, Modifier.weight(1f)) {
                    CalcBtn("7", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 16.sp) { onDigit("7") }
                    CalcBtn("8", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 16.sp) { onDigit("8") }
                    CalcBtn("9", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 16.sp) { onDigit("9") }
                }
                ButtonRow(buttonSpacing, Modifier.weight(1f)) {
                    CalcBtn("4", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 16.sp) { onDigit("4") }
                    CalcBtn("5", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 16.sp) { onDigit("5") }
                    CalcBtn("6", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 16.sp) { onDigit("6") }
                }
                ButtonRow(buttonSpacing, Modifier.weight(1f)) {
                    CalcBtn("1", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 16.sp) { onDigit("1") }
                    CalcBtn("2", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 16.sp) { onDigit("2") }
                    CalcBtn("3", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 16.sp) { onDigit("3") }
                }
                ButtonRow(buttonSpacing, Modifier.weight(1f)) {
                    CalcBtn("0", Color(0xFF333333), Color.White, Modifier.weight(2f), cornerRadius, 16.sp) { onDigit("0") }
                    CalcBtn(",", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 16.sp) { onDigit(".") }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(56.dp),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalcBtn("÷", Color(0xFFFF9500), Color.White, Modifier.fillMaxWidth().weight(1f), cornerRadius, 20.sp) { onOperation(CalculatorOperation.Divide) }
            CalcBtn("×", Color(0xFFFF9500), Color.White, Modifier.fillMaxWidth().weight(1f), cornerRadius, 20.sp) { onOperation(CalculatorOperation.Multiply) }
            CalcBtn("-", Color(0xFFFF9500), Color.White, Modifier.fillMaxWidth().weight(1f), cornerRadius, 20.sp) { onOperation(CalculatorOperation.Subtract) }
            CalcBtn("+", Color(0xFFFF9500), Color.White, Modifier.fillMaxWidth().weight(1f), cornerRadius, 20.sp) { onOperation(CalculatorOperation.Add) }
            CalcBtn("=", Color(0xFFFF9500), Color.White, Modifier.fillMaxWidth().weight(1f), cornerRadius, 20.sp) { onEquals() }
        }
    }
}
