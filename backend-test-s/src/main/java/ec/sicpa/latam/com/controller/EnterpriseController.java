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

import ec.sicpa.latam.com.dto.EnterprisesDto;
import ec.sicpa.latam.com.entity.Enterprises;
import ec.sicpa.latam.com.mapper.IEnterpriseMapper;
import ec.sicpa.latam.com.service.IEnterprisesService;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 *
 * @author AMF
 * @version $1.0$
 */
@RestController()
@RequestMapping("/api/enterprises")
public class EnterpriseController {

	private static final Logger LOG = LoggerFactory.getLogger(EnterpriseController.class);

	@Autowired
	private IEnterprisesService enterprisesService;
	
	@Autowired
	private IEnterpriseMapper enterpriseMapper;
	private Map<String, Object> response;

	public EnterpriseController() {
		response = new HashMap<>();
	}


	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		try {
			return ResponseEntity.ok().body(enterpriseMapper.listEnterprisesToListEnterprisesDto(enterprisesService.findAll()));
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
			return ResponseEntity.ok().body(enterpriseMapper.enterprisesToEnterprisesDto(enterprisesService.findById(id).orElse(null)));
		} catch (Exception e) {
			LOG.error("list: ", e);
			response.put("message", "Error findAll");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Secured({ "ROLE_ADMIN" })
	@PostMapping("")
	public ResponseEntity<?> create(@Valid @RequestBody EnterprisesDto enterprisesDto, BindingResult result) {
		response = new HashMap<>();
		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			Enterprises enterprises = enterpriseMapper.enterprisesDtoToEnterprises(enterprisesDto);
			enterprises = enterprisesService.save(enterprises);
			response.put("message", "success save enterprises");
			response.put("enterprises", enterpriseMapper.enterprisesToEnterprisesDto(enterprises));
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (DataAccessException e) {
			response.put("message", "Error to save enterprises");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody EnterprisesDto enterprisesDto, BindingResult result, @PathVariable Long id) {
		response = new HashMap<>();
		Enterprises enterprises;
		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream()
						.map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage())
						.collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			enterprises = enterprisesService.findById(id).orElse(null);
			if (enterprises == null) {
				response.put("message", "Enterprise not found");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			} else {
				enterprises.setId(enterprisesDto.getId());
				enterprises.setAddress(enterprisesDto.getAddress());
				enterprises.setName(enterprisesDto.getName());
				enterprises.setPhone(enterprisesDto.getPhone());
				enterprises = enterprisesService.save(enterprises);
				response.put("message", "Success update");
				response.put("enterprises", enterpriseMapper.enterprisesToEnterprisesDto(enterprises));

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
