package ec.sicpa.latam.com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentsDto extends  BaseDto {

    
    private static final long serialVersionUID = 8176006802712142385L;

	private Long id;
   
    private String description;
   
    private String name;

    private String phone;

    private EnterprisesDto enterprises;

}
