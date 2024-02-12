package com.revature.Controllers;

import com.revature.DAOS.UserDao;
import com.revature.Models.ToDo;
import com.revature.Models.Users;
import com.revature.Services.ToDoServices;
import com.revature.Services.UserServices;
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


    private final ToDoServices tds;
    private final UserServices uss;

    @Autowired
    public ToDoController(ToDoServices tds, UserServices uss, UserDao userDao) {
        this.tds = tds;
        this.uss = uss;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        Users newUser = uss.registerUser(user);
        if(newUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body(newUser);
        } else {
            Users checkAcc = uss.findByUsername(user.getUsername());
            if(checkAcc != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Account Exists.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
    } @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Users users) {
        Users checkAcc = uss.findByUsername(users.getUsername());
        if(checkAcc != null && checkAcc.getPassword().equals(users.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body(checkAcc);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username and/or password");
        }
    }





    @GetMapping("/todos")
    public ResponseEntity<List<ToDo>> getAllToDos() {
        List<ToDo> getAllToDos = tds.getAllToDos();
        return ResponseEntity.status(HttpStatus.OK).body(getAllToDos);
    }



    @PostMapping("/todos")
    public ResponseEntity<?> createToDos(@RequestBody ToDo todo) {
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
