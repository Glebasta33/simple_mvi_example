package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.intents

import ru.braveowlet.common.mvi.general.models.MviState
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal data class SavedDogsScreenState(
    val dogs: List<Dog>,
) : MviState
