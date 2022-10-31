package hu.bme.aut.android.wordcard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.wordcard.data.word.Word
import hu.bme.aut.android.wordcard.databinding.WordListBinding

class WordAdapter(private val listener: WordClickListener, var context: Context) :
    RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private val items = mutableListOf<Word>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WordViewHolder(
        WordListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: WordAdapter.WordViewHolder, position: Int) {
        val word = items[position]

        holder.binding.tvWordFirst.text = word.first_language
        holder.binding.tvWordSecond.text = word.second_language

        //TODO EDIT holder.binding.llcollection.setOnClickListener { context.startActivity(Intent(context, WordActivity::class.java)) }

        holder.binding.ibRemoveWord.setOnClickListener { listener.onWordCollectionDeleted(word) }
    }

    override fun getItemCount(): Int = items.size

    interface WordClickListener {
        fun onItemChanged(item: Word)
        fun onWordCollectionDeleted(deleteItem: Word)
    }

    fun addItem(item: Word) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(words: List<Word>) {
        items.clear()
        items.addAll(words)
        notifyDataSetChanged()
    }

    fun deleteItem(item: Word) {
        items.remove(item)
        notifyDataSetChanged()
    }

    inner class WordViewHolder(val binding: WordListBinding) : RecyclerView.ViewHolder(binding.root)
}