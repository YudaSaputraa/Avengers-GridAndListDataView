package com.kom.avengerslist.presentation.avengerslist.adapter

import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.kom.avengerslist.R
import com.kom.avengerslist.base.ViewHolderBinder
import com.kom.avengerslist.data.model.Avenger
import com.kom.avengerslist.databinding.ItemAvengerGridBinding


class AvengerGridItemViewHolder(
    private val binding: ItemAvengerGridBinding,
    private val listener : AvengersAdapter.OnItemClickedListener<Avenger>
) : ViewHolder(binding.root), ViewHolderBinder<Avenger> {
    override fun bind(item: Avenger) {
        item.let {
            binding.ivAvengersPhoto.load(it.profilePictUrl){
                crossfade(true)
                error(R.mipmap.ic_launcher)
            }
            binding.tvAvengersName.text = it.name
            itemView.setOnClickListener{
                listener.onItemClicked(item)
            }
        }
    }

}