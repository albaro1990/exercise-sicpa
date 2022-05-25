package ec.sicpa.latam.com.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -950872439596383011L;

	protected String createBy;

	protected Date createDate;

	protected String status;

	protected Date modifiedDate;

	protected String modifiedBy;

}
