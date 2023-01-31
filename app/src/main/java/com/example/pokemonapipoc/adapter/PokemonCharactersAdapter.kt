package com.example.pokemonapipoc.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokemonapipoc.R
import com.example.pokemonapipoc.model.PokemonCharacters

class PokemonCharactersAdapter(
    private val pokeMonList: ArrayList<PokemonCharacters>
) : RecyclerView.Adapter<PokemonCharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemoncustomitems, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPosition = pokeMonList[position]
        with(holder) {
            uiTvPokemonName.text = itemPosition.name.toString()
            uiTvPokemonExperience.text = itemPosition.baseExperience.toString()
            uiTvPokemonHeight.text = itemPosition.height.toString()
            uiTvPokemonWeight.text = itemPosition.weight.toString()
            uiIvPokemonImages.setImageResource(R.drawable.pokemonbackground)
//            uiIvPokemonImages.let{
//                Glide.with(it)
//                    .load(itemPosition.sprites?.frontShiny)
////                    .placeholder(R.drawable.ic_launcher_background)
//                    .listener(object : RequestListener<Drawable?> {
//                        override fun onLoadFailed(
//                            e: GlideException?,
//                            model: Any?,
//                            target: Target<Drawable?>?,
//                            isFirstResource: Boolean
//                        ): Boolean {
//                            Log.d("Glide", "Load failed: " + e?.message)
//                           holder.uiIvImageProgress.visibility = View.VISIBLE
//                            return false
//                        }
//
//                        override fun onResourceReady(
//                            resource: Drawable?,
//                            model: Any?,
//                            target: Target<Drawable?>?,
//                            dataSource: DataSource?,
//                            isFirstResource: Boolean
//                        ): Boolean {
//                            holder.uiIvImageProgress.visibility = View.GONE
//                            return true
//                        }
//                    })
//                    .into(it)
//            }
        }
    }

    override fun getItemCount(): Int {
        return pokeMonList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onCharactersChanged(pokemonCharacters: List<PokemonCharacters>) {
        Log.e("res", pokemonCharacters.toString())
        pokeMonList.clear()
        pokeMonList.addAll(pokemonCharacters)
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val uiTvPokemonName:TextView = itemView.findViewById(R.id.uiTvPokemonName)
        val uiTvPokemonExperience: TextView = itemView.findViewById(R.id.uiTvPokemonExperience)
        val uiTvPokemonHeight:TextView = itemView.findViewById(R.id.uiTvPokemonHeight)
        val uiTvPokemonWeight:TextView = itemView.findViewById(R.id.uiTvPokemonWeight)
        val uiIvPokemonImages:ImageView = itemView.findViewById(R.id.uiIvPokemonImages)
        val uiIvImageProgress:ProgressBar = itemView.findViewById(R.id.uiIvImageProgress)
    }
}