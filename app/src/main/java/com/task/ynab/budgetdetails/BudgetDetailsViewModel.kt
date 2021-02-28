package com.task.ynab.budgetdetails

import android.app.Application
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.task.ynab.R
import com.task.ynab.budgetslist.BudgetsListRepo
import com.task.ynab.network.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class BudgetDetailsViewModel @ViewModelInject constructor(
    val application: Application,
    val repo: BudgetDetailstRepo,
    @Assisted private val savedStateHandle: SavedStateHandle

) : ViewModel() {

    val budgetId = savedStateHandle.get<String>("id")


    val job = SupervisorJob()
    val _viewModelScope = CoroutineScope(job + Dispatchers.Main)

    var responseModel = repo.responseModel


    private val _budgetTotal = MutableLiveData<Double>(0.0)
    val budgetTotal: LiveData<Double> = _budgetTotal

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState
    val errorMessage=MutableLiveData<String?>("")

init {
    Log.v("budget_details","${budgetId}")
    if (budgetId != null) {
        budgetDetails(budgetId)
    }
}
    fun budgetDetails(id:String) {
        _loadingState.value=(LoadingState.LOADING)

        _viewModelScope.launch {
            try {
                    repo.BudgetDetailsRequestServer (id)
                    Log.v("budget_details"," FROM SERVER")
                _loadingState.value=(LoadingState.LOADED)
           /*     if (responseModel.value?.data?.budgets?.size==0){
                    _loadingState.value=(LoadingState.error(null))
                    errorMessage.value= application.getString(R.string.no_data)
                }*/
              for ((item,index) in responseModel.value?.data?.budget?.categories!!)
                Log.v("budget_details", "result size is :${responseModel.value?.data?.budget?.categories?.size}")

            } catch (throwable: Throwable) {
                _loadingState.value=(LoadingState.error(null))
                errorMessage.value= application.getString(R.string.error_happens)
                Log.v("budget_details", "msg error: ${throwable.message}")
                when (throwable) {
                    is Exception -> {
                        Log.v("budget_details", "msg error Exception: ${throwable.message}")
                        _loadingState.value=(LoadingState.error(null))
                        errorMessage.value= application.getString(R.string.no_connection)
                    }
                }
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}