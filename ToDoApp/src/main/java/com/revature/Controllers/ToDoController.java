package com.revature.Controllers;

import com.revature.Models.ToDo;
import com.revature.Services.ToDoServices;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("")
public class ToDoController {


    private ToDoServices tds;

    @Autowired
    public ToDoController(ToDoServices tds) {
        this.tds = tds;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ToDo>> getAllToDos() {
        List<ToDo> getAllToDos = tds.getAllToDos();
        return ResponseEntity.status(HttpStatus.OK).body(getAllToDos);
    }



    @PostMapping("/todos")
    public ResponseEntity<?> createToDoHandler(@RequestBody ToDo todo) {
        ToDo createToDo = tds.createNewToDo(todo);
        if(createToDo != null) {
            return ResponseEntity.status(200).body(createToDo);
        } else {
            return ResponseEntity.status(400).build();
        }

    }


    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getToDoById(@PathVariable int id) {
        Optional<ToDo> optionalToDo = tds.getToDoById(id);
        if(optionalToDo.isPresent()) {
            ToDo todo = optionalToDo.get();
            return ResponseEntity.status(HttpStatus.OK).body(optionalToDo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @PutMapping("/todos/{id}")
    public ResponseEntity<ToDo> updateToDoById(@PathVariable int id,@RequestBody ToDo todo) {
        ToDo returnedToDo;

        try {
            returnedToDo = tds.updateToDoById(id, todo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(returnedToDo, HttpStatus.OK);
    }

    @DeleteMapping("/todos/{id}")
    public @ResponseBody ResponseEntity<?> deleteToDoById(@PathVariable int id) {
        ToDo deleteToDo = tds.deleteToDoById(id);
        return ResponseEntity.status(200).body("Todo Deleted!");
    }
}
