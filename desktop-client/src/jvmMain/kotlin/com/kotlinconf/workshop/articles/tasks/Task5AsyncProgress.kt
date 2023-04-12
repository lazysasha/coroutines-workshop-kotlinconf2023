package com.kotlinconf.workshop.articles.tasks

import com.kotlinconf.workshop.articles.model.Article
import com.kotlinconf.workshop.articles.network.BlogService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

// Task: Implement concurrent loading of comments using flows
fun observeArticlesConcurrently(service: BlogService): Flow<Article> {
    return channelFlow {
        val articleInfoList = service.getArticleInfoList()
        for (articleInfo in articleInfoList) {
            launch {
                send(Article(articleInfo, service.getComments(articleInfo))) // send vs. emit
            }
        }
    }
}
