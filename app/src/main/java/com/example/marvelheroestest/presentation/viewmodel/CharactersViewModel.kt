package com.example.marvelheroestest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroestest.domain.usecase.CharacterUseCase
import com.example.marvelheroestest.data.datasource.Result
import java.lang.Exception
import com.example.marvelheroestest.data.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
    ): ViewModel() {

    private val _characters = MutableLiveData<Result<List<Character>>>()
    val characters: LiveData<Result<List<Character>>> get() = _characters

    private val _selectedCharacter = MutableLiveData<Result<Character?>>()
    val selectedCharacter: LiveData<Result<Character?>> get() = _selectedCharacter

    fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val charactersResult = characterUseCase.execute()
                if (charactersResult is Result.Success) {
                    _characters.value = Result.Success(charactersResult.data)
                } else if (charactersResult is Result.Error) {
                    _characters.value = Result.Error(charactersResult.exception)
                }
            } catch (e: Exception) {
                _characters.value = Result.Error(e)
            }
        }
    }
}