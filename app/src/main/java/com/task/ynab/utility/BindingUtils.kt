package com.task.ynab.utility

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.task.ynab.R
import com.task.ynab.budgetdetails.CategoriesListAdapter
import com.task.ynab.budgetslist.AccountssListAdapter
import com.task.ynab.models.BudgetDetailsResponseModel
import com.task.ynab.models.BudgetsListResponseModel

fun getProcessDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val imgUri = Uri.parse(uri).buildUpon().scheme("https").build()

    val requestOptions = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_baseline_broken_image_24)

    Glide.with(context)
        .setDefaultRequestOptions(requestOptions)
        .load(imgUri)
        .into(this)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgView.loadImage(imgUrl, getProcessDrawable(imgView.context))
}

@BindingAdapter("items")
fun bindRecyclerViewFilter(viewPager2: RecyclerView, data: List<BudgetsListResponseModel.Data.Budget.Account>) {
    val adapter = viewPager2.adapter as AccountssListAdapter
    adapter.updateDate(data)
}

@BindingAdapter("categories")
fun bindCategoriesRecyclerViewFilter(recycler: RecyclerView, data: List<BudgetDetailsResponseModel.Data.Budget.Category>) {
    val adapter = recycler.adapter as CategoriesListAdapter
    adapter.updateDate(data)
}