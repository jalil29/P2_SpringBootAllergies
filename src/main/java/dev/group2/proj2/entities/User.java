package dev.group2.proj2.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid")
    private int userId;

    @Column(name="username")
    private String userName;

    @Column(name="pass")
    private String password;



}
