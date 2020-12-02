package com.marvel.desafioandroid.features.character.view.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.marvel.desafioandroid.R
import com.marvel.desafioandroid.features.character.view.CharacterViewModel
import com.marvel.desafioandroid.features.character.view.CharacterViewState
import com.marvel.desafioandroid.features.character.view.CharacterCommand
import com.marvel.desafioandroid.features.character.view.detail.DetailFragment
import com.marvel.desafioandroid.features.character.view.list.component.CharactersAdapter
import kotlinx.android.synthetic.main.fragment_character_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListFragment : Fragment(R.layout.fragment_character_list) {

    private val viewModel by viewModel<CharacterViewModel>()
    private val charactersAdapter by lazy { CharactersAdapter(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupClickListeners()
        viewModel.loadCharacters(0, 50)
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { setupViewState(it) })
        viewModel.command.observe(viewLifecycleOwner, Observer {
            when (it) {
                is CharacterCommand.showLoading -> {
                    showLoading()
                }
                is CharacterCommand.hideLoading -> {
                    hideLoading()
                }
            }
        })
    }

    private fun setupViewState(viewState: CharacterViewState) {
        viewState.characters?.let {
            charactersAdapter.list = it.toMutableList()
            with(rvCharacters) {
                adapter = charactersAdapter
                layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
            }
        }
    }

    private fun setupClickListeners() {
        charactersAdapter.onClick = {
            NavHostFragment.findNavController(this).navigate(R.id.goToDetail, Bundle().apply {
                putLong(DetailFragment.ID, it.id)
            })
        }
    }

    private fun hideLoading() {
        progressBar.isVisible = false
        rvCharacters.isVisible = true
    }

    private fun showLoading() {
        progressBar.isVisible = true
        rvCharacters.isVisible = false
    }
}