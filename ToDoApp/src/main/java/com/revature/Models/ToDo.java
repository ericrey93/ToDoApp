package com.revature.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "todos")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "text")
    private String text;


    @Column(name = "completed")
    private boolean isCompleted;

    public ToDo() {

    }

    public ToDo(int id, String text, boolean isCompleted) {
        this.id = id;
        this.text = text;
        this.isCompleted = isCompleted;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public boolean isCompleted() {

        return isCompleted;
    }

    public void setCompleted(boolean completed) {

        isCompleted = completed;
    }


    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return id == toDo.id && isCompleted == toDo.isCompleted && Objects.equals(text, toDo.text);
    }

}
