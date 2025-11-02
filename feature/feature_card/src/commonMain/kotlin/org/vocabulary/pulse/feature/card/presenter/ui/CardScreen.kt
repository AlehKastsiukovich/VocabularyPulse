package org.vocabulary.pulse.feature.card.presenter.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

const val ONE_MINUTE_IN_MILLIS = 60000

@Composable
fun CardScreen(
    onNavigateBack: () -> Unit,
) {
    CardScreen(
        onNavigateBack = onNavigateBack,
        onCorrect = { },
        onWrong = { }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardScreen(
    onNavigateBack: () -> Unit,
    onCorrect: () -> Unit,
    onWrong: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val progressAnimatable = remember { Animatable(0f) }
    var showAnswerButtons by remember { mutableStateOf(false) }
    var isFlipped by remember { mutableStateOf(false) }

    LaunchedEffect(isFlipped) {
        if (!isFlipped) {
            progressAnimatable.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = ONE_MINUTE_IN_MILLIS,
                    easing = LinearEasing
                )
            )
        } else {
            scope.launch {
                progressAnimatable.stop()
            }
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Training") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FlippableWordCard(
                word = "Text",
                translation = "Transl",
                isFlipped = isFlipped,
                onClick = {
                    if (!isFlipped) {
                        isFlipped = true
                    }
                },
                onAnimationFinished = {
                    showAnswerButtons = true
                }
            )

            Spacer(Modifier.height(48.dp))

            if (showAnswerButtons) {
                AnswerButtons(onCorrect = onCorrect, onWrong = onWrong)
            } else {
                LinearProgressIndicator(
                    progress = { progressAnimatable.value },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun FlippableWordCard(
    word: String,
    translation: String,
    isFlipped: Boolean,
    onClick: () -> Unit,
    onAnimationFinished: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val rotationY by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = FastOutSlowInEasing
        ),
        finishedListener = onAnimationFinished
    )

    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .graphicsLayer {
                this.rotationY = rotationY
                cameraDistance = 8 * density
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (rotationY <= 90f) {
                Text(text = word, style = MaterialTheme.typography.headlineLarge)
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { this.rotationY = 180f },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = translation, style = MaterialTheme.typography.headlineLarge)
                }
            }
        }
    }
}

@Composable
private fun AnswerButtons(
    onCorrect: () -> Unit,
    onWrong: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onCorrect,
            modifier = Modifier.size(width = 120.dp, height = 56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Green
        ) {
            Text("Correct")
        }
        Spacer(Modifier.width(24.dp))
        Button(
            onClick = onWrong,
            modifier = Modifier.size(width = 120.dp, height = 56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)) // Red
        ) {
            Text("Wrong")
        }
    }
}

@Preview
@Composable
private fun CardScreenPreviewInitial() {
    MaterialTheme {
        CardScreen(
            onNavigateBack = {},
            onCorrect = {},
            onWrong = {}
        )
    }
}

@Preview
@Composable
private fun CardScreenPreviewFlipped() {
    MaterialTheme {
        CardScreen(
            onNavigateBack = {},
            onCorrect = {},
            onWrong = {}
        )
    }
}
