package com.example.davit_zakaryan.mvvmapp;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private View.OnClickListener onClickListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		onClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
				///Toast.makeText(MainActivity.this, "kuku", Toast.LENGTH_SHORT).show();
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
		getMenuInflater().inflate(R.menu.main, menu);
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
		Toast toast = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, toast.getXOffset() / 2, toast.getYOffset() / 2);
		toast.show();
	}
}
