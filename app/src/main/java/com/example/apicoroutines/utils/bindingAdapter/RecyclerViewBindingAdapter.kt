package com.example.apicoroutines.utils.bindingAdapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.feature.shared.adapter.CategoryAdapter
import com.example.apicoroutines.feature.shared.model.response.Category

@BindingAdapter("bindList")
fun bindCategoryList(view : RecyclerView, categoryList : ArrayList<Category>){
    if(categoryList.isEmpty())
        return
    val layoutManager = view.layoutManager
    val adapter = view.adapter

    if(layoutManager == null){
        view.layoutManager = LinearLayoutManager(view.context)
    }
    if(adapter == null){
        view.adapter = CategoryAdapter(categoryList)
    }
}