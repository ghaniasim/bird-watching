package com.example.birdwatching.activities

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.birdwatching.adapters.BirdsListAdapter
import com.example.birdwatching.R
import com.example.birdwatching.viewmodel.BirdViewModel
import com.example.birdwatching.model.BirdsListItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private var isAscending: Boolean = true
    private lateinit var birdViewModel: BirdViewModel
    private lateinit var adapter: BirdsListAdapter

    val liveDataSwitch = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab_add_new_bird.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, AddNewBird::class.java)
            startActivityForResult(intent, 1)
        }
        recyclerViewBirdsList.layoutManager = LinearLayoutManager(this)
        adapter = BirdsListAdapter()
        recyclerViewBirdsList.adapter = adapter

        birdViewModel = ViewModelProviders.of(this).get(BirdViewModel::class.java)
        birdViewModel.getAllBirdsDesc().observe(this,
            Observer<List<BirdsListItem>> { t -> adapter.update((t as MutableList<BirdsListItem>?)!!) })

        initSwipe()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort -> {
                isAscending = !isAscending
                reverseSortingOrder()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun reverseSortingOrder() {
        if(isAscending) {
            birdViewModel.getAllBirdsDesc().observe(this,
                Observer<List<BirdsListItem>> { t -> adapter.update((t as MutableList<BirdsListItem>?)!!) })
        }else {
            birdViewModel.getAllBirdsAsc().observe(this,
                Observer<List<BirdsListItem>> { t -> adapter.update((t as MutableList<BirdsListItem>?)!!) })
        }
    }

    private fun initSwipe() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if(isAscending) {
                    birdViewModel.delete(birdViewModel.getAllBirdsDesc().value!![position]?.id)
                } else {
                    birdViewModel.delete(birdViewModel.getAllBirdsAsc().value!![position]?.id)
                }
            }

            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                return false
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerViewBirdsList)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val newBird = BirdsListItem(
                0,
                data!!.getStringExtra(AddNewBird.EXTRA_NAME),
                data!!.getStringExtra(AddNewBird.EXTRA_DATE),
                data.getStringExtra(AddNewBird.EXTRA_RARITY),
                data.getStringExtra(AddNewBird.EXTRA_NOTES),
                data.getStringExtra(AddNewBird.EXTRA_FILE_PATH)
            )
            birdViewModel.insert(newBird)

            Toast.makeText(this, "Bird saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Bird not saved!", Toast.LENGTH_SHORT).show()
        }
    }
}
