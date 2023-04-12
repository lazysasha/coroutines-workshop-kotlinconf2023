package com.kotlinconf.workshop.articles.tasks

import com.kotlinconf.workshop.articles.model.Article
import com.kotlinconf.workshop.articles.network.BlogService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// Task: Implement loading of articles and comments using flows (with progress!)
fun observeArticlesLoading(service: BlogService): Flow<Article> = flow {
    val articles = service.getArticleInfoList()
    articles.forEach { articleInfo ->
        emit(Article(articleInfo, service.getComments(articleInfo)))
    }
}