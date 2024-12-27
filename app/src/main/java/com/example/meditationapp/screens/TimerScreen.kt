package com.example.timertutorial

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meditationapp.R
import com.example.meditationapp.ViewModelFactory
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun TimerScreen(
    modifier: Modifier = Modifier.size(200.dp),
    handleColor: Color = Color(0xFF032744),
    inactiveColor: Color = Color.DarkGray,
    minutes: Long,
    seconds: Long,
    index: Int,
    listOfSongs: List<Int>,
    activeColor: Color = Color(0xFF4682B4),
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp,
) {
    val context = LocalContext.current
    var currentIndex by remember { mutableIntStateOf(index) }
    val viewModel = viewModel<CountDownViewModel>(
        factory = ViewModelFactory(
            minutes,
            seconds,
            listOfSongs = listOfSongs,
            context = context,
            index = currentIndex
        )
    )
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember { //Percentage value of time 0.77=>77%
        mutableFloatStateOf(initialValue)
    }
    var isTimerRunnig by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(currentIndex) {
        Log.d("index", index.toString())
        viewModel.whichMusic(currentIndex)
    }
    DisposableEffect(true) {
        onDispose {
            viewModel.exitFromScreen()
        }
    }
    LaunchedEffect(key1 = viewModel.timeLeft, key2 = viewModel.isPlaying) {
        Log.d("tag", viewModel.timeLeft.toString())
        if (!viewModel.isFinished && viewModel.isPlaying) {
            //delay(100L)
            //currentTime -= 100L
            value =
                viewModel.timeLeft / viewModel.totalTime.toFloat() //currentTime/totalTime.toFloat()
        } else if (viewModel.isFinished) {
            value = 1f
        }
    }
//    LaunchedEffect(player.currentMediaItemIndex){
//        currentIndex = player.currentMediaItemIndex
//    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center, modifier = modifier.onSizeChanged {
            size = it
        }) {
            Canvas(modifier = modifier) {
                drawArc(
                    color = inactiveColor,
                    startAngle = -215f,
                    sweepAngle = 250f,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
                drawArc(
                    color = activeColor,
                    startAngle = -215f,
                    sweepAngle = if (!viewModel.isFinished) 250f * value else 250f, // Depends on percentage of timer
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
                val center = Offset(size.width / 2f, size.height / 2f)
                val beta =
                    if (!viewModel.isFinished) (250f * value + 145f) * (PI / 180f).toFloat() else (250f + 145f) * (PI / 180f).toFloat()
                val r = size.width / 2f
                val a = cos(beta) * r
                val b = sin(beta) * r
                drawPoints(
                    listOf(Offset(center.x + a, center.y + b)),
                    pointMode = PointMode.Points,
                    color = handleColor,
                    strokeWidth = (strokeWidth * 3f).toPx(),
                    cap = StrokeCap.Round
                )
            }
            Text(
                text = viewModel.timerText.value,//(currentTime / 60000L).toString(),
                fontSize = 44.sp, fontWeight = FontWeight.Bold, color = Color.Black
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_fast_rewind_24),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(16.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF9E6CF)).clickable { viewModel.goToPrev() }
            )
            Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                Button(
                    onClick = {
                        if (!viewModel.isFinished) {
                            if (viewModel.isPlaying) {
                                viewModel.stopCountDownTimer()
                                Log.d("play", "Pause")
                            } else {
                                viewModel.startCountDownTimer()
                            }
                        } else {
                            viewModel.isFinished = false
                            isTimerRunnig = true
                            value = 1f
                            viewModel.timeLeft = viewModel.totalTime
                            viewModel.startCountDownTimer()
                            //player.play()
                        }
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = if (!viewModel.isPlaying || viewModel.isFinished) Color(
                            0xFF8b4513
                        )
                        else Color.Red
                    )
                ) {
                    Text(text = if (viewModel.isPlaying && !viewModel.isFinished) "Stop" else if (!viewModel.isPlaying && !viewModel.isFinished) "Start" else "Restart")
                }
                Button(
                    onClick = {
                        viewModel.resetCountDownTimer()
                        value = 1f

                    }, colors = ButtonDefaults.buttonColors(
                        Color(0xFF8b4513)
                    )
                ) {
                    Text(text = "Reset")
                }
            }
            Image(
                painter = painterResource(R.drawable.baseline_fast_forward_24),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(16.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF9E6CF)).clickable { viewModel.goToNext() }
            )
        }
    }
}