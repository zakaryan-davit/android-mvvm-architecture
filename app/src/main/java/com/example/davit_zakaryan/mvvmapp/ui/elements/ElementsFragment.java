package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.service.IRepository;
import com.example.davit_zakaryan.mvvmapp.databinding.FragmentElementsBinding;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseFragment;

public class ElementsFragment extends BaseFragment {

	private ElementsViewModel elementsViewModel;

	public ElementsFragment() {
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		// ViewModel creation
		IRepository repository = new ElementsRepository();
		elementsViewModel = new ElementsViewModel(repository, getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
		FragmentElementsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_elements, container, false);
		binding.setViewModel(elementsViewModel);
		return binding.getRoot();
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getActivity().setTitle(elementsViewModel.getChosenType());
	}


	@Override
	public void onStart() {
		super.onStart();
		elementsViewModel.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
		elementsViewModel.onStop();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.elements, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_edit:
				createSingleChoiceDialog(R.string.dialog_choose_type, R.array.elements).show();
				break;
			case R.id.action_reset:
				Toast toast = Toast.makeText(getActivity(), "Reset", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, toast.getXOffset() / 2, toast.getYOffset() / 2);
				toast.show();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private Dialog createSingleChoiceDialog(@StringRes int titleRes, @ArrayRes int choiceItems) {
		//Initialize the Alert Dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(titleRes)
				.setSingleChoiceItems(choiceItems, elementsViewModel.getChosenType(), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						elementsViewModel.setChosenType(i);
					}
				})
				.setPositiveButton(R.string.dialog_choose, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						getActivity().setTitle(elementsViewModel.getChosenType());
					}
				})
				.setNegativeButton(R.string.dialog_cancel, null);
		return builder.create();
	}
}
