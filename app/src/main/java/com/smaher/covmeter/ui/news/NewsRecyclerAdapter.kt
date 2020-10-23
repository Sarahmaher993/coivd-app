package com.smaher.covmeter.ui.news

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smaher.covmeter.R
import com.smaher.covmeter.data.model.response.ArticleData


class NewsRecyclerAdapter() :
    RecyclerView.Adapter<NewsRecyclerAdapter.MyViewHolder>() {

    private val articles: ArrayList<ArticleData> = ArrayList()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var holderContext = view.context
        var title: TextView = view.findViewById(R.id.title)
        var author: TextView = view.findViewById(R.id.author)
        var imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_news_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val article = articles[position]
        holder.title.text = article.title
        holder.author.text = article.author
        Glide
            .with(holder.holderContext)
            .load(article.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.places_ic_clear)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setData(articles: ArrayList<ArticleData>) {
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }
}