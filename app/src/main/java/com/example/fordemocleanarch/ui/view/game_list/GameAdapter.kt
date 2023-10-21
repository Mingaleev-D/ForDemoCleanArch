package com.example.fordemocleanarch.ui.view.game_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.centerCropTransform
import com.example.fordemocleanarch.R
import com.example.fordemocleanarch.databinding.GameListItemBinding
import com.example.fordemocleanarch.domain.model.Game

/**
 * @author : Mingaleev D
 * @data : 21.10.2023
 */

class GameAdapter : ListAdapter<Game, GameAdapter.MyViewHolder>(diff) {

   private companion object {

      val diff = object : DiffUtil.ItemCallback<Game>() {
         override fun areItemsTheSame(oldItem: Game, newItem: Game) =
             oldItem::class == newItem::class

         override fun areContentsTheSame(oldItem: Game, newItem: Game) =
             oldItem == newItem

      }
   }

   inner class MyViewHolder(val binding: GameListItemBinding) :
       RecyclerView.ViewHolder(binding.root) {

      fun bind(itemGame: Game) {
         with(binding) {
            title.text = itemGame.title
            platform.text = itemGame.platform
            releaseDate.text = itemGame.releaseDate

            poster.load(itemGame.thumbnail)
         }
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      return MyViewHolder(
          GameListItemBinding.inflate(
              LayoutInflater.from(parent.context),
              parent,
              false
          )
      )
   }

   override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val item = getItem(position) ?: return

      holder.bind(item)
   }
}


fun ImageView.load(url: String){
   Glide.with(context)
       .load(url)
       .placeholder(R.drawable.baseline_error_24)
       .into(this)
}