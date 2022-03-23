package com.schoolapp.schoolapp.Entity;

import java.io.Serializable;
import java.security.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
Creando el Modelo y la Tabla Estudiante con las propiedades: nombre, edad, ciudad
y zona horaria de la ciudad. El ID es autogenerado.
*/

@Entity
@Table(name="student")
public class StudentEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(30) default ' '")
    private String name;

    @Column(name="age",nullable=false,columnDefinition="int default 0")
	private int age;

	@Column(name="city",nullable=false,columnDefinition="varchar(30) default ' '")
	private String city;

	@Column(name="time_zone",nullable=true,columnDefinition="timestamp")
	private Timestamp time_zone;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "exam_id", nullable = false)
	private ExamEntity exam;

    public StudentEntity() {

    }
    
}
