package com.task.ynab.network

import com.task.ynab.models.BudgetDetailsResponseModel
import com.task.ynab.models.BudgetsListResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("budgets")
    fun getAllBudgets(
        @Header("Authorization")token:String,
        @Query("include_accounts")include_accounts:Boolean,
    ): Deferred<BudgetsListResponseModel>


    @GET("budgets/{id}")
    fun getBudgetDetails(
        @Header("Authorization")token:String,
        @Path("id")id:String,
    ): Deferred<BudgetDetailsResponseModel>
}