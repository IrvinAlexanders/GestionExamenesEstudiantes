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
    public StudentEntity(String name, int age, String city, Timestamp time_zone,
			ExamEntity exam) {
		super();
		this.name = name;
		this.age = age;
		this.city = city;
		this.time_zone = time_zone;
		this.exam = exam;
	}

    // Obtenemos el ID
	public long getId() {
		return id;
	}

    // No es buena práctica cambiar el ID, pero acá podemos hacerlo
	public void setId(long id) {
		this.id = id;
	}

    // Obtenemos el nombre completo
	public String getName() {
		return name;
	}

    // Para cambiar el nombre completo
	public void setName(String name) {
		this.name = name;
	}

    // Obtenemos la edad
	public int getAge() {
		return age;
	}

    // Editamos la edad
	public void setAge(int age) {
		this.age = age;
	}

    // Obtenemos la ciudad
	public String getCity() {
		return city;
	}

    // Editamos la ciudad
	public void setCity(String city) {
		this.city = city;
	}

    // Vemos la zona horaria
	public Timestamp getTime_zone() {
		return time_zone;
	}

    //Editamos la zona horaria
	public void setTime_zone(Timestamp time_zone) {
		this.time_zone = time_zone;
	}

    //Obtenemos el Examen importando ExamEntity
	public ExamEntity getExam() {
		return exam;
	}
	public void setExam(ExamEntity exam) {
		this.exam = exam;
	}

    //Devolvemos una cadena con todos los datos del estudiante
	@Override
	public String toString() {
		return "StudentEntity [id =" + id + ", name = " + name + ", age = " + age + ", city = " + city
				+ ", time_zone = " + time_zone + ", exam = " + exam + "]";
	}
    
}
