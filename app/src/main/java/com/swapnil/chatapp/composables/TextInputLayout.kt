package com.swapnil.chatapp.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import com.streamliners.compose.comp.textInput.state.TextInputState
import com.streamliners.compose.comp.textInput.state.hasError
import com.streamliners.compose.comp.textInput.state.preValidateAndUpdate
import com.streamliners.compose.ext.icons.Visibility
import com.streamliners.compose.ext.icons.VisibilityOff

@Composable
fun TextInputLayout(
    modifier: Modifier = Modifier.fillMaxWidth(),
    state: MutableState<TextInputState>,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    trailingIconButton: @Composable() (() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    showLabel: Boolean = true,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    doneAction: (() -> Unit)? = null,
    imeAction: ImeAction = doneAction?.let { ImeAction.Done } ?: ImeAction.Next,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    showPasswordVisibilityButton: Boolean = true,
    onTextChanged: () -> Unit = {},
    iconTint: Color = MaterialTheme.colorScheme.primary
) {
    val localFocusManager = LocalFocusManager.current

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier,
        label = if (!showLabel) null else {
            {
                Text(
                    text = buildAnnotatedString {
                        append(state.value.label)
                        if (!state.value.inputConfig.optional) {
                            withStyle(
                                SpanStyle(color = MaterialTheme.colorScheme.error)
                            ) {
                                append(" *")
                            }
                        }
                    }
                )
            }
        },
        value = state.value.value,
        onValueChange = {
            state.preValidateAndUpdate(it)
            onTextChanged()
        },
        leadingIcon = if (leadingIcon == null) null else {
            {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "",
                    tint = iconTint
                )
            }
        },
        trailingIcon = if (showPasswordVisibilityButton &&
            state.value.inputConfig.keyboardType == KeyboardType.Password
        ) {
            {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = "Password"
                    )
                }
            }
        } else if (trailingIcon == null && trailingIconButton == null) null else {
            {
                if (trailingIconButton != null) {
                    trailingIconButton()
                } else if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = ""
                    )
                }
            }
        },
        enabled = enabled,
        readOnly = readOnly,
        colors = colors,
        isError = state.value.hasError(),
        keyboardOptions = (keyboardOptions ?: KeyboardOptions.Default).copy(
            keyboardType = state.value.inputConfig.keyboardType,
            imeAction = if (!state.value.inputConfig.singleLine) ImeAction.Default else imeAction
        ),
        singleLine = state.value.inputConfig.singleLine,
        keyboardActions = keyboardActions ?: doneAction?.let {
            KeyboardActions(
                onDone = {
                    localFocusManager.clearFocus()
                    it()
                }
            )
        } ?: KeyboardActions(
            onNext = { localFocusManager.moveFocus(FocusDirection.Down) }
        ),
        visualTransformation = if (state.value.inputConfig.keyboardType == KeyboardType.Password && !passwordVisible)
            PasswordVisualTransformation()
        else
            visualTransformation,
        minLines = state.value.inputConfig.minLines,
        maxLines = state.value.inputConfig.maxLines,
        supportingText = state.value.error?.let {
            {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        } ?: state.value.supportingText?.let {
            { Text(text = it) }
        }
    )
}