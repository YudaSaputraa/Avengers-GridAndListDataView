package com.kom.avengerslist.presentation.avengerslist.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.kom.avengerslist.R
import com.kom.avengerslist.base.ViewHolderBinder
import com.kom.avengerslist.data.model.Avenger
import com.kom.avengerslist.databinding.ItemAvengerBinding


class AvengerListItemViewHolder(
    private val binding: ItemAvengerBinding,
    private val listener : AvengersAdapter.OnItemClickedListener<Avenger>
) : ViewHolder(binding.root), ViewHolderBinder<Avenger> {
    override fun bind(item: Avenger) {
        item.let {
            binding.ivAvengersPhoto.load(it.profilePictUrl) {
                // muncul gambar secara perlahan
                crossfade(true)
                // gambar fallback ketika gambar gagal diload
                error(R.mipmap.ic_launcher)
            }
            binding.tvAvengersName.text = it.name
            binding.tvAvengersPower.text = it.power
            itemView.setOnClickListener{
                listener.onItemClicked(item)
            }
        }
    }

}