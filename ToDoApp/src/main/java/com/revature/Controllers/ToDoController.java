package com.revature.Controllers;

import com.revature.Models.ToDo;
import com.revature.Services.ToDoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    // TODO Dependency Injection for our service class

    private ToDoServices tds;

    @Autowired
    public ToDoController(ToDoServices tds) {
        this.tds = tds;
    }

    // TODO Handler for creating a to do record

    @PostMapping
    public ToDo createToDoHandler(@RequestBody ToDo todo) {
        ToDo savedToDoData = tds.createNewToDo(todo);
        return savedToDoData;
    }

    // TODO Handler for getting a todo record by id
    @GetMapping("{id}")
    public ResponseEntity<ToDo> getToDOById(@PathVariable int id) {
        ToDo returnedToDo;

        try {
            returnedToDo = tds.getToDOById(id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(returnedToDo, HttpStatus.OK);
    }

    // TODO Handler for updating todo record

    @PutMapping("{id}")
    public ResponseEntity<ToDo> updateToDoById(@PathVariable int id,@RequestBody ToDo todo) {
        ToDo returnedToDo;

        try {
            returnedToDo = tds.updateToDoById(id, todo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(returnedToDo, HttpStatus.OK);
    }
}
