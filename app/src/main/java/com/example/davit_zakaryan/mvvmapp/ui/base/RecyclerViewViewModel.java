package com.example.davit_zakaryan.mvvmapp.ui.base;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Davit_Zakaryan on 12/19/2017.
 */
public interface RecyclerViewViewModel {

	RecyclerView.LayoutManager getLayoutManager();

	RecyclerView.Adapter getAdapter();

}
