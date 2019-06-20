package com.example.birdwatching

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_new_bird.*
import java.text.SimpleDateFormat
import java.util.*

class AddNewBird : AppCompatActivity() {

    private lateinit var database: BirdsListRoomDatabase
    private lateinit var date: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_bird)
        initialiseDatabase()
        date = getTimeStamp()
        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setBackgroundColor(getColor(R.color.colorPrimary))
        saveButton.setOnClickListener {
            saveNewBird()
        }
    }

    private fun getTimeStamp(): String {
        var rawDate = Date()
        val formatter = SimpleDateFormat("MMM dd yyyy HH:mm")
        return formatter.format(rawDate)
    }

    private fun initialiseDatabase() {
        database = Room.databaseBuilder(applicationContext, BirdsListRoomDatabase::class.java, "hs_db").build()
    }

    private fun saveNewBird() {
        val name = nameEditText.text.toString()
        val item = BirdsListItem(0, name, date)
        val handler = Handler(Handler.Callback {
            Toast.makeText(applicationContext, it.data.getString("message"), Toast.LENGTH_SHORT).show()
            true
        })
        Thread(Runnable {
            val id = database.birdsListDao().insert(item)
            item.id = id.toInt()
            val message = Message.obtain()
            message.data.putString("message", "Bird added to database!")
            handler.sendMessage(message)
        }).start()
        finish()
    }
}
