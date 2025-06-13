package com.example.message.model;

import jakarta.persistence.*;

@Entity
@Table(name = "userlist")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    public Message() {
    }

    public Message(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

}