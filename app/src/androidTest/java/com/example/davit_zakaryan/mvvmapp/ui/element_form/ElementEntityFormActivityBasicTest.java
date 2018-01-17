package com.example.davit_zakaryan.mvvmapp.ui.element_form;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.davit_zakaryan.mvvmapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Davit_Zakaryan on 12/13/2017.
 */

@RunWith(AndroidJUnit4.class)
public class ElementFormActivityBasicTest {


	// ActivityTestRule is a rule that provides functional testing for a specific, single activity
	@Rule
	public ActivityTestRule<ElementFormActivity> activityTestRule
			= new ActivityTestRule<>(ElementFormActivity.class);


	@Test
	public void clickIncrementButton_ChangesLevel() {
		// 1. Find the view
		// 2. Perform action on the view
		onView(withId(R.id.element_form_level_increment_button)).perform(click());

		// 3. Check if the view does what we expected
		onView(withId(R.id.element_form_level_edit_text)).check(matches(withText("2")));
	}

	@Test
	public void clickDecrementButton_ChangesLevel() {
		onView(withId(R.id.element_form_level_decrement_button)).perform(click());
		onView(withId(R.id.element_form_level_edit_text)).check(matches(withText("1")));
	}
}