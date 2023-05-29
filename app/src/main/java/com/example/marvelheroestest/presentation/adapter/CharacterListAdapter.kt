package com.example.marvelheroestest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelheroestest.data.model.Character
import com.example.marvelheroestest.databinding.CharacterItemListBinding

class CharacterListAdapter(
    private val characterClickListener: (Character) -> Unit
) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    private var characterList = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListAdapter.ViewHolder {
        val binding = CharacterItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListAdapter.ViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)
        holder.itemView.setOnClickListener { characterClickListener(character) }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun setCharacterList(characters: List<Character>) {
        characterList.clear()
        characterList.addAll(characters)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: CharacterItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(character: Character) {
            binding.characterName.text = character.name
            val thumbnailPath = character.thumbnail.path
            val thumbnailExtension = character.thumbnail.extension
            val imageURL = "${thumbnailPath}.${thumbnailExtension}"
            Glide
                .with(binding.root.context)
                .load(imageURL)
                .into(binding.characterThumbnail)
        }
    }
}