package com.revature.Services;


import com.revature.DAOS.ToDoDao;
import com.revature.Models.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServices {


    private ToDoDao tdd;

    @Autowired
    public  ToDoServices(ToDoDao tdd) {

        this.tdd = tdd;
    }

    public ToDo createNewToDo(ToDo todo) {
        ToDo returnedToDo = tdd.save(todo);
        return returnedToDo;
    }

    public List<ToDo> getAllToDos() {
        return tdd.findAll();
    }

    public Optional<ToDo> getToDoById(int id) {
        return tdd.findById(id);
    }

    public ToDo updateToDoById(int id, ToDo todo) {
        Optional<ToDo> possibleReturnedToDo = tdd.findById(id);
        ToDo returnedToDo = possibleReturnedToDo.orElseThrow();

        returnedToDo.setText(todo.getText());
        returnedToDo.setCompleted((todo.isCompleted()));
        return tdd.save(returnedToDo);
    }

    public ToDo deleteToDoById(int id) {
        Optional<ToDo> optionalToDo = tdd.findById(id);
        if(optionalToDo.isPresent()) {
            ToDo todo  = optionalToDo.get();
            tdd.deleteById(id);
            return todo;
        } else {
            return null;
        }
    }




}
