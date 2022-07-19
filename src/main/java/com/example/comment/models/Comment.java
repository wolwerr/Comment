package com.example.comment.models;


import com.example.comment.enums.StatusComment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Email
    @Column(unique=true)
    private String email;
    @Size(min = 6)
    private String password;
    private String message;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private LocalDateTime inclusionDate;
    public StatusComment statusComment;
    public Boolean loggedIn;


    public Comment(@NotBlank String email,
                    @NotBlank String password) {
        this.email =   email;
        this.password = password;
        this.loggedIn = false;
    }

}

