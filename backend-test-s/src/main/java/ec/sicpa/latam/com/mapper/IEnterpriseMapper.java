
package ec.sicpa.latam.com.mapper;

import ec.sicpa.latam.com.dto.EnterprisesDto;
import ec.sicpa.latam.com.entity.Enterprises;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author AMF
 * @version $1.0$
 */
@Mapper(implementationName = "EnterpriseMapper", implementationPackage = "<PACKAGE_NAME>.impl", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEnterpriseMapper {

	IEnterpriseMapper INSTANCE = Mappers.getMapper(IEnterpriseMapper.class);


	public EnterprisesDto enterprisesToEnterprisesDto(Enterprises enterprises);


	public Enterprises enterprisesDtoToEnterprises(EnterprisesDto enterprisesDto);


	public List<EnterprisesDto> listEnterprisesToListEnterprisesDto(List<Enterprises> enterprises);


	public List<Enterprises> listEnterprisesDtoToListEnterprises(List<EnterprisesDto> enterprisesDto);

}
