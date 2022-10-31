package hu.bme.aut.android.wordcard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.wordcard.adapter.WordCollectionAdapter
import hu.bme.aut.android.wordcard.data.collection.WordCollection
import hu.bme.aut.android.wordcard.data.collection.WordCollectionDatabase
import hu.bme.aut.android.wordcard.databinding.ActivityCollectionBinding
import hu.bme.aut.android.wordcard.fragment.NewWordCollectionDialogFragment
import kotlin.concurrent.thread

class CollectionActivity() : AppCompatActivity(), WordCollectionAdapter.WordCollectionClickListener, NewWordCollectionDialogFragment.NewWordCollectionDialogListener {
    private lateinit var binding: ActivityCollectionBinding

    private lateinit var database: WordCollectionDatabase
    private lateinit var adapter: WordCollectionAdapter
    //private val prof_email = intent.getStringExtra("prof_email").toString()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_collection, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sign_out -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = WordCollectionDatabase.getDatabase(applicationContext)

        binding.fab.setOnClickListener {
            NewWordCollectionDialogFragment().show(
                supportFragmentManager,
                NewWordCollectionDialogFragment.TAG
            )
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = WordCollectionAdapter(this, this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        //loadItemsInBackground()

        binding.rvMain.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                (binding.rvMain.layoutManager as LinearLayoutManager).orientation
            )
        )
    }

    /*private fun loadItemsInBackground() {
        thread {
            val items = database.wordcollectionDao().getProfileCollection(prof_email)
            runOnUiThread {
                adapter.update(items)
            }
        }
    }*/

    override fun onItemChanged(item: WordCollection) {
        thread {
            database.wordcollectionDao().update(item)
            Log.d("CollectionActivity", "WordCollection update was successful")
        }
    }

    override fun onWordCollectionCreated(newItem: WordCollection) {
        thread {
            val insertId = database.wordcollectionDao().insert(newItem)
            newItem.id = insertId
            runOnUiThread {
                adapter.addItem(newItem)
            }
        }
    }

    override fun onWordCollectionDeleted(deleteItem: WordCollection) {
        thread {
            database.wordcollectionDao().deleteItem(deleteItem)
            runOnUiThread {
                adapter.deleteItem(deleteItem)
            }
        }
    }
}