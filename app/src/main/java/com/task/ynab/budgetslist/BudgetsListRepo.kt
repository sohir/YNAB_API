package com.task.ynab.budgetslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.ynab.models.BudgetsListResponseModel
import com.task.ynab.network.ApiServices
import com.task.ynab.network.NetworkModule.GeneralToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BudgetsListRepo @Inject constructor(val apiServices: ApiServices) {
    private var _ResponseModel = MutableLiveData<BudgetsListResponseModel>(null)
    var responseModel : LiveData<BudgetsListResponseModel> = _ResponseModel

    private var _BudgetssListModel = MutableLiveData<ArrayList<BudgetsListResponseModel.Data.Budget>>(null)
    var budgetsListModel : LiveData<ArrayList<BudgetsListResponseModel.Data.Budget>> = _BudgetssListModel


    suspend fun BudgetsListRequestServer(include_accounts:Boolean){
        withContext(Dispatchers.IO){
           val result = apiServices.getAllBudgets(GeneralToken,include_accounts).await()
           _ResponseModel.postValue(result)
            _BudgetssListModel.postValue(result.data.budgets as ArrayList<BudgetsListResponseModel.Data.Budget>)
        }
    }



}