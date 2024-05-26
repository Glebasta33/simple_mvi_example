package ru.braveowlet.kmmpr.features.dog_screens.impl.screens.saved_dogs_screen.mvi

import ru.braveowlet.common.mvi.general.MviState
import ru.braveowlet.kmmpr.components.dogs.domain.model.Dog

internal data class SavedDogsScreenState(
    val dogs: List<Dog>,
) : MviState {

    fun setDog(dogs: List<Dog>) = copy(
        dogs = dogs
    )

    companion object {
        val DEFAULT = SavedDogsScreenState(
            dogs = emptyList()
        )
    }
}
