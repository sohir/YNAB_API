package com.task.ynab.budgetdetails

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
import com.task.ynab.databinding.GroupListItemBinding
import com.task.ynab.models.BudgetDetailsResponseModel
import com.task.ynab.models.BudgetsListResponseModel

class GroupsListAdapter (private val actionsInterface:ComponentActionsClickListener ?=null) : RecyclerView.Adapter<GroupsListAdapter.ViewHolder>() {
    val Diff_CallBack = object : DiffUtil.ItemCallback<BudgetDetailsResponseModel.Data.Budget.CategoryGroup> (){
        override fun areItemsTheSame(oldItem: BudgetDetailsResponseModel.Data.Budget.CategoryGroup, newItem: BudgetDetailsResponseModel.Data.Budget.CategoryGroup): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: BudgetDetailsResponseModel.Data.Budget.CategoryGroup, newItem: BudgetDetailsResponseModel.Data.Budget.CategoryGroup): Boolean {
            return oldItem==newItem
        }

    }
    private val differ = AsyncListDiffer(this,Diff_CallBack)
   fun updateDate(newList:List<BudgetDetailsResponseModel.Data.Budget.CategoryGroup>){
       differ.submitList(newList)
   }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rootView = DataBindingUtil.inflate<GroupListItemBinding>(inflater,
            R.layout.group_list_item, parent,false)
        return ViewHolder(rootView,actionsInterface)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.group = differ.currentList[position]
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

    class ViewHolder (var view:GroupListItemBinding,private val characterActions: ComponentActionsClickListener?):
        RecyclerView.ViewHolder(view.root){
        fun bind(itemL1: BudgetDetailsResponseModel.Data.Budget.CategoryGroup){
            view.group = itemL1
            view.listener = characterActions

            view.categoryRecycler.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CategoriesListAdapter(actionsInterfacel2 = characterActions)
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

        fun onItemClicked(v: View,item: BudgetDetailsResponseModel.Data.Budget.CategoryGroup)
      //  fun onAccountClicked(v: View,item: BudgetDetailsResponseModel.Data.Budget.CategoryGroup)

    }
}