package com.marvel.desafioandroid.features.character.view.list.component

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvel.desafioandroid.R
import com.marvel.desafioandroid.features.character.data.domain.Character
import kotlinx.android.synthetic.main.view_character_item.view.*

class CharactersAdapter(val context: Context) : RecyclerView.Adapter<CharactersAdapter.ViewModel>() {

    var onClick: (Character) -> Unit = {}

    var list: MutableList<Character> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_character_item, parent, false)
        return ViewModel(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        val item = list[position]

        with(holder.itemView) {
            Glide.with(this).load(item.imageUrl).into(imgHero)
            txtName.text = item.name
            iconRight.setOnClickListener {
                onClick(item)
            }
        }
    }

    class ViewModel(view: View) : RecyclerView.ViewHolder(view)
}