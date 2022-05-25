
package ec.sicpa.latam.com.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import ec.sicpa.latam.com.dto.DepartmentsDto;
import ec.sicpa.latam.com.entity.Departments;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author AMF
 * @version $1.0$
 */
@Mapper(implementationName = "DepartmentsMapper", implementationPackage = "<PACKAGE_NAME>.impl", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IDepartmentsMapper {

	IDepartmentsMapper INSTANCE = Mappers.getMapper(IDepartmentsMapper.class);


	public DepartmentsDto departmentsToDepartmentsDto(Departments departments);


	public Departments departmentsDtoToDepartments(DepartmentsDto departmentsDto);


	public List<DepartmentsDto> listDepartmentsToListDepartmentsDto(List<Departments> departments);


	public List<Departments> listDepartmentsDtoToListDepartments(List<DepartmentsDto> departmentsDto);

}
