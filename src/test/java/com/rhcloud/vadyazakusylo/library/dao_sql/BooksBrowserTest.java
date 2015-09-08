package com.rhcloud.vadyazakusylo.library.dao_sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rhcloud.vadyazakusylo.library.entity.Book;

public class BooksBrowserTest {
	static BookBrowser bookBrowser;
	List<String> contentString;
	List<Book> downloadBookList;

	@BeforeClass
	public static void oneTimeSetUp() {
		bookBrowser = new BookBrowser();
	}

	@AfterClass
	public static void oneTimeTearDown() {
		bookBrowser = null;
	}

	@Before
	public void setUp() {
		contentString = new ArrayList<String>();
		downloadBookList = new ArrayList<Book>();
	}

	@After
	public void tearDown() {
		contentString.clear();
		downloadBookList.clear();
	}

	@Test(expected = NullPointerException.class)
	public void shouldParseStringToList_whenNoString() throws IOException {
		// when
		String string = null;
		// then
		bookBrowser.parseStringToList(string);
	}

	@Test
	public void shouldParseStringToList() {
		// when
		String testString = "10001;Joshua Bloch;Effective Java;2010;385";
		// then
		contentString = bookBrowser.parseStringToList(testString);
		assertEquals(5, contentString.size());
		assertEquals("10001", contentString.get(0));
		assertEquals("Joshua Bloch", contentString.get(1));
		assertEquals("Effective Java", contentString.get(2));
		assertEquals("2010", contentString.get(3));
		assertEquals("385", contentString.get(4));

		// when
		testString = "10001;\nJoshua Bloch;;    ;Effective Java;\n2010";
		// then
		contentString = bookBrowser.parseStringToList(testString);
		assertEquals(4, contentString.size());
		assertEquals("10001", contentString.get(0));
		assertEquals("Joshua Bloch", contentString.get(1));
		assertEquals("Effective Java", contentString.get(2));
		assertEquals("2010", contentString.get(3));
	}

	@Test(expected = NumberFormatException.class)
	public void shouldCreateBookList_whenContentIncorrect() {
		// when
		contentString.add("Effective Java");
		contentString.add("Joshua Bloch");
		contentString.add("10001");
		contentString.add("2010");
		contentString.add("385");
		// then
		downloadBookList = bookBrowser.createBookList(contentString);

		// when
		contentString.add("12340");
		contentString.add("Conan Doyle");
		// then
		downloadBookList = bookBrowser.createBookList(contentString);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldCreateBookList_whenContentNotFull() {
		// when
		contentString.add("10001");
		contentString.add("Joshua Bloch");
		contentString.add("Effective Java");
		contentString.add("2010");
		contentString.add("385");
		contentString.add("12340");
		contentString.add("Conan Doyle");
		// then
		downloadBookList = bookBrowser.createBookList(contentString);
	}

	@Test
	public void shouldCreateBookList_whenContentNull() {
		// then
		downloadBookList = bookBrowser.createBookList(contentString);
		assertTrue(downloadBookList.isEmpty());
	}

	@Test
	public void shouldCreateBookList_whenOneBook() {
		// when
		contentString.add("10001");
		contentString.add("Joshua Bloch");
		contentString.add("Effective Java");
		contentString.add("2010");
		contentString.add("385");
		assertEquals(0, downloadBookList.size());
		// then
		downloadBookList = bookBrowser.createBookList(contentString);
		assertEquals(1, downloadBookList.size());
	}

	@Test
	public void shouldCreateBookList_whenFewBook() {
		// when
		contentString.add("10001");
		contentString.add("Joshua Bloch");
		contentString.add("Effective Java");
		contentString.add("2010");
		contentString.add("385");
		contentString.add("10001");
		contentString.add("Joshua Bloch");
		contentString.add("Effective Java");
		contentString.add("2010");
		contentString.add("385");
		// then
		downloadBookList = bookBrowser.createBookList(contentString);
		assertEquals(2, downloadBookList.size());
	}
}
