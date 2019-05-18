package com.caesar_84.noblypostestapp.mainscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.caesar_84.noblypostestapp.R
import com.caesar_84.noblypostestapp.commons.utils.LogHelper.logToConsole
import com.caesar_84.noblypostestapp.mainscreen.model.entities.Article

class ArticlesInfoRvAdapter : RecyclerView.Adapter<ArticlesInfoViewHolder>() {
    private var items: MutableList<Article> = mutableListOf()

    fun setData(data: List<Article>) {
        logToConsole("Setting articles data")

        if (items.isNotEmpty()) {
            items.clear()
            items.addAll(data)
            notifyDataSetChanged()
        } else {
            items.addAll(data)
            notifyDataSetChanged()
        }

        logToConsole("New articles data set")
        logToConsole(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ArticlesInfoViewHolder(
                    LayoutInflater
                            .from(parent.context)
                            .inflate(
                                    R.layout.articles_list_item,
                                    parent,
                                    false
                            ) as CardView
            )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ArticlesInfoViewHolder, position: Int) {
        holder.bind(items[position])
    }
}