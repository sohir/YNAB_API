package com.task.ynab.budgetdetails

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
import com.task.ynab.databinding.CategoresListItemBinding
import com.task.ynab.models.BudgetDetailsResponseModel
import com.task.ynab.models.BudgetsListResponseModel

class CategoriesListAdapter (private val actionsInterfacel2: GroupsListAdapter.ComponentActionsClickListener?=null) : RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {
    val Diff_CallBack = object : DiffUtil.ItemCallback<BudgetDetailsResponseModel.Data.Budget.Category> (){
        override fun areItemsTheSame(oldItem: BudgetDetailsResponseModel.Data.Budget.Category, newItem: BudgetDetailsResponseModel.Data.Budget.Category): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: BudgetDetailsResponseModel.Data.Budget.Category, newItem: BudgetDetailsResponseModel.Data.Budget.Category): Boolean {
            return oldItem==newItem
        }

    }
    private val differ = AsyncListDiffer(this,Diff_CallBack)
   fun updateDate(newList:List<BudgetDetailsResponseModel.Data.Budget.Category>){
       differ.submitList(newList)
   }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rootView = DataBindingUtil.inflate<CategoresListItemBinding>(inflater,
            R.layout.categores_list_item, parent,false)
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

    class ViewHolder (var view:CategoresListItemBinding,private val characterActions: GroupsListAdapter.ComponentActionsClickListener?):
        RecyclerView.ViewHolder(view.root){
        fun bind(itemL1: BudgetDetailsResponseModel.Data.Budget.Category){
            view.data = itemL1
            view.listener = characterActions
            view.executePendingBindings()
        }
    }

/*    interface CharactersListActionsClickListener {
        fun onItemClicked(v: View,item: BudgetDetailsResponseModel.Data.Budget.Category)
    }*/

}