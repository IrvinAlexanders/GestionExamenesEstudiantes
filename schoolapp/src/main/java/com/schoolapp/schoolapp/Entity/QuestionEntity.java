package com.schoolapp.schoolapp.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
Creando el modelo para las preguntas, como atributo tendrá el enunciado, las opciones a, b, c,
y el valor de cada pregunta
*/

@Entity
@Table(name="questions")
public class QuestionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="statement",nullable=false,columnDefinition="varchar(40) default 'enunciado'")
    private String statement;

    @Column(name="opption_a",nullable=false,columnDefinition="boolean default false")
    private boolean option_a;

    @Column(name="opc_b",nullable=false,columnDefinition="boolean default false")
    private boolean opc_b;

    @Column(name="opc_c",nullable=false,columnDefinition="boolean default false")
    private boolean opc_c;

    @Column(name="opc_d",nullable=false,columnDefinition="boolean default false")
    private boolean opc_d;

    @Column(name="question_value",nullable=false,columnDefinition="int default 0")
    private int question_value;

    public QuestionEntity() {
	
    }

    public QuestionEntity(String statement, boolean option_a, boolean opc_b, boolean opc_c, boolean opc_d,int question_value) {
	    super();
	    this.statement = statement;
	    this.option_a = option_a;
	    this.opc_b = opc_b;
	    this.opc_c = opc_c;
	    this.opc_d = opc_d;
	    this.question_value = question_value;
    }
    
    public long getId() {
	    return id;
    }

    public void setId(long id) {
	    this.id = id;
    }

    public String getStatement() {
	    return statement;
    }

    public void setStatement(String statement) {
	    this.statement = statement;
    }

    public boolean isOption_a() {
	    return option_a;
    }

    public void setOption_a(boolean option_a) {
	    this.option_a = option_a;
    }

    public boolean isOpc_b() {
	    return opc_b;
    }

    public void setOpc_b(boolean opc_b) {
	    this.opc_b = opc_b;
    }

    public boolean isOpc_c() {
	    return opc_c;
    }

    public void setOpc_c(boolean opc_c) {
	    this.opc_c = opc_c;
    }

    public boolean isOpc_d() {
	    return opc_d;
    }

    public void setOpc_d(boolean opc_d) {
	    this.opc_d = opc_d;
    }

    public int getQuestionValue() {
	    return question_value;
    }

    public void setQuestionValue(int question_value) {
	    this.question_value = question_value;
    }

    /*toString*/
    @Override
    public String toString() {
	    return "Questions [id = " + id + ", statement = " + statement + ", option_a = " + option_a + ", opc_b = " + opc_b + ", opc_c = "
		    	+ opc_c + ", opc_d = " + opc_d +", question value = " + question_value + "]";
    }

}
