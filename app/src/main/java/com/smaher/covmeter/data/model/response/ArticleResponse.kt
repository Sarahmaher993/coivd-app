package com.smaher.covmeter.data.model.response

data class ArticleResponse(
    val status: String?,
    val totalResults: Int?,
    val articles: List<ArticleData>?
)

data class ArticleSourceData(
    val id: Any?,
    val name: String?
)

data class ArticleData(
    val source: ArticleSourceData?,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)