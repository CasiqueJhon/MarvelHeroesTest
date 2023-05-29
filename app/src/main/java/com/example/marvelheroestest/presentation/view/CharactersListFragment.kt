package com.example.marvelheroestest.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.marvelheroestest.R
import com.example.marvelheroestest.data.model.Character
import com.example.marvelheroestest.databinding.FragmentCharactersListBinding
import com.example.marvelheroestest.domain.usecase.CharacterUseCase
import com.example.marvelheroestest.presentation.adapter.CharacterListAdapter
import com.example.marvelheroestest.presentation.viewmodel.CharactersViewModel
import com.example.marvelheroestest.data.datasource.Result
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    @Inject
    lateinit var characterUseCase: CharacterUseCase

    private var _binding: FragmentCharactersListBinding? = null
    private val binding: FragmentCharactersListBinding get() = _binding ?: error("Something went wrong")

    private val viewModel: CharactersViewModel by viewModels()
    private val characterListAdapter = CharacterListAdapter { navigateToDetail (it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        observeCharacterList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUp() {
        binding.characterList.adapter = characterListAdapter
        viewModel.fetchCharacters()
    }

    private fun observeCharacterList() {
        viewModel.characters.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    val characterList = result.data
                    characterListAdapter.setCharacterList(characterList)
                }
                is Result.Error -> {
                    val errorMessage = result.exception.message
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToDetail(character: Character) {
        val fragment = CharacterDetailFragment()
        val thumbnailPath = character.thumbnail.path
        val thumbnailExtension = character.thumbnail.extension
        val imageUri = "${thumbnailPath}.${thumbnailExtension}"

        val args = Bundle()
        args.putInt("characterId", character.id)
        args.putString("characterName", character.name)
        args.putString("characterDescription", character.description)
        args.putString("characterThumbnail", imageUri)
        fragment.arguments = args

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}