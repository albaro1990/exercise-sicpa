package ec.sicpa.latam.com.dao.impl;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import ec.sicpa.latam.com.dao.IEmployeesDao;
import ec.sicpa.latam.com.entity.Employees;


public class EmployeesDao extends GenericDao<Employees, Long> implements IEmployeesDao {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(EmployeesDao.class);

	public EmployeesDao(JpaEntityInformation<Employees, Long> ei, EntityManager em) {
		super(ei, em);
	}

}
