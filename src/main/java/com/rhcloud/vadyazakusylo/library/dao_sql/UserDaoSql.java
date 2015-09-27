package com.rhcloud.vadyazakusylo.library.dao_sql;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rhcloud.vadyazakusylo.library.dao.UserDao;
import com.rhcloud.vadyazakusylo.library.entity.User;

@Component
public class UserDaoSql extends AbstractDao implements UserDao {

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public User getUser(String login) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		return (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<User> getUserList() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(User.class).list();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
}
