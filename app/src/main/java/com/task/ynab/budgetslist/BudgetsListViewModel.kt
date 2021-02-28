package com.task.ynab.budgetslist

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.ynab.R
import com.task.ynab.network.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class BudgetsListViewModel @ViewModelInject constructor(
    val application: Application,
    val repo: BudgetsListRepo
) : ViewModel() {
    val job = SupervisorJob()
    val _viewModelScope = CoroutineScope(job + Dispatchers.Main)

    var responseModel = repo.responseModel
    var budgetssListModel = repo.budgetsListModel

    private val _budgetTotal = MutableLiveData<Double>(0.0)
    val budgetTotal: LiveData<Double> = _budgetTotal

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState
    val errorMessage=MutableLiveData<String?>("")

    init {
        budgetsList()
}
    fun budgetsList() {
        _loadingState.value=(LoadingState.LOADING)

        _viewModelScope.launch {
            try {
                    repo.BudgetsListRequestServer (true)
                    Log.v("list"," FROM SERVER")
                _loadingState.value=(LoadingState.LOADED)


                if (responseModel.value?.data?.budgets?.size==0){
                    _loadingState.value=(LoadingState.error(null))
                    errorMessage.value= application.getString(R.string.no_data)
                }
              for ((item,index) in responseModel.value?.data?.budgets!!)
                Log.v("list", "result size is :${responseModel.value?.data?.budgets?.size}")

            } catch (throwable: Throwable) {
                _loadingState.value=(LoadingState.error(null))
                errorMessage.value= application.getString(R.string.error_happens)
                Log.v("list", "msg error: ${throwable.message}")
                when (throwable) {
                    is Exception -> {
                        Log.v("list", "msg error Exception: ${throwable.message}")
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