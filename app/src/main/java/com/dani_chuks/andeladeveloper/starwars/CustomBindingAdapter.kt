package com.dani_chuks.andeladeveloper.starwars

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dani_chuks.andeladeveloper.presentation_models.MainModels
import com.dani_chuks.andeladeveloper.starwars.adapters.SmallAdapter

object CustomBindingAdapter {

    @BindingAdapter("android:visibility")
    @JvmStatic
    fun setPaddingLeft(view: View, visible: Boolean) {
        if (!visible) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    @BindingAdapter("smallAdapterData")
    @JvmStatic
    fun <T : MainModels> setRecyclerViewAdapter(recyclerView: RecyclerView, data: LiveData<List<MainModels>>?) {

        if((recyclerView.adapter as? SmallAdapter) == null){
            recyclerView.adapter = SmallAdapter()
        }
        data?.value?.let {
            (recyclerView.adapter as? SmallAdapter)?.submitList(it)
            var layoutManager: RecyclerView.LayoutManager? = recyclerView.layoutManager
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(recyclerView.context)
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            }
            recyclerView.layoutManager = layoutManager
        }
    }

    @BindingAdapter("smallAdapterData")
    @JvmStatic
    fun <T : MainModels> setRecyclerViewAdapter(recyclerView: RecyclerView, data: List<MainModels>?) {
        if((recyclerView.adapter as? SmallAdapter) == null){
            recyclerView.adapter = SmallAdapter()
        }
        data?.let {
            (recyclerView.adapter as? SmallAdapter)?.submitList(it)
            var layoutManager: RecyclerView.LayoutManager? = recyclerView.layoutManager
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(recyclerView.context)
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            }
            recyclerView.layoutManager = layoutManager
        }
    }
}
