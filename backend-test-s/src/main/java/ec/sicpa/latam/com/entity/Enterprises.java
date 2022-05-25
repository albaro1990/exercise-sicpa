package ec.sicpa.latam.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enterprises")
public class Enterprises extends  BaseEntity {

    private static final long serialVersionUID = 2318952949639922556L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = true)
    private String address;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String phone;
}
