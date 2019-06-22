package com.example.birdwatching

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class BirdsListAdapter(var birdsList: MutableList<BirdsListItem>) :
    RecyclerView.Adapter<BirdsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = birdsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: BirdsListItem = birdsList[position]
        holder.nameTextView.text = item.name
        holder.dateTextView.text = item.date
        holder.rareTextView.text = item.rarity
        holder.notesTextView.text = item.notes
        holder.listItemImageView.setImageURI(Uri.parse(item.file))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.nameTextView
        val dateTextView: TextView = view.dateTextView
        val rareTextView: TextView = view.rareTextView
        val notesTextView: TextView = view.notesTextView
        val listItemImageView: ImageView = view.listItemImageView
    }

    fun update(newList: MutableList<BirdsListItem>) {
        birdsList = newList
        notifyDataSetChanged()
    }
}