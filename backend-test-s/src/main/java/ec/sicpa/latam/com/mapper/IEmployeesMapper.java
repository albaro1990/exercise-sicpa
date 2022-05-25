
package ec.sicpa.latam.com.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import ec.sicpa.latam.com.dto.EmployeesDto;
import ec.sicpa.latam.com.entity.Employees;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author AMF
 * @version $1.0$
 */
@Mapper(implementationName = "EmployeesMapper", implementationPackage = "<PACKAGE_NAME>.impl", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEmployeesMapper {

	IEmployeesMapper INSTANCE = Mappers.getMapper(IEmployeesMapper.class);


	public EmployeesDto employeeToEmployeeDto(Employees employee);


	public Employees employeesDtoToEmployee(EmployeesDto employeeDto);


	public List<EmployeesDto> listEmployeeToListEmployeesDto(List<Employees> employees);


	public List<Employees> listEmployeesDtoToListEmployee(List<EmployeesDto> employeesDto);

}
