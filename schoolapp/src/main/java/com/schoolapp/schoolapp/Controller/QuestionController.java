package com.schoolapp.schoolapp.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.schoolapp.schoolapp.Entity.QuestionEntity;
import com.schoolapp.schoolapp.Repository.QuestionRepository;

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
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionEntity>>getAllQuestion(@RequestParam(required=false) int id)
    {
    try {
	    List<QuestionEntity>questions = new ArrayList<QuestionEntity>();
	    if(id <=0) {
	    questionRepository.findAll().forEach(questions::add);
	    }else if(questions.isEmpty()) {
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(questions, HttpStatus.OK);
    }catch(Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    @GetMapping("/questions/{id}")
    public ResponseEntity<QuestionEntity> getQuestionById(@PathVariable("id") long id) {
	    Optional<QuestionEntity> questionData = questionRepository.findById(id);

	    if (questionData.isPresent()) {
		    return new ResponseEntity<>(questionData.get(), HttpStatus.OK);
	    } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
    }
    @PostMapping("/questions")
    public ResponseEntity<QuestionEntity> createQuestion(@RequestBody QuestionEntity questions) {
	    try {
		
		    QuestionEntity _questions = questionRepository.save(new QuestionEntity(questions.getStatement(),questions.isOption_a(),questions.isOpc_b(),questions.isOpc_c(),questions.isOpc_d(),questions.getQuestionValue()));
		    return new ResponseEntity<>(_questions, HttpStatus.CREATED);
	    } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<QuestionEntity> updateQuestion(@PathVariable("id") long id, @RequestBody QuestionEntity questions) {
	    Optional<QuestionEntity> questionData = questionRepository.findById(id);

	    if (questionData.isPresent()) {
		    QuestionEntity _questions = questionData.get();
		    _questions.setStatement(questions.getStatement());
		    _questions.setQuestionValue(questions.getQuestionValue());
		    _questions.setOption_a(questions.isOption_a());
		    _questions.setOpc_b(questions.isOpc_b());
		    _questions.setOpc_c(questions.isOpc_c());
		    _questions.setOpc_d(questions.isOpc_d());
		    return new ResponseEntity<>(questionRepository.save(_questions), HttpStatus.OK);
	    } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable("id") long id) {
	    try {
		    questionRepository.deleteById(id);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
    @DeleteMapping("/questions")
    public ResponseEntity<HttpStatus> deleteAllQuestion() {
	    try {
		    questionRepository.deleteAll();
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

    }
    @SuppressWarnings("unchecked")
    @GetMapping("/questions/correct")
    public ResponseEntity<List<QuestionEntity>> findByCorrect() {
	    try {
		    List<QuestionEntity> questions = (List<QuestionEntity>) questionRepository.findByCorrect(true);

		    if (questions.isEmpty()) {
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(questions, HttpStatus.OK);
	    } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }

}
