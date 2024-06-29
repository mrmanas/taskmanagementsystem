package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gl.model.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
