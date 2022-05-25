package ec.sicpa.latam.com.dao.impl;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import ec.sicpa.latam.com.dao.IDepartmentsDao;
import ec.sicpa.latam.com.entity.Departments;


public class DepartmentsDao extends GenericDao<Departments, Long> implements IDepartmentsDao {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentsDao.class);

	public DepartmentsDao(JpaEntityInformation<Departments, Long> ei, EntityManager em) {
		super(ei, em);
	}

}
