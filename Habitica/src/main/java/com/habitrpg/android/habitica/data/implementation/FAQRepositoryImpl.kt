package com.habitrpg.android.habitica.data.implementation

import com.habitrpg.android.habitica.data.ApiClient
import com.habitrpg.android.habitica.data.FAQRepository
import com.habitrpg.android.habitica.data.local.FAQLocalRepository
import com.habitrpg.android.habitica.models.FAQArticle

import io.reactivex.Flowable
import io.realm.RealmResults


class FAQRepositoryImpl(localRepository: FAQLocalRepository, apiClient: ApiClient) : ContentRepositoryImpl<FAQLocalRepository>(localRepository, apiClient), FAQRepository {

    override fun getArticles(): Flowable<RealmResults<FAQArticle>> {
        return localRepository.articles
    }
}
