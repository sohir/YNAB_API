package com.task.ynab.budgetslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.ynab.databinding.FragmentBudgetListBinding
import com.task.ynab.models.BudgetsListResponseModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import javax.inject.Inject

@AndroidEntryPoint
class BudgetsListFragment : Fragment(),
    BudgetsListAdapter.ComponentActionsClickListener {
    private var _binding: FragmentBudgetListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<BudgetsListViewModel>()
   // private lateinit var mAdapter: BudgetsListAdapter


    lateinit var budgetAdapter:BudgetsListAdapter
    lateinit var accountAdapter:AccountssListAdapter
    var allResponse: ArrayList<BudgetsListResponseModel.Data.Budget> = arrayListOf()
    val accountsList: ArrayList<BudgetsListResponseModel.Data.Budget.Account> = arrayListOf()
//@Inject
//lateinit var helper:HelperClass
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBudgetListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        setHasOptionsMenu(true)

    budgetAdapter = BudgetsListAdapter(this)

        binding.recycler.apply {
            adapter = budgetAdapter
            layoutManager = LinearLayoutManager(context)
        }
        observeCharctersList()
        return binding.root
    }

    fun observeCharctersList() {
        viewModel.budgetssListModel.observe(viewLifecycleOwner, Observer {
            it?.let {
                allResponse.addAll(it)
                for ((item,index) in allResponse.withIndex()){
                    accountsList.addAll(allResponse[item].accounts)
                }
                budgetAdapter.updateDate(it)

                Log.v("list", "its from the fragment : ${it.size}")
            }
        })
    }

    override fun onSeeMoreClicked(v: View, item: BudgetsListResponseModel.Data.Budget) {
        findNavController().navigate(BudgetsListFragmentDirections.listToDetails(item.id,item.name))
    }

    override fun onItemClicked(v: View, item: BudgetsListResponseModel.Data.Budget) {
        Log.v("list", "clicked item is : ${item.name}")
        item.isVisiable = if (item.isVisiable)false else true
        Log.v("filter", "visiablity after:  ${item.isVisiable}")
        budgetAdapter.notifyDataSetChanged()

       // findNavController().navigate(CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDetailsFragment(item.name,item.description,"${item.thumbnail.path}.${item.thumbnail.extension}",item.id))
    }

    override fun onAccountClicked(v: View, item: BudgetsListResponseModel.Data.Budget.Account) {
        Toast.makeText(requireActivity(), "Go to ${item.name} account", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}