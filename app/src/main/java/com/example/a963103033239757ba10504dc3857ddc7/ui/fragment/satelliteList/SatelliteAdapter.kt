package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a963103033239757ba10504dc3857ddc7.databinding.ItemSatelliteLayoutBinding
import com.example.a963103033239757ba10504dc3857ddc7.ui.model.satellite.SatelliteListModelItem
import java.util.*

class SatelliteAdapter(var itemList: MutableList<SatelliteListModelItem>) :
    ListAdapter<SatelliteListModelItem, SatelliteAdapter.ViewHolder>(ListDiffUtil()),
    Filterable {

    var itemListFiltered: MutableList<SatelliteListModelItem>

    init {
        itemListFiltered = itemList
        this.submitList(itemList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val isLastItem = itemList.lastIndex == position // hide divider for last item
        holder.bind(item, isLastItem)
    }

    class ViewHolder private constructor(private val binding: ItemSatelliteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SatelliteListModelItem, isLastItem: Boolean) {
            binding.model = item
            binding.lastItem = isLastItem
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSatelliteLayoutBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(text: CharSequence?): FilterResults {
                val charString: String = text.toString()
                if (charString.isEmpty()) {
                    itemListFiltered = itemList
                } else {
                    val filteredList: MutableList<SatelliteListModelItem> = ArrayList()
                    for (row in itemList) {
                        if (row.name.lowercase(Locale.getDefault())
                                .contains(charString.lowercase(Locale.getDefault()))
                        ) {
                            filteredList.add(row)
                        }
                    }
                    itemListFiltered = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = itemListFiltered
                return filterResults
            }

            override fun publishResults(text: CharSequence?, filterResults: FilterResults?) {
                itemListFiltered = filterResults!!.values as MutableList<SatelliteListModelItem>
                submitList(itemListFiltered)
            }
        }
    }


}

