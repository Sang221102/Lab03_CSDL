package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="username",length = 50, nullable = false,unique = true)
    @NotBlank(message = "Username is required")
    @Size(max = 50,message = " Username must be less than 50 characters")
    private String username;

    @Column(name ="password",length = 250, nullable = false)
    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 50,message = " Username must be less than 50 characters")
    private String email;

    @Column(name ="name",length = 50, nullable = false)
    @NotBlank(message = "Name is required")
    @Size(max = 50,message = " Username must be less than 50 characters")
    private String name;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();
}
