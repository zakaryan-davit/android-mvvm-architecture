package com.example.davit_zakaryan.mvvmapp.ui.elemets;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.davit_zakaryan.mvvmapp.databinding.ActivityElementsBinding;
import com.example.davit_zakaryan.mvvmapp.ui.elementdetails.ElementDetailsActivity;
import com.example.davit_zakaryan.mvvmapp.R;

public class ElementsActivity extends AppCompatActivity {

	private ElementsViewModel viewModel;
	private View.OnClickListener onClickListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		ActivityElementsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_elements);
		binding.setViewModel(viewModel);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		onClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(ElementsActivity.this, ElementDetailsActivity.class);
				startActivity(intent);
			}
		};

		findViewById(R.id.list_item1).setOnClickListener(onClickListener);
		findViewById(R.id.list_item2).setOnClickListener(onClickListener);
		findViewById(R.id.list_item3).setOnClickListener(onClickListener);
		findViewById(R.id.list_item4).setOnClickListener(onClickListener);

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
				showMsg("Edit");
				break;
			case R.id.action_reset:
				showMsg("Reset");
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showMsg(String msg) {
		Toast toast = Toast.makeText(ElementsActivity.this, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, toast.getXOffset() / 2, toast.getYOffset() / 2);
		toast.show();
	}
}
