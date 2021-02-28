package com.task.ynab.network


data class LoadingState  constructor(val status: Status, val msg:String?=null) {
    companion object {
        val LOADED = LoadingState(Status.SUCCESS)
        val LOADING = LoadingState(Status.RUNNING)
        fun error(msg: String?) = LoadingState(Status.FAILED)
    }

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }
}


/*fun isConnectedNetwork(application: Application):Boolean{
    val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true

}*/

