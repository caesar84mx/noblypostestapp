package com.caesar_84.noblypostestapp.mainscreen.ui

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.caesar_84.noblypostestapp.R
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article
import com.caesar_84.noblypostestapp.commons.utils.Constants
import com.caesar_84.noblypostestapp.commons.utils.LogHelper
import com.caesar_84.noblypostestapp.commons.utils.LogHelper.logToConsole
import com.squareup.picasso.Picasso

class ArticlesInfoRvAdapter : RecyclerView.Adapter<ArticlesInfoRvAdapter.ArticlesInfoViewHolder>() {
    private var articleInfoList: MutableList<Article> = mutableListOf()

    fun setArticlesInfoList(list: List<Article>) {
        logToConsole("Setting articles list")

        if (articleInfoList.isNotEmpty()) {
            articleInfoList.clear()
            articleInfoList.addAll(list)
            notifyDataSetChanged()
        } else {
            articleInfoList.addAll(list)
            notifyDataSetChanged()
        }

        logToConsole("New articles list set")
        logToConsole(articleInfoList)
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

    override fun getItemCount() = articleInfoList.size

    override fun onBindViewHolder(holder: ArticlesInfoViewHolder, position: Int) {
        val articleInfo = articleInfoList[position]
        val context = holder.cardView.context

        val tvTitle = holder.cardView.findViewById<TextView>(R.id.tvTitle)
        val tvWordCount = holder.cardView.findViewById<TextView>(R.id.tvWordCount)
        val ivAvatar = holder.cardView.findViewById<ImageView>(R.id.ivAvatar)

        tvTitle.text = if (articleInfo.title.isNotBlank()) articleInfo.title else context.getString(R.string.no_title)
        tvWordCount.text = holder.cardView.context.getString(R.string.words_count).format(articleInfo.wordCount)

        if (articleInfo.imageUrl.isNotBlank()) {
            Picasso
                .get()
                .load("${Constants.ApiService.NYT_IMAGES_BASE_URL}/${articleInfo.imageUrl}")
                .placeholder(context.getDrawable(R.drawable.ic_launcher_foreground))
                .error(context.getDrawable(R.drawable.ic_not_found_pic_108dp))
                .fit()
                .into(ivAvatar)
        } else {
            ivAvatar.setImageDrawable(context.getDrawable(R.drawable.ic_not_found_pic_108dp))
        }
    }

    inner class ArticlesInfoViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}