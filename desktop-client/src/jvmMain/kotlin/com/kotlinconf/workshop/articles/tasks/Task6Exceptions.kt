package com.kotlinconf.workshop.articles.tasks

import com.kotlinconf.workshop.articles.model.Article
import com.kotlinconf.workshop.articles.network.BlogService
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

fun observeArticlesUnstable(service: BlogService): Flow<Article> = flow {
    val articleInfoList = service.getArticleInfoList()
    for (articleInfo in articleInfoList) {
        emit(Article(articleInfo, service.getComments(articleInfo)))
    }
}