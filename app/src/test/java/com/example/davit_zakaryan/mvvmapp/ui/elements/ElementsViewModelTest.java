package com.example.davit_zakaryan.mvvmapp.ui.elements;

import com.example.davit_zakaryan.mvvmapp.data.model.Element;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Davit_Zakaryan on 12/13/2017.
 */
public class ElementsViewModelTest {

	private List<Element> elements;

	private ElementsViewModel elementsViewModel;

	@Before
	public void setupTasksViewModel() {
		// Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
		// inject the mocks in the test the initMocks method needs to be called.
		//MockitoAnnotations.initMocks(this);

		//setupContext();

		// Get a reference to the class under test
		elementsViewModel = new ElementsViewModel();

		// We initialise the Elements
		//elements =
		//Lists.newArrayList(new Task("Title1", "Description1"), new Task("Title2", "Description2", true), new Task("Title3", "Description3", true));
	}


	@Test
	public void test1() {
		//  create mock
		ElementsViewModel test = mock(ElementsViewModel.class);

		// define return value for method getUniqueId()
		when(test.getSimpleInteger()).thenReturn(43);

		// use mock in test....
		assertEquals(test.getSimpleInteger(), 43);
	}

	// demonstrates the return of multiple values
	@Test
	public void testMoreThanOneReturnValue() {
		Iterator<String> i = mock(Iterator.class);
		when(i.next()).thenReturn("Mockito").thenReturn("rocks");
		String result = i.next() + " " + i.next();
		//assert
		assertEquals("Mockito rocks", result);
	}


	@Test
	public void testVerify()  {
		// create and configure mock
		ElementsViewModel test = Mockito.mock(ElementsViewModel.class);
		when(test.getSimpleInteger()).thenReturn(43);


		// call method testing on the mock with parameter 12
		test.testing(12);
		test.getSimpleInteger();
		test.getSimpleInteger();

		test.someMethod("called at least once");

		// now check if method testing was called with the parameter 12
		verify(test).testing(ArgumentMatchers.eq(12));

		// was the method called twice?
		verify(test, times(2)).getSimpleInteger();

		// other alternatives for verifiying the number of method calls for a method
		verify(test, never()).someMethod("never called");
		verify(test, atLeastOnce()).someMethod("called at least once");
		//verify(test, atLeast(2)).someMethod("called at least twice");
		//verify(test, times(5)).someMethod("called five times");
		//verify(test, atMost(3)).someMethod("called at most 3 times");
		// This let's you check that no other methods where called on this object.
		// You call it after you have verified the expected method calls.
		verifyNoMoreInteractions(test);
	}
}