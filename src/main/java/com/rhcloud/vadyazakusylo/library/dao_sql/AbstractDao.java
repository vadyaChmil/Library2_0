package com.rhcloud.vadyazakusylo.library.dao_sql;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDao {

	@Autowired
	protected SessionFactory sessionFactory;

}
