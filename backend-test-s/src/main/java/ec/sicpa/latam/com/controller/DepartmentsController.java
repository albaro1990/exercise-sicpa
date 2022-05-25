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
import ec.sicpa.latam.com.entity.Departments;
import ec.sicpa.latam.com.mapper.IDepartmentsMapper;
import ec.sicpa.latam.com.mapper.IEnterpriseMapper;
import ec.sicpa.latam.com.service.IDepartmentsService;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 *
 * @author AMF
 * @version $1.0$
 */
@RestController()
@RequestMapping("/api/department")
public class DepartmentsController {

	private static final Logger LOG = LoggerFactory.getLogger(DepartmentsController.class);

	@Autowired
	private IDepartmentsService departmentsService;
	
	@Autowired
	private IDepartmentsMapper departmentsMapper;
	
	@Autowired
	private IEnterpriseMapper enterpriseMapper;
	
	private Map<String, Object> response;

	public DepartmentsController() {
		response = new HashMap<>();
	}


	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		try {
			return ResponseEntity.ok().body(departmentsMapper.listDepartmentsToListDepartmentsDto(departmentsService.findAll()));
		} catch (Exception e) {
			LOG.error("list: ", e);
			response.put("message", "Error findAll");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok().body(departmentsMapper.departmentsToDepartmentsDto(departmentsService.findById(id).orElse(null)));
		} catch (Exception e) {
			LOG.error("list: ", e);
			response.put("message", "Error findAll");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Secured({ "ROLE_ADMIN" })
	@PostMapping("")
	public ResponseEntity<?> create(@Valid @RequestBody DepartmentsDto departmentsDto, BindingResult result) {
		response = new HashMap<>();
		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream().map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			Departments departments = departmentsMapper.departmentsDtoToDepartments(departmentsDto);
			departments = departmentsService.save(departments);
			response.put("message", "success save departmants");
			response.put("enterprises", departmentsMapper.departmentsToDepartmentsDto(departments));
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (DataAccessException e) {
			response.put("message", "Error to save departmants");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody DepartmentsDto departmentsDto, BindingResult result, @PathVariable Long id) {
		response = new HashMap<>();
		Departments departments;
		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream()
						.map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage())
						.collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			departments = departmentsService.findById(id).orElse(null);
			if (departments == null) {
				response.put("message", "Departments not found");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			} else {
				departments.setId(departmentsDto.getId());
				departments.setDescription(departmentsDto.getDescription());
				departments.setName(departmentsDto.getName());
				departments.setPhone(departmentsDto.getPhone());
				departments.setEnterprises(enterpriseMapper.enterprisesDtoToEnterprises(departmentsDto.getEnterprises()));
				
				departments = departmentsService.save(departments);
				response.put("message", "Success update");
				response.put("enterprises", departmentsMapper.departmentsToDepartmentsDto(departments));

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
