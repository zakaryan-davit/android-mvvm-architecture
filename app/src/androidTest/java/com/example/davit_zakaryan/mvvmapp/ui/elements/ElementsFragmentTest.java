package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.ui.base.HomeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class ElementsFragmentTest {


	/**
	 * The ActivityTestRule is a rule provided by Android used for functional testing of a single
	 * activity. The activity that will be tested will be launched before each test that's annotated
	 * with @Test and before methods annotated with @Before.
	 */
	@Rule
	public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class);


	@Before
	public void init() {

//		ElementsFragment elementsFragment = new ElementsFragment();
//		//elementsFragment.setArguments(getIntent().getExtras());
//
//		activityTestRule.getActivity()
//				.getSupportFragmentManager()
//				.beginTransaction().add(R.id.fragment_container, elementsFragment)
//				.commit();
	}

	@Test
	public void clickRecyclerViewItem_OpenDetailsScreen() {
		onView(withId(R.id.activity_elements_recycleView))
				.perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

		// Checks that the OrderActivity opens with the correct tea name displayed
		onView(withId(R.id.activity_details_name_text)).check(matches(withText(is("Black mamba"))));
	}

}