package ec.sicpa.latam.com.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employees extends  BaseEntity {

    private static final long serialVersionUID = -3619487152598480281L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String surname;
    
    @OneToMany(mappedBy = "employees", fetch = FetchType.LAZY)
	private List<DepartmentsEmployees> departmentsEmployees;

}
