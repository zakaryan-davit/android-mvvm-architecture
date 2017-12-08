package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.davit_zakaryan.mvvmapp.FakeData;
import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.databinding.FragmentElementsBinding;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseActivity;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseFragment;
import com.example.davit_zakaryan.mvvmapp.ui.element_form.ElementFormActivity;

/**
 * Created by Davit_Zakaryan on 12/8/2017.
 */

public class ElementsFragment extends BaseFragment {

	private ElementsViewModel viewModel;
	private int chosenType; //TODO make intDef
	private ElementsAdapter elementsAdapter;


	public ElementsFragment() {
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
		FragmentElementsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_elements, container, false);
		((BaseActivity) getActivity()).setSupportActionBar((Toolbar) binding.toolbar);
		return binding.getRoot();
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);


		elementsAdapter = new ElementsAdapter(FakeData.getElementList(), chosenType);

		// Set default title
		//elementsAdapter.setChosenType(chosenType);
		getActivity().setTitle(chosenType);

		View.OnClickListener onClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (view.getId() == R.id.fab) {
					Intent intent = new Intent(getActivity(), ElementFormActivity.class);
					startActivity(intent);
				}

			}
		};

		RecyclerView recyclerView = view.findViewById(R.id.activity_elements_recycleView);
		final int columns = getResources().getInteger(R.integer.element_columns);
		recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), columns));
		recyclerView.setAdapter(elementsAdapter);


		FloatingActionButton fab = view.findViewById(R.id.fab);
		fab.setOnClickListener(onClickListener);
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
				showMsg();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showMsg() {
		Toast toast = Toast.makeText(getActivity(), "Reset", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, toast.getXOffset() / 2, toast.getYOffset() / 2);
		toast.show();
	}

	private Dialog createSingleChoiceDialog(@StringRes int titleRes, @ArrayRes int choiceItems) {
		//Initialize the Alert Dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(titleRes)
				.setSingleChoiceItems(choiceItems, chosenType, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						chosenType = i;
					}
				})
				.setPositiveButton(R.string.dialog_choose, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						elementsAdapter.setChosenType(chosenType);
						getActivity().setTitle(chosenType);
					}
				})
				.setNegativeButton(R.string.dialog_cancel, null);
		return builder.create();
	}
}
