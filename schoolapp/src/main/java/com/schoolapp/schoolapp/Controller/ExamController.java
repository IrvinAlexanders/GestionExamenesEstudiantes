package com.schoolapp.schoolapp.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional; 

import com.schoolapp.schoolapp.Entity.ExamEntity;
import com.schoolapp.schoolapp.Repository.ExamRepository;

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
public class ExamController {
    @Autowired
    ExamRepository examRepository;

    @GetMapping("/exams")
    public ResponseEntity<List<ExamEntity>>getAllExams(@RequestParam(required=false) int id)
    {
    try {
	    List<ExamEntity>exams = new ArrayList<ExamEntity>();
	    if(id <=0) {
		    examRepository.findAll().forEach(exams::add);
	    }else if(exams.isEmpty()) {
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(exams, HttpStatus.OK);
    }catch(Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    @GetMapping("/exams/{id}")
    public ResponseEntity<ExamEntity> getExamById(@PathVariable("id") long id) {
	    Optional<ExamEntity> examData = examRepository.findById(id);

	    if (examData.isPresent()) {
		    return new ResponseEntity<>(examData.get(), HttpStatus.OK);
	    } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
    }
    @PostMapping("/exams")
    public ResponseEntity<ExamEntity> createExamen(@RequestBody ExamEntity exams) {
	    try {
		    //public ExamenEntity(int puntaje, PreguntasEntity preguntas,int cantidadPreguntas)
		    ExamEntity _exams = examRepository.save(new ExamEntity(exams.getScore(),exams.getQuestions()));
		    return new ResponseEntity<>(_exams, HttpStatus.CREATED);
	    } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }

    @PutMapping("/exams/{id}")
    public ResponseEntity<ExamEntity> updateExam(@PathVariable("id") long id, @RequestBody ExamEntity exams) {
	    Optional<ExamEntity> examData = examRepository.findById(id);

	    if (examData.isPresent()) {
		    ExamEntity _exams = examData.get();
		    _exams.setScore(exams.getScore());
		    _exams.setQuestions(exams.getQuestions());
		    return new ResponseEntity<>(examRepository.save(_exams), HttpStatus.OK);
	    } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
    }

    @DeleteMapping("/exams/{id}")
    public ResponseEntity<HttpStatus> deleteExam(@PathVariable("id") long id) {
	    try {
		    examRepository.deleteById(id);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
    @DeleteMapping("/exams")
    public ResponseEntity<HttpStatus> deleteAllExam() {
	    try {
		    examRepository.deleteAll();
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

    }   
}
