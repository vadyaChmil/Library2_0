package com.rhcloud.vadyazakusylo.library.dao_sql;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rhcloud.vadyazakusylo.library.dao.BookDao;
import com.rhcloud.vadyazakusylo.library.entity.Book;
import com.rhcloud.vadyazakusylo.library.entity.Genre;

@Component
public class BookDaoSql extends AbstractDao implements BookDao {

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Book> getBookList() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Book.class).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Book> getBookList(int genreId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select b from Book b join "
				+ "b.genres g where g.id=:genreId");
		return query.setParameter("genreId", genreId).list();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void addBook(Book book, int[] genreId) {
		Session session = sessionFactory.getCurrentSession();
		Set<Genre> genres = new HashSet<>();
		if (genreId != null) {
			for (int id : genreId) {
				Genre genre = (Genre) session.get(Genre.class, id);
				genres.add(genre);
			}
		}
		book.setGenres(genres);
		session.save(book);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void addBookList(List<Book> books, List<int[]> genreId) {
		for (int index = 0; index < books.size(); index++) {
			addBook(books.get(index), genreId.get(index));
		}
	}
}
