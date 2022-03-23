package com.schoolapp.schoolapp.Repository;

import com.schoolapp.schoolapp.Entity.ExamEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import antlr.collections.List;


public interface ExamRepository extends JpaRepository<ExamEntity,Long>{
    List findById(int id);
}
