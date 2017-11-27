package com.example.davit_zakaryan.mvvmapp.ui.elementdetails;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.model.CommonElement;
import com.example.davit_zakaryan.mvvmapp.databinding.ActivityDetailsBindedBinding;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseActivity;

/**
 * Created by Davit_Zakaryan on 11/22/2017.
 */

public class ElementDetailsActivity extends BaseActivity {

	private int chosenType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_details);

		chosenType = getIntent().getIntExtra("chosen_type", 0);
		setTitle(chosenType);

		CommonElement element = new CommonElement();
		element.name = "Simple name ";
		element.shortDesc = "sit amet, mollis po mus eu felis";
		element.description = "Lorem Lorem ipsum dolor sit amet, mollis po mus eu felis. Risus varius, a e";
		element.level = 1;

		ActivityDetailsBindedBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details_binded);
		binding.setElement(element);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// Show the Up button in the action bar.
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.element_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_alarm:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	// TODO there is duplicate method in ElementsActivity class
	// should be removed when switching to fragments
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
	}
}
