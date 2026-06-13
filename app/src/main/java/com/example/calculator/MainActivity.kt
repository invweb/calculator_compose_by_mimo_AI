package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.enableEdgeToEdge
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var display by remember { mutableStateOf("0") }
    var firstOperand by remember { mutableDoubleStateOf(0.0) }
    var operation by remember { mutableStateOf("") }
    var isNewInput by remember { mutableStateOf(true) }

    fun onDigit(digit: String) {
        display = if (isNewInput) {
            isNewInput = false
            digit
        } else {
            if (display == "0" && digit != ".") digit else display + digit
        }
    }

    fun onOperation(op: String) {
        firstOperand = display.toDoubleOrNull() ?: 0.0
        operation = op
        isNewInput = true
    }

    fun onEquals() {
        val secondOperand = display.toDoubleOrNull() ?: 0.0
        val result = when (operation) {
            "+" -> firstOperand + secondOperand
            "-" -> firstOperand - secondOperand
            "×" -> firstOperand * secondOperand
            "÷" -> if (secondOperand != 0.0) firstOperand / secondOperand else Double.NaN
            else -> secondOperand
        }
        display = if (result.isNaN()) "Error" else {
            if (result == result.toLong().toDouble()) result.toLong().toString()
            else result.toString()
        }
        operation = ""
        isNewInput = true
    }

    fun onClear() {
        display = "0"
        firstOperand = 0.0
        operation = ""
        isNewInput = true
    }

    fun onBackspace() {
        display = if (display.length > 1) display.dropLast(1) else "0"
    }

    fun onPercent() {
        val value = display.toDoubleOrNull() ?: 0.0
        display = (value / 100).toString()
    }

    fun onPlusMinus() {
        val value = display.toDoubleOrNull() ?: 0.0
        display = (-value).toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .systemBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = operation.ifEmpty { " " },
            color = Color.Gray,
            fontSize = 24.sp,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Text(
            text = display,
            color = Color.White,
            fontSize = 64.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            maxLines = 1
        )

        val buttonSpacing = 12.dp

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("AC", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f)) { onClear() }
            CalculatorButton("±", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f)) { onPlusMinus() }
            CalculatorButton("%", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f)) { onPercent() }
            CalculatorButton("÷", Color(0xFFFF9500), Color.White, Modifier.weight(1f)) { onOperation("÷") }
        }

        Spacer(modifier = Modifier.height(buttonSpacing))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("7", Color(0xFF333333), Color.White, Modifier.weight(1f)) { onDigit("7") }
            CalculatorButton("8", Color(0xFF333333), Color.White, Modifier.weight(1f)) { onDigit("8") }
            CalculatorButton("9", Color(0xFF333333), Color.White, Modifier.weight(1f)) { onDigit("9") }
            CalculatorButton("×", Color(0xFFFF9500), Color.White, Modifier.weight(1f)) { onOperation("×") }
        }

        Spacer(modifier = Modifier.height(buttonSpacing))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("4", Color(0xFF333333), Color.White, Modifier.weight(1f)) { onDigit("4") }
            CalculatorButton("5", Color(0xFF333333), Color.White, Modifier.weight(1f)) { onDigit("5") }
            CalculatorButton("6", Color(0xFF333333), Color.White, Modifier.weight(1f)) { onDigit("6") }
            CalculatorButton("-", Color(0xFFFF9500), Color.White, Modifier.weight(1f)) { onOperation("-") }
        }

        Spacer(modifier = Modifier.height(buttonSpacing))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("1", Color(0xFF333333), Color.White, Modifier.weight(1f)) { onDigit("1") }
            CalculatorButton("2", Color(0xFF333333), Color.White, Modifier.weight(1f)) { onDigit("2") }
            CalculatorButton("3", Color(0xFF333333), Color.White, Modifier.weight(1f)) { onDigit("3") }
            CalculatorButton("+", Color(0xFFFF9500), Color.White, Modifier.weight(1f)) { onOperation("+") }
        }

        Spacer(modifier = Modifier.height(buttonSpacing))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("0", Color(0xFF333333), Color.White, Modifier.weight(2f)) { onDigit("0") }
            CalculatorButton(",", Color(0xFF333333), Color.White, Modifier.weight(1f)) { onDigit(".") }
            CalculatorButton("=", Color(0xFFFF9500), Color.White, Modifier.weight(1f)) { onEquals() }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    backgroundColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(if (text == "0") 2f else 1f)
            .height(72.dp),
        shape = RoundedCornerShape(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = text,
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
