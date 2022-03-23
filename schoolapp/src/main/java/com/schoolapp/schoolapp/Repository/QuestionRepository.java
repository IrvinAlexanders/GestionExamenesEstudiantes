package com.schoolapp.schoolapp.Repository;

import com.schoolapp.schoolapp.Entity.QuestionEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import antlr.collections.List;


public interface QuestionRepository extends JpaRepository<QuestionEntity,Long>{
    List findById(int id);
    List findByCorrect(boolean correct);
}