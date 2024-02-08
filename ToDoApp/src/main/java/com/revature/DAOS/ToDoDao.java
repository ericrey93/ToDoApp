package com.revature.DAOS;

import com.revature.Models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoDao extends JpaRepository<ToDo, Integer> {





}
