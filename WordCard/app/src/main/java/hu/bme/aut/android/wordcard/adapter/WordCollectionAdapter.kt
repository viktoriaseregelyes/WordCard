package hu.bme.aut.android.wordcard.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.wordcard.WordActivity
import hu.bme.aut.android.wordcard.data.collection.WordCollection
import hu.bme.aut.android.wordcard.databinding.WordcollectionListBinding

class WordCollectionAdapter(private val listener: WordCollectionClickListener, var context: Context) :
    RecyclerView.Adapter<WordCollectionAdapter.WordCollectionViewHolder>() {

    private val items = mutableListOf<WordCollection>()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WordCollectionViewHolder(
            WordcollectionListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: WordCollectionViewHolder, position: Int) {
        val wordcollection = items[position]

        holder.binding.tvName.text = wordcollection.name
        holder.binding.tvDescription.text = wordcollection.description

        holder.binding.llcollection.setOnClickListener { listener.onWordCollectionSelected(wordcollection) }

        holder.binding.ibRemove.setOnClickListener { listener.onWordCollectionDeleted(wordcollection) }
    }

    override fun getItemCount(): Int = items.size

    interface WordCollectionClickListener {
        fun onItemChanged(item: WordCollection)
        fun onWordCollectionDeleted(deleteItem: WordCollection)
        fun onWordCollectionSelected(item: WordCollection)
    }

    fun addItem(item: WordCollection) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(wordcollections: List<WordCollection>) {
        items.clear()
        items.addAll(wordcollections)
        notifyDataSetChanged()
    }

    fun deleteItem(item: WordCollection) {
        items.remove(item)
        notifyDataSetChanged()
    }

    inner class WordCollectionViewHolder(val binding: WordcollectionListBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}