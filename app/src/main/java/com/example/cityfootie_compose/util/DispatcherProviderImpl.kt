package com.example.cityfootie_compose.util

import kotlinx.coroutines.Dispatchers


class DispatcherProviderImpl : DispatcherProvider {
    override val mainDispatcher = Dispatchers.Main
    override val ioDispatcher = Dispatchers.IO //Llamadas a base datos
    override val cpuDispatcher = Dispatchers.Default //Calculos complejos
}