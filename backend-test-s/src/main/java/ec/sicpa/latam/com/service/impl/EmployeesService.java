package ec.sicpa.latam.com.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ec.sicpa.latam.com.dao.IGenericDao;
import ec.sicpa.latam.com.entity.Departments;
import ec.sicpa.latam.com.entity.Employees;
import ec.sicpa.latam.com.service.IEmployeesService;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author amf
 * @version $1.0$
 */

@Scope("singleton")
@Service("EmployeesService")
public class EmployeesService extends GenericService<Employees, Long> implements IEmployeesService {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(EmployeesService.class);


	public EmployeesService() {
	}

	@Autowired
	public EmployeesService(IGenericDao<Employees, Long> genericDao) {
		super(genericDao);
	}

}
