package com.schoolapp.schoolapp.Entity;

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
Creando el modelo para examen con parametros puntos y preguntas.
El ID del examen se genera automáticamente.
*/

@Entity
@Table(name="exam")
public class ExamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="score",nullable=false,columnDefinition="int default 0")
    private int score;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="question_id",nullable=false)
    private QuestionEntity [] questions;

    public ExamEntity() {
	
    }
	
    public ExamEntity(int score, QuestionEntity [] questions) {
	    super();
	    this.score = score;
	    this.questions = questions;
    }
    
    public long getId() {
	    return id;
    }

    public void setId(long id) {
	    this.id = id;
    }

    public int getScore() {
	    return score;
    }

    public void setScore(int score) {
	    this.score = score;
    }

    public QuestionEntity [] getQuestions() {
	    return questions;
    }

    public void setQuestions(QuestionEntity [] questions) {
	    this.questions = questions;
    }

    //Retornando una cadena
    @Override
    public String toString() {
	    return "Exam [id = " + id + ", score = " + score + ", questions = " + questions + "]";
    }

    //Iteramos y obtenemos los puntajes
    public void obtainScore() {
    
        int p=0;
        for(QuestionEntity e: questions) {
	        if(e.isOption_a() || e.isOpc_b() || e.isOpc_c() || e.isOpc_d()) {
		        p+=e.getQuestionValue();
	        }
        }

    this.setScore(p);
    }
}