package com.kom.avengerslist.presentation.avengerslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kom.avengerslist.base.ViewHolderBinder
import com.kom.avengerslist.data.model.Avenger
import com.kom.avengerslist.databinding.ItemAvengerBinding
import com.kom.avengerslist.databinding.ItemAvengerGridBinding

class AvengersAdapter(val listener: OnItemClickedListener<Avenger>, val listMode: Int = MODE_LIST) :
    RecyclerView.Adapter<ViewHolder>() {
    companion object {
        const val MODE_GRID = 1
        const val MODE_LIST = 0
    }

    private val asyncDataDiffer = AsyncListDiffer<Avenger>(
        this, object : DiffUtil.ItemCallback<Avenger>() {
            override fun areItemsTheSame(oldItem: Avenger, newItem: Avenger): Boolean {
                // membandingkan apakah item tersebut sama atau tidak
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Avenger, newItem: Avenger): Boolean {
                // yang dibandingkan adalah contentnya
                return oldItem.hashCode() == newItem.hashCode()
            }

        }
    )

    fun submitData(data: List<Avenger>) {
        asyncDataDiffer.submitList(data)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // membuat instance of View Holder
        return if (listMode == MODE_GRID) AvengerGridItemViewHolder(
            ItemAvengerGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), listener
        ) else {
            AvengerListItemViewHolder(
                ItemAvengerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                ), listener
            )
        }
    }

    override fun getItemCount(): Int = asyncDataDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder !is ViewHolderBinder<*>) return
        (holder as ViewHolderBinder<Avenger>).bind(asyncDataDiffer.currentList[position])
    }


    interface OnItemClickedListener<T> {
        fun onItemClicked(item: T)
    }
}