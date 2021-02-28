package com.task.ynab.budgetslist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.ynab.R
import com.task.ynab.databinding.AccountListItemBinding
import com.task.ynab.models.BudgetsListResponseModel

class AccountssListAdapter (private val actionsInterfacel2: BudgetsListAdapter.ComponentActionsClickListener?=null) : RecyclerView.Adapter<AccountssListAdapter.ViewHolder>() {
    val Diff_CallBack = object : DiffUtil.ItemCallback<BudgetsListResponseModel.Data.Budget.Account> (){
        override fun areItemsTheSame(oldItem: BudgetsListResponseModel.Data.Budget.Account, newItem: BudgetsListResponseModel.Data.Budget.Account): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: BudgetsListResponseModel.Data.Budget.Account, newItem: BudgetsListResponseModel.Data.Budget.Account): Boolean {
            return oldItem==newItem
        }

    }
    private val differ = AsyncListDiffer(this,Diff_CallBack)
   fun updateDate(newList:List<BudgetsListResponseModel.Data.Budget.Account>){
       differ.submitList(newList)
   }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rootView = DataBindingUtil.inflate<AccountListItemBinding>(inflater,
            R.layout.account_list_item, parent,false)
        return ViewHolder(rootView,actionsInterfacel2)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.data = differ.currentList[position]
        Log.v("list"," adapter items size: ${differ.currentList[position]} for the position ${position}}")
        holder.bind(differ.currentList[position])
   /*     holder.itemView.setOnClickListener {
            actionsInterfacel2?.onAccountClicked()
        }*/
    }

    class ViewHolder (var view:AccountListItemBinding,private val characterActions: BudgetsListAdapter.ComponentActionsClickListener?):
        RecyclerView.ViewHolder(view.root){
        fun bind(itemL1: BudgetsListResponseModel.Data.Budget.Account){
            view.data = itemL1
            view.listener = characterActions
            view.executePendingBindings()
        }
    }

    interface CharactersListActionsClickListener {
        fun onItemClicked(v: View,item: BudgetsListResponseModel.Data.Budget)
    }

}