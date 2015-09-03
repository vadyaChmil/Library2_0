package com.rhcloud.vadyazakusylo.library.dao_sql;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rhcloud.vadyazakusylo.library.dao.GenreDao;
import com.rhcloud.vadyazakusylo.library.entity.Genre;

@Component
public class GenreDaoSql extends AbstractDao implements GenreDao {

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Genre> getGenreList() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Genre.class).list();
	}

}