package com.cardinalblue.moviedetails.impl.credits.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinalblue.domain.Credit
import com.cardinalblue.moviedetails.api.MovieDetailsInput
import com.cardinalblue.moviedetails.impl.credits.usecase.GetCredits
import com.cardinalblue.moviedetails.impl.di.MovieId
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreditsScreenViewModel @AssistedInject constructor(
    @Assisted val input: MovieDetailsInput,
    private val getCredits: GetCredits
) : ViewModel() {
    @AssistedFactory
    interface Factory {
        fun create(input: MovieDetailsInput): CreditsScreenViewModel
    }

    private val _credits = MutableStateFlow<List<Credit>>(emptyList())
    val credits = _credits.asStateFlow()

    init {
        viewModelScope.launch {
            _credits.value = getCredits(input.movieId)
        }
    }
}