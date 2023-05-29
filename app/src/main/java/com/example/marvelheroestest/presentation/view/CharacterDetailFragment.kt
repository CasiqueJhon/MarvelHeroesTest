package com.example.marvelheroestest.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.marvelheroestest.data.datasource.Result
import com.example.marvelheroestest.databinding.FragmentCharacterDetailBinding
import com.example.marvelheroestest.presentation.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding: FragmentCharacterDetailBinding get() = _binding ?: error("Something went wrong")

    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showCharacterDetail()
    }

    private fun showCharacterDetail() {
        val characterId = arguments?.getInt("characterId", -1)
        val characterName = arguments?.getString("characterName", "")
        val characterDescription = arguments?.getString("characterDescription", "")
        val characterThumbnail = arguments?.getString("characterThumbnail", "")
        if (characterId != null && characterId != -1) {
            binding.detailName.text = characterName
            binding.detailDescription.text = characterDescription

            Glide.with(this)
                .load(characterThumbnail)
                .into(binding.detailImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}