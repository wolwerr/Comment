package com.example.comment.models;


import com.example.comment.enums.StatusComment;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    @Email
    private String email;
    @Size(min = 6)
    private String password;
    private String message;
    private LocalDateTime inclusionDate;
    public StatusComment statusComment;

}

