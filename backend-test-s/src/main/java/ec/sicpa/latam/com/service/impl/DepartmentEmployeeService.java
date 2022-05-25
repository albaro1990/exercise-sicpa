package ec.sicpa.latam.com.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ec.sicpa.latam.com.dao.IGenericDao;
import ec.sicpa.latam.com.entity.DepartmentsEmployees;
import ec.sicpa.latam.com.service.IDepartmentEmployeeService;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author amf
 * @version $1.0$
 */

@Scope("singleton")
@Service("DepartmentEmployeeService")
public class DepartmentEmployeeService extends GenericService<DepartmentsEmployees, Long> implements IDepartmentEmployeeService {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentEmployeeService.class);


	public DepartmentEmployeeService() {
	}

	@Autowired
	public DepartmentEmployeeService(IGenericDao<DepartmentsEmployees, Long> genericDao) {
		super(genericDao);
	}

}
