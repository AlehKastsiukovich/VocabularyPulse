package org.vocabulary.pulse.feature.add.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddWordScreen(viewModel: AddWordScreenViewModel = koinViewModel()) {
    AddWordScreen(
        onNavigateBack = {},
        onAddWordClicked = { originalWord, translatedWord, example ->

        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddWordScreen(
    onNavigateBack: () -> Unit, 
    onAddWordClicked: (originalWord: String, translatedWord: String, example: String?) -> Unit
) {
    var originalWord by remember { mutableStateOf("") }
    var translatedWord by remember { mutableStateOf("") }
    var exampleUsage by remember { mutableStateOf("") } 

    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Новое слово") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WordInputTextField(
                value = originalWord,
                onValueChange = { originalWord = it },
                label = "Слово или фраза",
                leadingIconVector = Icons.Outlined.Language,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            WordInputTextField(
                value = translatedWord,
                onValueChange = { translatedWord = it },
                label = "Перевод",
                leadingIconVector = Icons.Filled.Translate,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            WordInputTextField(
                value = exampleUsage,
                onValueChange = { exampleUsage = it },
                label = "Пример использования (опционально)",
                isOptional = true,
                singleLine = false,
                minLines = 3,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onAddWordClicked(originalWord.trim(), translatedWord.trim(), exampleUsage.trim().takeIf { it.isNotEmpty() })
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = originalWord.isNotBlank() && translatedWord.isNotBlank()
            ) {
                Text("Добавить слово", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
private fun WordInputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIconVector: ImageVector? = null,
    isOptional: Boolean = false,
    singleLine: Boolean = true,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(if (isOptional) "$label (опционально)" else label) },
        leadingIcon = leadingIconVector?.let {
            { Icon(imageVector = it, contentDescription = null) }
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Очистить")
                }
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = if (singleLine) 1 else 5,
        shape = MaterialTheme.shapes.medium
    )
}

@Preview
@Composable
fun AddWordScreenPreview() {
    MaterialTheme {
        AddWordScreen(
            onNavigateBack = {},
            onAddWordClicked = { _, _, _ -> }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddWordScreenFilledPreview() {
    MaterialTheme {
        var originalWord by remember { mutableStateOf("Hello") }
        var translatedWord by remember { mutableStateOf("Привет") }
        var exampleUsage by remember { mutableStateOf("Hello there! How are you?") }

        Scaffold(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Новое слово") },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Назад")
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                WordInputTextField(
                    value = originalWord,
                    onValueChange = { originalWord = it },
                    label = "Слово или фраза",
                    leadingIconVector = Icons.Outlined.Language,
                )
                WordInputTextField(
                    value = translatedWord,
                    onValueChange = { translatedWord = it },
                    label = "Перевод",
                    leadingIconVector = Icons.Filled.Translate,
                )
                WordInputTextField(
                    value = exampleUsage,
                    onValueChange = { exampleUsage = it },
                    label = "Пример использования",
                    isOptional = true,
                    singleLine = false,
                    minLines = 3
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    enabled = originalWord.isNotBlank() && translatedWord.isNotBlank()
                ) {
                    Text("Добавить слово", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}
