package ec.sicpa.latam.com.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;

import ec.sicpa.latam.com.BackendSicpaApplication;
import ec.sicpa.latam.com.dto.DepartmentsEmployeesDto;
import ec.sicpa.latam.com.dto.EmployeesDto;
import ec.sicpa.latam.com.entity.Departments;
import ec.sicpa.latam.com.entity.DepartmentsEmployees;
import ec.sicpa.latam.com.entity.Employees;
import ec.sicpa.latam.com.mapper.IDepartmentsMapper;
import ec.sicpa.latam.com.mapper.IEmployeesMapper;
import ec.sicpa.latam.com.service.IDepartmentEmployeeService;
import ec.sicpa.latam.com.service.IEmployeesService;


@SpringBootTest(classes = BackendSicpaApplication.class)
class EmployeesControllerTest {
	
	MockMvc mvc;
	
	@Mock
	private IEmployeesService employeesService;
	
	@Mock
	private IEmployeesMapper employeesMapper;
	
	@Mock
	private IDepartmentsMapper departmentsMapper;
	
	
	@Mock
	private IDepartmentEmployeeService departmentEmployeeService;
	
	
	@InjectMocks
    private EmployeesController employeesController;

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		
		MockitoAnnotations.initMocks(this);


        mvc = MockMvcBuilders.standaloneSetup(employeesController).build();
        
	}

	@Test
	void whenGivenAllEmployees() throws Exception {
		
		List<Employees> listEmployee = new ArrayList<>();
		List<EmployeesDto> listEmployeeDto = new ArrayList<>();
		
		when(employeesService.findAll()).thenReturn(listEmployee);
		when(employeesMapper.listEmployeeToListEmployeesDto(listEmployee)).thenReturn(listEmployeeDto);
	    mvc.perform(get("/api/employees")).andExpect(status().is2xxSuccessful());
	
	}
	
	@Test
	void whenGivenAllEmployeesIsException() throws Exception {
		
		when(employeesService.findAll()).thenThrow(NullPointerException.class);
		
	    mvc.perform(get("/api/employees")).andExpect(status().is5xxServerError());
	
	}
	
	
	@Test
	void whenCreateEmployeeRetornError() throws Exception {
		
		DepartmentsEmployeesDto departmentsEmployeesDto = new DepartmentsEmployeesDto();
		EmployeesDto employeesDto = new EmployeesDto();
		employeesDto.setId(0l);
		departmentsEmployeesDto.setEmployees(employeesDto);
		
		Employees employees = new Employees();
		employees.setId(0l);
		
		Gson gson = new Gson();
        String jsonInString = gson.toJson(departmentsEmployeesDto);
        							
        when(employeesMapper.employeesDtoToEmployee(employeesDto)).thenReturn(employees);
        when(employeesService.save(employees)).thenReturn(employees);
		mvc.perform(post("/api/create/employee")
				.content(jsonInString)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
		
        
	}
	
	@Test
	void whenCreateEmployeeRetornIsCorrect() throws Exception {
		
		DepartmentsEmployeesDto departmentsEmployeesDto = new DepartmentsEmployeesDto();
		Employees employees = new Employees();
		employees.setId(1l);
		
		DepartmentsEmployees departmentsEmployees = new DepartmentsEmployees();
		
		Departments departments= new Departments();
		
		Gson gson = new Gson();
        String jsonInString = gson.toJson(departmentsEmployeesDto);
        
        when(employeesMapper.employeesDtoToEmployee(departmentsEmployeesDto.getEmployees())).thenReturn(employees);
        when(employeesService.save(employees)).thenReturn(employees);
        when(departmentsMapper.departmentsDtoToDepartments(departmentsEmployeesDto.getDepartments())).thenReturn(departments);
        when(departmentEmployeeService.save(departmentsEmployees)).thenReturn(departmentsEmployees);
		
		
		mvc.perform(post("/api/create/employee")
				.content(jsonInString)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		
        
	}

}
