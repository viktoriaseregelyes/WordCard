package hu.bme.aut.android.wordcard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CollectionAdapter(private val listener: CollectionClickListener) :
    RecyclerView.Adapter<CollectionAdapter.ShoppingViewHolder>() {

    private val items = mutableListOf<Collection>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ShoppingViewHolder(
        CollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        // TODO implementation
    }

    override fun getItemCount(): Int = items.size

    interface ShoppingItemClickListener {
        fun onItemChanged(item: Collection)
    }

    inner class ShoppingViewHolder(val binding: CollectionBinding) : RecyclerView.ViewHolder(binding.root)
}