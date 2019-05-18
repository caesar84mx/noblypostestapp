package com.caesar_84.noblypostestapp.mainscreen.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.caesar_84.noblypostestapp.mainscreen.model.entities.Article

abstract class BaseAppViewHolder(val parentView: View): RecyclerView.ViewHolder(parentView) {
    abstract fun bind(article: Article)
}