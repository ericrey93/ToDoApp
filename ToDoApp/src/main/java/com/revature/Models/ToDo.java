package com.revature.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "List")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    @Column(name = "completed")
    private boolean isCompleted;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return id == toDo.id && isCompleted == toDo.isCompleted && Objects.equals(content, toDo.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, isCompleted);
    }
}
