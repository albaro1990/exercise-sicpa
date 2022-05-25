package ec.sicpa.latam.com.dao.impl;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import ec.sicpa.latam.com.dao.IDepartmentEmployeeDao;
import ec.sicpa.latam.com.entity.DepartmentsEmployees;


public class DepartmentEmployeeDao extends GenericDao<DepartmentsEmployees, Long> implements IDepartmentEmployeeDao {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentEmployeeDao.class);

	public DepartmentEmployeeDao(JpaEntityInformation<DepartmentsEmployees, Long> ei, EntityManager em) {
		super(ei, em);
	}

}
