package hu.bme.aut.android.wordcard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.wordcard.adapter.WordAdapter
import hu.bme.aut.android.wordcard.data.word.WordDatabase
import hu.bme.aut.android.wordcard.data.word.Word
import hu.bme.aut.android.wordcard.databinding.ActivityWordBinding
import hu.bme.aut.android.wordcard.fragment.LearnDialogFragment
import hu.bme.aut.android.wordcard.fragment.NewWordCollectionDialogFragment
import hu.bme.aut.android.wordcard.fragment.NewWordItemDialogFragment
import kotlin.concurrent.thread

class WordActivity : AppCompatActivity(), WordAdapter.WordClickListener, NewWordItemDialogFragment.NewWordDialogListener {
    private lateinit var binding: ActivityWordBinding

    private lateinit var database: WordDatabase
    private lateinit var adapter: WordAdapter

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_card, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sign_out -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.action_add_item -> {
                NewWordItemDialogFragment().show(
                    supportFragmentManager,
                    NewWordItemDialogFragment.TAG
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = WordDatabase.getDatabase(applicationContext)

        binding.fab.setOnClickListener {
            LearnDialogFragment().show(
                supportFragmentManager,
                LearnDialogFragment.TAG
            )
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = WordAdapter(this, this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        loadItemsInBackground()

        binding.rvMain.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                (binding.rvMain.layoutManager as LinearLayoutManager).orientation
            )
        )
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.wordDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(item: Word) {
        thread {
            database.wordDao().update(item)
            Log.d("WordActivity", "Word update was successful")
        }
    }

    override fun onWordCreated(newItem: Word) {
        thread {
            val insertId = database.wordDao().insert(newItem)
            newItem.id = insertId
            runOnUiThread {
                adapter.addItem(newItem)
            }
        }
    }
    override fun onWordCollectionDeleted(deleteItem: Word) {
        thread {
            database.wordDao().deleteItem(deleteItem)
            runOnUiThread {
                adapter.deleteItem(deleteItem)
            }
        }
    }
}