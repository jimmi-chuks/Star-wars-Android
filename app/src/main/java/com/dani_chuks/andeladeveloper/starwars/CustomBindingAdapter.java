package com.dani_chuks.andeladeveloper.starwars;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dani_chuks.andeladeveloper.presentation_models.ItemModel;
import com.dani_chuks.andeladeveloper.starwars.adapters.SmallAdapter;

import java.util.List;

public class CustomBindingAdapter {

    @BindingAdapter("android:visibility")
    public static void setPaddingLeft(View view, boolean visible) {
        if(!visible){
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter("smallAdapterData")
    public static <T extends ItemModel> void setRecyclerViewAdapter(RecyclerView recyclerView, MutableLiveData<List<T>>  data) {
        SmallAdapter adapter = (SmallAdapter) recyclerView.getAdapter();
        if(adapter == null){
            adapter = new SmallAdapter<T>();
        }
        adapter.setItems(data.getValue());
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager == null) {
            layoutManager = new LinearLayoutManager(recyclerView.getContext());
            ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        }
        recyclerView.setLayoutManager(layoutManager);

    }

    @BindingAdapter("smallAdapterData")
    public static <T extends ItemModel> void setRecyclerViewAdapter(RecyclerView recyclerView, List<T>  data) {
        SmallAdapter adapter = (SmallAdapter) recyclerView.getAdapter();
        if(adapter == null){
            adapter = new SmallAdapter<T>();
        }
        adapter.setItems(data);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager == null) {
            layoutManager = new LinearLayoutManager(recyclerView.getContext());
            ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        }
        recyclerView.setLayoutManager(layoutManager);

    }


//    @BindingAdapter("smallAdapter")
//    public static <T extends ItemModel> void setRecyclerViewAdapter(RecyclerView recyclerView, List<T>  data) {
//        SmallAdapter adapter = (SmallAdapter) recyclerView.getAdapter();
//        if(adapter == null){
//            adapter = new SmallAdapter<T>(data);
//        }
//        recyclerView.setAdapter(adapter);
//        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//        if(layoutManager == null) {
//            layoutManager = new LinearLayoutManager(recyclerView.getContext());
//            ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
//        }
//
//    }


//    @BindingAdapter("text")
//    public static void setTime(TextView view, String text) {
//        // Important to break potential infinite loops.
//        if (view.getText() != text) {
//            view.getText() = text;
//        }
//    }
//
//    @InverseBindingAdapter("text")
//    public static String getTime(TextView view) {
//        return view.getText().toString();
//    }
//
//    @BindingAdapter("app:timeAttrChanged")
//    public static void setListeners(
//            MyView view, final InverseBindingListener attrChange) {
//        // Set a listener for click, focus, touch, etc.
//    }
}
