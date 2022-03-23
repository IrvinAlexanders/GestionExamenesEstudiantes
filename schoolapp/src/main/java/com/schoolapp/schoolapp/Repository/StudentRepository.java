package com.schoolapp.schoolapp.Repository;

import com.schoolapp.schoolapp.Entity.StudentEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import antlr.collections.List;

public interface StudentRepository extends JpaRepository<StudentEntity,Long>{
    List findById(int id);
    List findByName(String name);
}
