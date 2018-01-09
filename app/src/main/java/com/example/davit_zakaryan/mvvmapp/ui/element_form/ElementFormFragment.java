package com.example.davit_zakaryan.mvvmapp.ui.element_form;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davit_zakaryan.mvvmapp.App;
import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.databinding.FragmentElementFormBinding;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Davit_Zakaryan on 1/9/2018.
 */
public class ElementFormFragment extends BaseFragment {

	@Inject
	ElementFormViewModel elementFormViewModel;

	public ElementFormFragment() {
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ViewModel creation
		App.get(getActivity()).getAppComponent().inject(this);

		elementFormViewModel.onStart();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		FragmentElementFormBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_element_form, container, false);
		binding.setViewModel(elementFormViewModel);
		return binding.getRoot();
	}
}
