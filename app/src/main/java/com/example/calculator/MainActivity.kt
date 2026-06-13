package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

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

    fun onPercent() {
        val value = display.toDoubleOrNull() ?: 0.0
        display = (value / 100).toString()
    }

    fun onPlusMinus() {
        val value = display.toDoubleOrNull() ?: 0.0
        display = (-value).toString()
    }

    if (isLandscape) {
        LandscapeCalculator(
            display = display,
            operation = operation,
            onDigit = ::onDigit,
            onOperation = ::onOperation,
            onEquals = ::onEquals,
            onClear = ::onClear,
            onPercent = ::onPercent,
            onPlusMinus = ::onPlusMinus
        )
    } else {
        PortraitCalculator(
            display = display,
            operation = operation,
            onDigit = ::onDigit,
            onOperation = ::onOperation,
            onEquals = ::onEquals,
            onClear = ::onClear,
            onPercent = ::onPercent,
            onPlusMinus = ::onPlusMinus
        )
    }
}

@Composable
private fun PortraitCalculator(
    display: String,
    operation: String,
    onDigit: (String) -> Unit,
    onOperation: (String) -> Unit,
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
            text = operation.ifEmpty { " " },
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
            ButtonRow(buttonSpacing) {
                CalcBtn("AC", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), cornerRadius) { onClear() }
                CalcBtn("±", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), cornerRadius) { onPlusMinus() }
                CalcBtn("%", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), cornerRadius) { onPercent() }
                CalcBtn("÷", Color(0xFFFF9500), Color.White, Modifier.weight(1f), cornerRadius) { onOperation("÷") }
            }

            ButtonRow(buttonSpacing) {
                CalcBtn("7", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius) { onDigit("7") }
                CalcBtn("8", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius) { onDigit("8") }
                CalcBtn("9", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius) { onDigit("9") }
                CalcBtn("×", Color(0xFFFF9500), Color.White, Modifier.weight(1f), cornerRadius) { onOperation("×") }
            }

            ButtonRow(buttonSpacing) {
                CalcBtn("4", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius) { onDigit("4") }
                CalcBtn("5", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius) { onDigit("5") }
                CalcBtn("6", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius) { onDigit("6") }
                CalcBtn("-", Color(0xFFFF9500), Color.White, Modifier.weight(1f), cornerRadius) { onOperation("-") }
            }

            ButtonRow(buttonSpacing) {
                CalcBtn("1", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius) { onDigit("1") }
                CalcBtn("2", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius) { onDigit("2") }
                CalcBtn("3", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius) { onDigit("3") }
                CalcBtn("+", Color(0xFFFF9500), Color.White, Modifier.weight(1f), cornerRadius) { onOperation("+") }
            }

            ButtonRow(buttonSpacing) {
                CalcBtn("0", Color(0xFF333333), Color.White, Modifier.weight(2f), cornerRadius) { onDigit("0") }
                CalcBtn(",", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius) { onDigit(".") }
                CalcBtn("=", Color(0xFFFF9500), Color.White, Modifier.weight(1f), cornerRadius) { onEquals() }
            }
        }
    }
}

@Composable
private fun LandscapeCalculator(
    display: String,
    operation: String,
    onDigit: (String) -> Unit,
    onOperation: (String) -> Unit,
    onEquals: () -> Unit,
    onClear: () -> Unit,
    onPercent: () -> Unit,
    onPlusMinus: () -> Unit
) {
    val buttonSpacing = 8.dp
    val cornerRadius = 20.dp

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .systemBarsPadding()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Text(
                text = operation.ifEmpty { " " },
                color = Color.Gray,
                fontSize = 18.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp)
            )
            Text(
                text = display,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                maxLines = 1
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                ButtonRow(buttonSpacing) {
                    CalcBtn("AC", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), cornerRadius, 18.sp) { onClear() }
                    CalcBtn("±", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), cornerRadius, 18.sp) { onPlusMinus() }
                    CalcBtn("%", Color(0xFFA5A5A5), Color.Black, Modifier.weight(1f), cornerRadius, 18.sp) { onPercent() }
                }
                ButtonRow(buttonSpacing) {
                    CalcBtn("7", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 18.sp) { onDigit("7") }
                    CalcBtn("8", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 18.sp) { onDigit("8") }
                    CalcBtn("9", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 18.sp) { onDigit("9") }
                }
                ButtonRow(buttonSpacing) {
                    CalcBtn("4", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 18.sp) { onDigit("4") }
                    CalcBtn("5", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 18.sp) { onDigit("5") }
                    CalcBtn("6", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 18.sp) { onDigit("6") }
                }
                ButtonRow(buttonSpacing) {
                    CalcBtn("1", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 18.sp) { onDigit("1") }
                    CalcBtn("2", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 18.sp) { onDigit("2") }
                    CalcBtn("3", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 18.sp) { onDigit("3") }
                }
                ButtonRow(buttonSpacing) {
                    CalcBtn("0", Color(0xFF333333), Color.White, Modifier.weight(2f), cornerRadius, 18.sp) { onDigit("0") }
                    CalcBtn(",", Color(0xFF333333), Color.White, Modifier.weight(1f), cornerRadius, 18.sp) { onDigit(".") }
                }
            }
        }

        Column(
            modifier = Modifier
                .width(72.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalcBtn("÷", Color(0xFFFF9500), Color.White, Modifier.weight(1f), cornerRadius, 22.sp) { onOperation("÷") }
            CalcBtn("×", Color(0xFFFF9500), Color.White, Modifier.weight(1f), cornerRadius, 22.sp) { onOperation("×") }
            CalcBtn("-", Color(0xFFFF9500), Color.White, Modifier.weight(1f), cornerRadius, 22.sp) { onOperation("-") }
            CalcBtn("+", Color(0xFFFF9500), Color.White, Modifier.weight(1f), cornerRadius, 22.sp) { onOperation("+") }
            CalcBtn("=", Color(0xFFFF9500), Color.White, Modifier.weight(1f), cornerRadius, 22.sp) { onEquals() }
        }
    }
}

@Composable
private fun ButtonRow(spacing: Dp, content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        content = content
    )
}

@Composable
private fun CalcBtn(
    text: String,
    backgroundColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 24.dp,
    fontSize: TextUnit = 24.sp,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(64.dp),
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium
        )
    }
}
