package ec.sicpa.latam.com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;


	@Column(name = "create_by", insertable = true, updatable = false, nullable = true, length = 50)
	protected String createBy;


	@CreationTimestamp
	@Column(name = "create_date", insertable = true, updatable = false, nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createDate;

	@Column(name = "status", insertable = true, updatable = false, nullable = true, length = 50)
	protected String status;


	@UpdateTimestamp
	@Column(name = "modified_date", insertable = false, updatable = true, nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedDate;


	@Column(name = "modified_by", insertable = true, updatable = false, nullable = true, length = 50)
	protected String modifiedBy;
}
