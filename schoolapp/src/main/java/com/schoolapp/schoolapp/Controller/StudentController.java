package com.schoolapp.schoolapp.Controller;

    
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.schoolapp.schoolapp.Entity.StudentEntity;
import com.schoolapp.schoolapp.Repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students")
    public ResponseEntity<List<StudentEntity>>getAllStudent(@RequestParam(required=false) int id)
    {
    try {
	    List<StudentEntity>student = new ArrayList<StudentEntity>();
	    if(id <=0) {
		    studentRepository.findAll().forEach(student::add);
	    }else if(student.isEmpty()) {
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(student, HttpStatus.OK);
    }catch(Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable("id") long id) {
	    Optional<StudentEntity> studentData = studentRepository.findById(id);

	    if (studentData.isPresent()) {
		    return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
	    } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
    }
    @PostMapping("/students")
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity student) {
	    try {
		    //String nombre, int edad, String ciudad, Timestamp zona_horaria,
		    //ExamenEntity examen
		    StudentEntity _student = studentRepository.save(new StudentEntity(student.getName(),student.getAge(),student.getCity(),student.getTime_zone(),student.getExam()));
		    return new ResponseEntity<>(_student, HttpStatus.CREATED);
	    } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable("id") long id, @RequestBody StudentEntity student) {
	    Optional<StudentEntity> studentData = studentRepository.findById(id);

	    if (studentData.isPresent()) {
		    StudentEntity _student = studentData.get();
		    _student.setTime_zone(student.getTime_zone());
	    //	_student.setExam(student.getExam().getScore());
		    return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);
	    } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
	    try {
		    studentRepository.deleteById(id);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
    @DeleteMapping("/students")
    public ResponseEntity<HttpStatus> deleteAllStudent() {
	    try {
		    studentRepository.deleteAll();
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

    }
}
