package com.example.davit_zakaryan.mvvmapp;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
	@Test
	public void addition_isCorrect() throws Exception {
		assertEquals(4, 2 + 2);
	}

	@Test(expected = NullPointerException.class)
	public void nullStringTest() {
		String str = null;
		assertTrue(str.isEmpty());
	}

	@Test
	@Ignore("only for fail testing")
	public void addition_isNotCorrect() throws Exception {
		assertEquals("Numbers isn't equals!", 5, 2 + 2);
	}

	@Test
	public void simple_is_equal() {
		assertThat(3, is(3));
	}


	@Test
	public void verify_some_behaviour() {
		//mock creation
		List mockedList = mock(List.class);

		//using mock object
		mockedList.add("one");
		mockedList.clear();

		//verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void throwsIllegalArgumentExceptionIfIconIsNull() {
		exception.expect(NullPointerException.class);
		//exception.expectMessage("Negative value not allowed");
		//ClassToBeTested t = new ClassToBeTested();
		//t.methodToBeTest(-1);
		String ka = null;
		ka.toString();
	}
}