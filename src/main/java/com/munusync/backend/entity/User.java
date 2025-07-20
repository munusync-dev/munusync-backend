package com.munusync.backend.entity;


import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Data;

//identifying that this class in an entity like a table in the data base
@Entity
// no idea that's data for
@Data
@Table(name="users")
public class User {

    //set id to be the primary key of the table
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //increment the value of id
    // didn't understand the things inside parameters much make sure to ask
    private Long id;
    private String name;
    private String email;

    public User() {}  // Required by JPA and tools like Jackson

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
