package com.task.ynab.budgetdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.ynab.models.BudgetDetailsResponseModel
import com.task.ynab.network.ApiServices
import com.task.ynab.network.NetworkModule.GeneralToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BudgetDetailstRepo @Inject constructor(val apiServices: ApiServices) {
    private var _ResponseModel = MutableLiveData<BudgetDetailsResponseModel>(null)
    var responseModel: LiveData<BudgetDetailsResponseModel> = _ResponseModel

    suspend fun BudgetDetailsRequestServer(budgetId: String) {
        withContext(Dispatchers.IO) {
            val result = apiServices.getBudgetDetails(GeneralToken, budgetId).await()
            _ResponseModel.postValue(result)
            for ((groupItem, groupValue) in result.data.budget.categoryGroups.withIndex()) {
                for ((categoryIndex, categoryValue) in result.data.budget.categories.withIndex()) {
                    if (categoryValue.categoryGroupId.equals(groupValue.id)) {
                        groupValue.itsCategoriesList.add(categoryValue)
                    }
                }
            }

        }
    }
}



