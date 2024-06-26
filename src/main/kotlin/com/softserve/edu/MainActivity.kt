package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface {
                FifteenState(FifteenEngineImpl())
            }
        }
    }
}


fun main() {
    ConsoleView(FifteenEngineImpl()).start()
}

class ConsoleView(val engine: FifteenEngine) {
    var state = engine.getInitialState()

    fun start() {
        while (!engine.isCompleted(state)) {
            show()
            println("enter the cell to move or q to exit")
            val response = readln()
            if ("q" == response) return
            val cell = response.toByteOrNull() ?: -1
            if (cell !in 1..15) {
                println("It's not a number of some cell on the field: '$response'")
                continue
            }
            val ix = state.indexOf(cell)
            state = engine.updatedState(state, ix)
        }
        show()
        println("You won! Congratulations")
    }

    fun show() {
        println("-".repeat(20))
        for (iRow in 0..3) {
            print("| ")
            for (iCol in 0..3) {
                val ix = iRow * 4 + iCol
                val cell = engine.toText(state[ix])
                print("%4s".format(cell))
            }
            println(" |")
        }
        println("-".repeat(20))
    }
}

interface FifteenEngine {
    companion object {
        const val EMPTY: Byte = 16
        private val finalState = ByteArray(16) { (it + 1).toByte() }
        fun toText(i: Byte): String = if (i == EMPTY) " " else i.toString()
    }

    fun isCompleted(currentState: ByteArray) = currentState.contentEquals(finalState)

    // todo: check whether the reordering is feasible and swap two adjacent cells if not
    fun getInitialState(): ByteArray = finalState.clone().also { it.shuffle() }
    fun toText(i: Byte): String = Companion.toText(i)
    fun updatedState(currentState: ByteArray, idClicked: Int): ByteArray
}

class FifteenEngineImpl : FifteenEngine {
    override fun updatedState(currentState: ByteArray, idClicked: Int): ByteArray {
        val iRowBase = idClicked / 4
        val iColBase = idClicked % 4
        for (d in listOf(-1, 1)) {
            val iCol = iColBase + d
            var ix = iCol + 4 * iRowBase
            if (iCol in 0..3 && currentState[ix] == FifteenEngine.EMPTY) {
                return currentState.withSwapped(ix, idClicked)
            }
            val iRow = iRowBase + d
            ix = iColBase + 4 * iRow
            if (iRow in 0..3 && currentState[ix] == FifteenEngine.EMPTY) {
                return currentState.withSwapped(ix, idClicked)
            }
        }
        return currentState
    }

    private fun ByteArray.withSwapped(i: Int, j: Int): ByteArray {
        val res = this.clone()
        res[i] = this[j]
        res[j] = this[i]
        return res
    }
}

@Composable
fun FifteenState(engine: FifteenEngine) {
    var state by rememberSaveable { mutableStateOf(engine.getInitialState()) }

    fun onClick(ix: Int) {
        state = engine.updatedState(state, ix)
    }

    fun onReset() {
        state = engine.getInitialState()
    }

    FifteenView(
        state = state,
        isCompleted = engine.isCompleted(state),
        onReset = ::onReset,
        onClick = ::onClick,
        toText = { engine.toText(it) }
    )
}

@Composable
fun FifteenView(
    state: ByteArray,
    isCompleted: Boolean,
    onReset: () -> Unit,
    onClick: (Int) -> Unit,
    toText: (Byte) -> String
) {
    fun ix(iRow: Int, iCol: Int) = iCol - 1 + (iRow - 1) * 4

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1F))
        for (iRow in 1..4) {
            Row {
                for (iCol in 1..4) {
                    Button(
                        shape = MaterialTheme.shapes.large,
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .padding(1.dp)
                            .size(94.dp),
                        onClick = { onClick(ix(iRow, iCol)) }
                    ) {
                        Text(
                            toText(state[ix(iRow, iCol)]),
                            fontSize = 50.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(4.dp, 8.dp)
                .fillMaxWidth()
        ) {
            Button(onClick = { onReset() }) {
                Text(
                    "Reset",
                    fontSize = 30.sp
                )
            }
            if (isCompleted) {
                Text(
                    "Victory!",
                    fontSize = 30.sp,
                    modifier = Modifier
                        .weight(1F)
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Preview(
    showBackground = true,
    device = "spec:parent=pixel_4",
    showSystemUi = true,
    name = "interactive"
)
@Composable
fun DefaultPreview1() {
    FifteenState(FifteenEngineImpl())
}

@Preview(showBackground = true, device = "spec:parent=pixel_4", showSystemUi = true)
@Composable
fun DefaultPreview2() {
    FifteenView(
        state = ByteArray(16) { (it + 1).toByte() },
        isCompleted = true,
        onClick = {},
        onReset = {},
        toText = FifteenEngine::toText
    )
}

@Preview(
    showBackground = true,
    device = "spec:parent=pixel_4,orientation=landscape", showSystemUi = true
)
@Composable
fun DefaultPreview3() {
    FifteenState(FifteenEngineImpl())
}
