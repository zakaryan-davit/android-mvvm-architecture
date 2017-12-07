package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.davit_zakaryan.mvvmapp.FakeData;
import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.databinding.ActivityElementsBinding;
import com.example.davit_zakaryan.mvvmapp.ui.element_details.ElementDetailsActivity;
import com.example.davit_zakaryan.mvvmapp.ui.element_form.ElementFormActivity;
import com.example.davit_zakaryan.mvvmapp.util.Constants;

public class ElementsActivity extends AppCompatActivity {

	private ElementsViewModel viewModel;
	private int chosenType; //TODO make intDef
	private ElementsAdapter elementsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		ActivityElementsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_elements);
		binding.setViewModel(viewModel);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		elementsAdapter = new ElementsAdapter(FakeData.getElementList(), chosenType);


		// Set default title
		setTitle(chosenType);


		View.OnClickListener onClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (view.getId() == R.id.fab) {
					Intent intent = new Intent(ElementsActivity.this, ElementFormActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(ElementsActivity.this, ElementDetailsActivity.class);
					intent.putExtra(Constants.EXTRA_CHOSEN_TYPE, chosenType);
					startActivity(intent);
				}

			}
		};

		RecyclerView recyclerView = findViewById(R.id.activity_elements_recycleView);
		recyclerView.setLayoutManager(new LinearLayoutManager(ElementsActivity.this));
		recyclerView.setAdapter(elementsAdapter);


		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(onClickListener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.elements, menu);
		return true;
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
		Toast toast = Toast.makeText(ElementsActivity.this, "Reset", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, toast.getXOffset() / 2, toast.getYOffset() / 2);
		toast.show();
	}

	private Dialog createSingleChoiceDialog(@StringRes int titleRes, @ArrayRes int choiceItems) {
		//Initialize the Alert Dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
						setTitle(chosenType);
					}
				})
				.setNegativeButton(R.string.dialog_cancel, null);
		return builder.create();
	}

	@Override
	public void setTitle(int chosenType) {
		int titleId;
		switch (chosenType) {
			case 0:
				titleId = R.string.snakes;
				break;
			case 1:
				titleId = R.string.cards;
				break;
			case 2:
				titleId = R.string.tasks;
				break;
			default:
				titleId = R.string.snakes;
				break;
		}
		super.setTitle(titleId);
		elementsAdapter.setChosenType(chosenType);
	}
}
