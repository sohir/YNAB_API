package com.task.ynab.hilt

import com.task.ynab.budgetslist.BudgetsListRepo
import com.task.ynab.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepoModule {
    @Provides
    @ActivityRetainedScoped
    fun provideUserBudgetsListRepo(apis: ApiServices):BudgetsListRepo{
        return BudgetsListRepo(apis)
    }



}