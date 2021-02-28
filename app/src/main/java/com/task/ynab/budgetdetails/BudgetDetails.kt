package com.task.ynab.budgetdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.ynab.R
import com.task.ynab.budgetslist.BudgetsListAdapter
import com.task.ynab.budgetslist.BudgetsListViewModel
import com.task.ynab.databinding.FragmentBudgetDetailsBinding
import com.task.ynab.databinding.FragmentBudgetListBinding
import com.task.ynab.models.BudgetDetailsResponseModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetDetails : Fragment(), GroupsListAdapter.ComponentActionsClickListener {

    private var _binding: FragmentBudgetDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<BudgetDetailsViewModel>()
    lateinit var groupsAdapter: GroupsListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBudgetDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        setHasOptionsMenu(true)
        groupsAdapter = GroupsListAdapter(this)
        binding.recycler.apply {
            adapter = groupsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        observeGroupsList()
        // Inflate the layout for this fragment
        return  return binding.root
    }


    fun observeGroupsList() {

        viewModel.responseModel.observe(viewLifecycleOwner, Observer {
            it?.let {
            //    allResponse.addAll(it)

                groupsAdapter.updateDate(it.data.budget.categoryGroups)

               // Log.v("list", "its from the fragment : ${it.size}")
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(
        v: View,
        item: BudgetDetailsResponseModel.Data.Budget.CategoryGroup
    ) {
        Log.v("details", "clicked item is : ${item.name}")
        item.isVisiable = if (item.isVisiable)false else true
        Log.v("details", "visiablity after:  ${item.isVisiable}")
        groupsAdapter.notifyDataSetChanged()
    }

}