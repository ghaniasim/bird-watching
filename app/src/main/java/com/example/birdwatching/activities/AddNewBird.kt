package com.example.birdwatching

import android.app.Activity
import android.arch.persistence.room.Room
import android.content.Intent
import android.net.Uri
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
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_add_new_bird.*
import java.text.SimpleDateFormat
import java.util.*

class AddNewBird : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var database: BirdsListRoomDatabase
    private lateinit var date: String
    private var rarityOption: String = ""
    private var imageFilePath: String? = ""

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
        val imageButton = findViewById<Button>(R.id.addImageButton)
        imageButton.setBackgroundColor(getColor(R.color.colorPrimary))
        imageButton.setOnClickListener {
            getImageFromUser()
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

    private fun getImageFromUser() {
        ImagePicker.with(this)
            .crop(1f, 1f)
            .compress(1024)
            .maxResultSize(500, 500)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var filePath: String? =""

        if (resultCode == Activity.RESULT_OK) {
            filePath = ImagePicker.getFilePath(data)
            Toast.makeText(this, "Image included", Toast.LENGTH_LONG).show()
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
        imageFilePath = filePath
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
            if (TextUtils.isEmpty(notesEditText.text)){
                notes = getString(R.string.no_notes)
            }
            if (imageFilePath.equals("")) {
                imageFilePath = Uri.parse("android.resource://com.example.birdwatching/"
                        + R.drawable.no_image).toString()
            }
            val item = BirdsListItem(0, name, date, rarityOption, notes, imageFilePath)
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
