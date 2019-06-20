package com.example.birdwatching

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private var birdsList: MutableList<BirdsListItem> = ArrayList()
    private lateinit var adapter: BirdsListAdapter
    private lateinit var database: BirdsListRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initialiseDatabase()
        fab_add_new_bird.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, AddNewBird::class.java)
            startActivityForResult(intent, 1)
        }
        recyclerViewBirdsList.layoutManager = LinearLayoutManager(this)
        adapter = BirdsListAdapter(birdsList)
        recyclerViewBirdsList.adapter = adapter
        loadBirdsListItems()
    }

    public override fun onResume() {
        super.onResume()
        loadBirdsListItems()
    }

    private fun initialiseDatabase() {
        database = Room.databaseBuilder(applicationContext, BirdsListRoomDatabase::class.java, "hs_db").build()
    }

    private fun loadBirdsListItems() {
        val handler = Handler(Handler.Callback {
            Toast.makeText(applicationContext, it.data.getString("message"), Toast.LENGTH_SHORT).show()
            adapter.update(birdsList)
            true
        })
        Thread(Runnable {
            birdsList = database.birdsListDao().getAll()
            val message = Message.obtain()
            if (birdsList.size > 0)
                message.data.putString("message", "Birds list is refreshed")
            else
                message.data.putString("message", "Birds list is empty!")
            handler.sendMessage(message)
        }).start()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
