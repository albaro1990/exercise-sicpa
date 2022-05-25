package ec.sicpa.latam.com.dto;


import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeesDto extends  BaseDto {

   
    private static final long serialVersionUID = -2533288402169939051L;

	private Long id;
   
	@NotNull
    private Integer age;
    
    private String email;
    
    @NotNull
    private String name;
  
    private String position;

    @NotNull
    private String surname;

}
