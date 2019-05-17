package com.caesar_84.noblypostestapp.mainscreen.adapter

import android.view.View
import com.caesar_84.noblypostestapp.R
import com.caesar_84.noblypostestapp.commons.utils.Constants
import com.caesar_84.noblypostestapp.mainscreen.model.entities.Article
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.articles_list_item.*

class ArticlesInfoViewHolder(
        override val containerView: View
) : BaseAppViewHolder(containerView), LayoutContainer {
    private lateinit var viewModel: Article

    override fun bind(article: Article) {
        viewModel = article
        val context = parentView.context

        tvTitle.text = if (article.title.isNotBlank()) article.title else context.getString(R.string.no_title)
        tvWordCount.text = context.getString(R.string.words_count).format(article.wordCount)

        if (article.imageUrl.isNotBlank()) {
            Picasso
                    .get()
                    .load("${Constants.ApiService.NYT_IMAGES_BASE_URL}/${article.imageUrl}")
                    .placeholder(context.getDrawable(R.drawable.ic_launcher_foreground))
                    .error(context.getDrawable(R.drawable.ic_not_found_pic_108dp))
                    .fit()
                    .into(ivAvatar)
        } else {
            ivAvatar.setImageDrawable(context.getDrawable(R.drawable.ic_not_found_pic_108dp))
        }
    }

}