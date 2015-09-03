package com.rhcloud.vadyazakusylo.library.dao;

import java.util.List;

import com.rhcloud.vadyazakusylo.library.entity.Book;

public interface BookDao {

	List<Book> getBookList();

	List<Book> getBookList(int genreId);

	void addBook(Book book, int[] genreId);

	void addBookList(List<Book> books, List<int[]> genreList);

}
