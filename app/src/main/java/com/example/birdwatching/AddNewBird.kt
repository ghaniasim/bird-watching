package com.example.birdwatching

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_new_bird.*
import java.text.SimpleDateFormat
import java.util.*

class AddNewBird : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var database: BirdsListRoomDatabase
    private lateinit var date: String
    private var rarityOption: String = ""

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
        ArrayAdapter.createFromResource(
            this,
            R.array.rarities,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        // Do nothing
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        rarityOption = spinner.selectedItem.toString()
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
        if (TextUtils.isEmpty(nameEditText.text)) {
            Toast.makeText(this, "Please enter bird's name", Toast.LENGTH_SHORT).show()
        } else {
            val name = nameEditText.text.toString()
            var notes = notesEditText.text.toString()
            val item = BirdsListItem(0, name, date, rarityOption, notes)
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
}
