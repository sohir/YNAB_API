package com.task.ynab.budgetslist

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.ynab.R
import com.task.ynab.databinding.BudgetListItemBinding
import com.task.ynab.models.BudgetsListResponseModel

class BudgetsListAdapter (private val actionsInterface:ComponentActionsClickListener ?=null) : RecyclerView.Adapter<BudgetsListAdapter.ViewHolder>() {
    val Diff_CallBack = object : DiffUtil.ItemCallback<BudgetsListResponseModel.Data.Budget> (){
        override fun areItemsTheSame(oldItem: BudgetsListResponseModel.Data.Budget, newItem: BudgetsListResponseModel.Data.Budget): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: BudgetsListResponseModel.Data.Budget, newItem: BudgetsListResponseModel.Data.Budget): Boolean {
            return oldItem==newItem
        }

    }
    private val differ = AsyncListDiffer(this,Diff_CallBack)
   fun updateDate(newList:List<BudgetsListResponseModel.Data.Budget>){
       differ.submitList(newList)
   }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rootView = DataBindingUtil.inflate<BudgetListItemBinding>(inflater,
            R.layout.budget_list_item, parent,false)
        return ViewHolder(rootView,actionsInterface)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.budget = differ.currentList[position]
        Log.v("list"," adapter items size: ${differ.currentList[position]} for the position ${position}}")
        holder.bind(differ.currentList[position])


        if (differ.currentList[position].isVisiable) {
            TransitionManager.beginDelayedTransition(holder.view.componentCardview, AutoTransition())
            holder.view.categoryArrowButton.setImageResource(R.drawable.ic_expand_more)
            holder.view.categoryRecycler.visibility = View.VISIBLE
        } else {
            TransitionManager.beginDelayedTransition(holder.view.componentCardview, AutoTransition())
            holder.view.categoryArrowButton.setImageResource(R.drawable.ic_exband_less)
            holder.view.categoryRecycler.visibility = View.GONE
        }

    }

    class ViewHolder (var view:BudgetListItemBinding,private val characterActions: ComponentActionsClickListener?):
        RecyclerView.ViewHolder(view.root){
        fun bind(itemL1: BudgetsListResponseModel.Data.Budget){
            view.budget = itemL1
            view.listener = characterActions

            view.categoryRecycler.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = AccountssListAdapter(actionsInterfacel2 = characterActions)
            }

            view.executePendingBindings()
        }
    }

/*
    interface CharactersListActionsClickListener {
        fun onItemClicked(v: View,item: BudgetsListResponseModel.Data.Budget)
    }
*/

    interface ComponentActionsClickListener {
        fun onSeeMoreClicked(v: View,item: BudgetsListResponseModel.Data.Budget)

        fun onItemClicked(v: View,item: BudgetsListResponseModel.Data.Budget)
        fun onAccountClicked(v: View,item: BudgetsListResponseModel.Data.Budget.Account)

    }
}