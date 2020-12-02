package com.marvel.desafioandroid.features.character.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.marvel.desafioandroid.R

class CharacterActivity : AppCompatActivity(R.layout.activity_character) {

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, CharacterActivity::class.java)
        }
    }
}
