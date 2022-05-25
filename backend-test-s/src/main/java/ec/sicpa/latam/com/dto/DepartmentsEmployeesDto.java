package ec.sicpa.latam.com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentsEmployeesDto extends BaseDto {

   
    private static final long serialVersionUID = -8009931253208512273L;


	private Long id;

   
    private DepartmentsDto departments;

    
    private EmployeesDto employees;

}
