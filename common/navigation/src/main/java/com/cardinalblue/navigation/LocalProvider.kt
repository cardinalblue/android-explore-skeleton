package com.cardinalblue.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import kotlin.reflect.KClass

object CompositionLocals {
    val compositionLocals = mutableMapOf<KClass<*>, ProvidableCompositionLocal<*>>()

    inline fun <reified T> ofType() = compositionLocals[T::class] as ProvidableCompositionLocal<T>? ?:
        compositionLocalOf<T> {
            error("Something No provider found for ${T::class.simpleName}! waaa ${T::class.typeParameters.joinToString { it.name }}")
        }
            .also { compositionLocals[T::class] = it }

    @Composable
    inline fun <reified T> current(): T = ofType<T>().current
}