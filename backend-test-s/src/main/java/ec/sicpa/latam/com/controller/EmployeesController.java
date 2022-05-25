package ec.sicpa.latam.com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.sicpa.latam.com.dto.DepartmentsDto;
import ec.sicpa.latam.com.dto.DepartmentsEmployeesDto;
import ec.sicpa.latam.com.dto.EmployeesDto;
import ec.sicpa.latam.com.entity.DepartmentsEmployees;
import ec.sicpa.latam.com.entity.Employees;
import ec.sicpa.latam.com.mapper.IDepartmentsMapper;
import ec.sicpa.latam.com.mapper.IEmployeesMapper;
import ec.sicpa.latam.com.service.IDepartmentEmployeeService;
import ec.sicpa.latam.com.service.IEmployeesService;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 *
 * @author AMF
 * @version $1.0$
 */
@RestController()
@RequestMapping("/api")
public class EmployeesController {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeesController.class);

	@Autowired
	private IEmployeesService employeesService;
	
	@Autowired
	private IDepartmentEmployeeService departmentEmployeeService;
	
	@Autowired
	private IDepartmentsMapper departmentsMapper;
	
	@Autowired
	private IEmployeesMapper employeesMapper;
	
	
	
	private Map<String, Object> response;

	public EmployeesController() {
		response = new HashMap<>();
	}


	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/employees")
	public ResponseEntity<?> findAll() {
		try {
			return ResponseEntity.ok().body(employeesMapper.listEmployeeToListEmployeesDto(employeesService.findAll()));
		} catch (Exception e) {
			LOG.error("list: ", e);
			response.put("message", "Error findAll");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	




	@Secured({ "ROLE_ADMIN" })
	@PostMapping("create/employee")
	public ResponseEntity<?> create(@Valid @RequestBody DepartmentsEmployeesDto departmentsEmployeesDto, BindingResult result) {
		response = new HashMap<>();
		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream().map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			

			Employees employees = employeesMapper.employeesDtoToEmployee(departmentsEmployeesDto.getEmployees());
			employees = employeesService.save(employees);
			
			
			if(employees!=null && employees.getId()>0) {
				DepartmentsEmployees departmentsEmployees = new DepartmentsEmployees() ;
				departmentsEmployees.setDepartments(departmentsMapper.departmentsDtoToDepartments(departmentsEmployeesDto.getDepartments()));
				departmentsEmployees.setEmployees(employees);
				
				departmentEmployeeService.save(departmentsEmployees);
				response.put("message", "success save employees");
			}else {
				
				response.put("error", "error save employees");
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
				
			}
			
			
			response.put("employee", employeesMapper.employeeToEmployeeDto(employees));
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (DataAccessException e) {
			response.put("message", "Error to save employees");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("update/employee/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody DepartmentsEmployeesDto departmentsEmployeesDto, BindingResult result, @PathVariable Long id) {
		response = new HashMap<>();
		Employees employees;
		DepartmentsEmployees departmentsEmployees;
		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream()
						.map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage())
						.collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			departmentsEmployees = departmentEmployeeService.findById(id).orElse(null);
			employees = departmentsEmployees.getEmployees();
			
			if (employees == null) {
				response.put("message", "Employees not found");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			} else {
				employees.setAge(departmentsEmployeesDto.getEmployees().getAge());
				employees.setModifiedBy(departmentsEmployeesDto.getEmployees().getModifiedBy());
				employees.setEmail(departmentsEmployeesDto.getEmployees().getEmail());
				employees.setName(departmentsEmployeesDto.getEmployees().getName());
				employees.setPosition(departmentsEmployeesDto.getEmployees().getPosition());
				employees.setSurname(departmentsEmployeesDto.getEmployees().getSurname());
				employees.setId(departmentsEmployeesDto.getEmployees().getId());
				
				employees = employeesService.save(employees);
				
				departmentsEmployees.setId(departmentsEmployeesDto.getId());
				departmentsEmployees.setEmployees(employees);
				departmentsEmployees.setDepartments(departmentsMapper.departmentsDtoToDepartments(departmentsEmployeesDto.getDepartments()));
				
				departmentEmployeeService.save(departmentsEmployees);
				
				
				response.put("message", "Success update");
				response.put("employees", employeesMapper.employeeToEmployeeDto(employees));

				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			}
		} catch (DataAccessException e) {
			LOG.error("update: ", e);
			response.put("message", "Error to update");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
