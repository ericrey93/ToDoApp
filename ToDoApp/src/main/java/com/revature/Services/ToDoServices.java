package com.revature.Services;


import com.revature.DAOS.ToDoDao;
import com.revature.Models.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToDoServices {


    private ToDoDao tdd;

    @Autowired
    public  ToDoServices(ToDoDao tdd) {
        this.tdd = tdd;
    }

    public ToDo createNewToDo(ToDo todo) {
     return tdd.save(todo);
    }

    public ToDo getToDOById(int id){
        Optional<ToDo> returnedToDo = tdd.findById(id);
        return returnedToDo.orElseThrow();
    }

    public ToDo updateToDoById(int id, ToDo todo) {
        Optional<ToDo> possibleReturnedToDo = tdd.findById(id);
        ToDo returnedToDo = possibleReturnedToDo.orElseThrow();

        returnedToDo.setContent(todo.getContent());
        returnedToDo.setCompleted((todo.isCompleted()));
        return tdd.save(returnedToDo);
    }


}
