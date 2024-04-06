package ru.braveowlet.kmmpr.features.main_screen.impl

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.Flow
import org.koin.compose.koinInject
import ru.braveowlet.common.mvi.koin.MviScreen
import ru.braveowlet.common.mvi.koin.collectEvent
import ru.braveowlet.kmmpr.features.dogs_screen.api.DogsScreenApi
import ru.braveowlet.kmmpr.features.main_screen.impl.compose.MainScreenContent
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenAction
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenEffect
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenEvent
import ru.braveowlet.kmmpr.features.main_screen.impl.mvi.MainScreenState
import ru.braveowlet.kmmpr.features.resources_screen.api.ResourcesScreenApi
import ru.braveowlet.kmmpr.features.saved_dogs_screen.api.SavedDogsScreenApi

internal class MainScreen(
    tag: String
) : MviScreen<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState>(
    tag = tag
) {
    @Composable
    override fun MviContent(
        state: MainScreenState,
        eventFlow: Flow<MainScreenEvent>,
        acceptAction: (MainScreenAction) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val dogsScreenApi = koinInject<DogsScreenApi>()
        val savedDogsScreenApi = koinInject<SavedDogsScreenApi>()
        val resourcesScreenApi = koinInject<ResourcesScreenApi>()

        eventFlow.collectEvent { event ->
            when (event) {
                is MainScreenEvent.NavigateToDogsScreen ->
                    navigator.push(dogsScreenApi.dogsScreen())

                is MainScreenEvent.NavigateToSavedDogsScreen ->
                    navigator.push(savedDogsScreenApi.savedDogsScreen())

                is MainScreenEvent.NavigateToResourcesScreen ->
                    navigator.push(resourcesScreenApi.resourcesScreen())
            }
        }

        MainScreenContent(
            state = state,
            onClickButtonDogsScreen = { acceptAction(MainScreenAction.ClickButtonDogsScreen) },
            onClickButtonSavedDogsScreen = { acceptAction(MainScreenAction.ClickButtonSavedDogsScreen) },
            onClickButtonResourcesScreen = { acceptAction(MainScreenAction.ClickButtonResourcesScreen) },
        )
    }
}