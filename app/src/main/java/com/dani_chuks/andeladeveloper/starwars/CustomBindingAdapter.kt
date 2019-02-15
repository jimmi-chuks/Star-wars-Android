package com.dani_chuks.andeladeveloper.starwars

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dani_chuks.andeladeveloper.presentation_models.ItemModel
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
    fun <T : ItemModel> setRecyclerViewAdapter(recyclerView: RecyclerView, data: MutableLiveData<List<ItemModel>>?) {
        var adapter = recyclerView.adapter as SmallAdapter?
        if (adapter == null) {
            adapter = SmallAdapter()
        }
        data?.value?.let {
            adapter.setItems(data.value)
            recyclerView.adapter = adapter
            var layoutManager: RecyclerView.LayoutManager? = recyclerView.layoutManager
            if (layoutManager == null) {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(recyclerView.context)
                layoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
            }
            recyclerView.layoutManager = layoutManager
        }
    }

    @BindingAdapter("smallAdapterData")
    @JvmStatic
    fun <T : ItemModel> setRecyclerViewAdapter(recyclerView: RecyclerView, data: List<ItemModel>?) {
        var adapter = recyclerView.adapter as SmallAdapter?
        if (adapter == null) {
            adapter = SmallAdapter()
        }
        data?.let {
            adapter.setItems(data)
            recyclerView.adapter = adapter
            var layoutManager: RecyclerView.LayoutManager? = recyclerView.layoutManager
            if (layoutManager == null) {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(recyclerView.context)
                layoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
            }
            recyclerView.layoutManager = layoutManager
        }

    }
}
