package com.example.cityfootie_compose.util

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val mainDispatcher: CoroutineDispatcher
    val ioDispatcher: CoroutineDispatcher
    val cpuDispatcher: CoroutineDispatcher
}