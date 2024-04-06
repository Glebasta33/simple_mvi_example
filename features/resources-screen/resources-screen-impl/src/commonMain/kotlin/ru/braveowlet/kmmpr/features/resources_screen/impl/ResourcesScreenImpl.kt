package ru.braveowlet.kmmpr.features.resources_screen.impl

import cafe.adriel.voyager.core.screen.Screen
import ru.braveowlet.kmmpr.features.resources_screen.api.ResourcesScreenApi

internal class ResourcesScreenImpl(
    private val tag : String
) : ResourcesScreenApi {

    override fun resourcesScreen(): Screen = ResourcesScreen(tag)
}
