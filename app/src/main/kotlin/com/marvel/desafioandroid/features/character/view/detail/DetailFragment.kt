package com.marvel.desafioandroid.features.character.view.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.marvel.desafioandroid.R
import com.marvel.desafioandroid.features.character.view.CharacterViewModel
import com.marvel.desafioandroid.features.character.view.CharacterViewState
import com.marvel.desafioandroid.features.character.view.CharacterCommand
import com.marvel.desafioandroid.features.character.view.list.component.CharactersAdapter
import kotlinx.android.synthetic.main.fragment_character_detail.*
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.android.synthetic.main.view_character_item.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_character_detail) {

    private val viewModel by viewModel<CharacterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        toolbar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }
        arguments?.let {
            viewModel.loadCharacterDetail(it.getLong(ID))
        }

    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { setupViewState(it) })
    }

    private fun setupViewState(viewState: CharacterViewState) {
        viewState.character?.let {
            Glide.with(this).load(it.imageUrl).into(imgHero)
            character_name.text = it.name
            nameHero.title = it.name
            character_description.text = it.description
        }
    }

    companion object {
        const val ID = "ID"
    }
}