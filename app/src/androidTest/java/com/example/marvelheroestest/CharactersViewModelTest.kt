package com.example.marvelheroestest

import androidx.lifecycle.Observer
import androidx.test.runner.screenshot.Screenshot.capture
import com.example.marvelheroestest.data.model.Thumbnail
import com.example.marvelheroestest.domain.usecase.CharacterUseCase
import com.example.marvelheroestest.presentation.viewmodel.CharactersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: CharactersViewModel
    private lateinit var characterUseCase: CharacterUseCase

    @Mock
    private lateinit var charactersObserver: Observer<Result<List<Character>>>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        characterUseCase = Mockito.mock(CharacterUseCase::class.java)
        viewModel = CharactersViewModel(characterUseCase)
        viewModel.characters.observeForever(charactersObserver)
    }

    @Test
    fun `fetchCharacters should update characters LiveData with success result`() =
        testScope.runBlockingTest {
            // Given
            val characters = listOf(
                Character(1, "Character 1", "Description 1", Thumbnail("path1", "extension1"))
            )
            val charactersResult = Result.Success(characters)
            Mockito.`when`(characterUseCase.execute()).thenReturn(charactersResult)

            // When
            viewModel.fetchCharacters()

            // Then
            val captor = ArgumentCaptor.forClass(Result::class.java)
            captor.run {
                Mockito.verify(charactersObserver, Mockito.times(1)).onChanged(capture() as Result<List<Character>>)
                Assert.assertEquals(charactersResult, value)
            }
        }

    @Test
    fun `fetchCharacters should update characters LiveData with error result`() =
        testScope.runBlockingTest {
            // Given
            val error = Exception("Error fetching characters")
            val errorResult = Result.Error(error)
            Mockito.`when`(characterUseCase.execute()).thenReturn(errorResult)

            // When
            viewModel.fetchCharacters()

            // Then
            val captor = ArgumentCaptor.forClass(Result::class.java)
            captor.run {
                Mockito.verify(charactersObserver, Mockito.times(1)).onChanged(capture() as Result<List<Character>>)
                Assert.assertEquals(errorResult, value)
            }
        }
}