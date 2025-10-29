package org.vocabulary.pulse.feature.add.presenter.ui

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
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.vocabulary.pulse.feature.add.presenter.model.AddWordEffect
import org.vocabulary.pulse.feature.add.presenter.model.AddWordIntent
import org.vocabulary.pulse.feature.add.presenter.model.AddWordState
import org.vocabulary.pulse.feature.add.presenter.viewmodel.AddWordScreenViewModel

@Composable
internal fun AddWordScreen(
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: AddWordScreenViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is AddWordEffect.NavigateBack -> {
                    focusManager.clearFocus()
                    onNavigateBack()
                }
                is AddWordEffect.NavigateToHome -> {
                    focusManager.clearFocus()
                    onNavigateToHome()
                }
                is AddWordEffect.Error -> {}
            }
        }
    }

    AddWordScreen(
        state = state,
        onNavigateBack = { viewModel.dispatchIntent(AddWordIntent.BackClicked) },
        onWordChanged = { viewModel.dispatchIntent(AddWordIntent.WordChanged(it)) },
        onTranslationChanged = { viewModel.dispatchIntent(AddWordIntent.TranslationChanged(it)) },
        onExamplesChanged = { viewModel.dispatchIntent(AddWordIntent.ExampleChanged(it)) },
        onAddWordClicked = { originalWord, translatedWord, examples ->
            viewModel.dispatchIntent(
                AddWordIntent.Submit(
                    word = originalWord,
                    translation = translatedWord,
                    examples = examples
                )
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddWordScreen(
    state: AddWordState,
    onNavigateBack: () -> Unit,
    onWordChanged: (String) -> Unit,
    onTranslationChanged: (String) -> Unit,
    onExamplesChanged: (List<String>) -> Unit,
    onAddWordClicked: (originalWord: String, translatedWord: String, examples: List<String>) -> Unit
) {
    var originalWord by remember { mutableStateOf(state.word) }
    var translatedWord by remember { mutableStateOf(state.translation) }
    val examples = remember { mutableStateListOf<String>().apply { addAll(state.examples) } }

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
                    IconButton(
                        onClick = onNavigateBack,
                        enabled = !state.isLoading
                    ) {
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
                onValueChange = {
                    originalWord = it
                    onWordChanged(it)
                },
                label = "Слово или фраза",
                leadingIconVector = Icons.Outlined.Language,
                enabled = !state.isLoading,
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
                onValueChange = {
                    translatedWord = it
                    onTranslationChanged(it)
                },
                label = "Перевод",
                leadingIconVector = Icons.Filled.Translate,
                enabled = !state.isLoading,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            examples.forEachIndexed { index, example ->
                WordInputTextField(
                    value = example,
                    onValueChange = {
                        examples[index] = it
                        onExamplesChanged(examples.toList())
                    },
                    label = "Пример использования ${index + 1} (опционально)",
                    isOptional = true,
                    singleLine = false,
                    minLines = 3,
                    enabled = !state.isLoading,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
            }

            IconButton(
                onClick = {
                    examples.add("")
                    onExamplesChanged(examples.toList())
                },
                enabled = !state.isLoading
            ) {
                Icon(Icons.Default.Add, contentDescription = "Добавить пример")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onAddWordClicked(
                        originalWord.trim(),
                        translatedWord.trim(),
                        examples.map { it.trim() }.filter { it.isNotEmpty() }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = state.isButtonEnabled
            ) {
                Text(
                    if (state.isLoading) "Добавление..." else "Добавить слово",
                    style = MaterialTheme.typography.titleMedium
                )
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
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    focusRequester: FocusRequester? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .let { modifier ->
                focusRequester?.let { modifier.focusRequester(it) } ?: modifier
            },
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
        enabled = enabled,
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
            state = AddWordState(),
            onNavigateBack = {},
            onWordChanged = {},
            onTranslationChanged = {},
            onExamplesChanged = {},
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
        val examples = remember { mutableStateListOf("Hello there! How are you?") }

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
                examples.forEachIndexed { index, example ->
                    WordInputTextField(
                        value = example,
                        onValueChange = { examples[index] = it },
                        label = "Пример использования ${index + 1}",
                        isOptional = true,
                        singleLine = false,
                        minLines = 3
                    )
                }
                IconButton(onClick = { examples.add("") }) {
                    Icon(Icons.Default.Add, contentDescription = "Добавить пример")
                }
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
