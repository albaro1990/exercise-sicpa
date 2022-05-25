package ec.sicpa.latam.com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnterprisesDto extends BaseDto {

    private static final long serialVersionUID = -4046848014104665626L;

	private Long id;

    private String address;

    private String name;

    private String phone;
}
