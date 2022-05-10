package com.example.comment.controllers;

import com.example.comment.dto.CommentDTO;
import com.example.comment.enums.Status;
import com.example.comment.exception.ResourcesNotFoundException;
import com.example.comment.repositories.CommentRepository;
import com.example.comment.services.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.comment.models.Comment;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;


    @PostMapping("/comment")
    public ResponseEntity<Comment> sendingComent(@RequestBody @Valid CommentDTO commentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        commentService.sendComment(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/comments")
    public ResponseEntity<Comment> getAllComments(){
        return new ResponseEntity(commentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/comment/{Id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "Id") Long Id) throws ResourcesNotFoundException {
            Comment comment = commentService.getById(Id)
                    .orElseThrow(()-> new ResourcesNotFoundException("Comment not found: " + Id));
            return ResponseEntity.ok().body(comment);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<Comment> updateUser(@PathVariable(value = "id") Long Id,
                                           @Valid @RequestBody Comment comentDetails ) throws ResourcesNotFoundException {
        Comment comment = commentService.getById(Id)
                .orElseThrow(()-> new ResourcesNotFoundException("Comment not found for this Id: " + Id));
        comment.setName(comentDetails.getName());
        comment.setEmail(comentDetails.getEmail());
        comment.setPassword(comentDetails.getPassword());
        comment.setMessage(comentDetails.getMessage());
        final Comment updateComment = commentRepository.save(comment);
        return ResponseEntity.ok(updateComment);
    }

    @DeleteMapping("/comment/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long Id) throws ResourcesNotFoundException{
        Comment comment = commentService.getById(Id)
                .orElseThrow(()-> new ResourcesNotFoundException("User not found for this Id: " + Id));
        commentRepository.delete(comment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Usuário deletado", Boolean.TRUE);
        return response;
    }

    @GetMapping("/comment/login")
    public Status loginUser(@Valid @RequestBody Comment comment) {
        List<Comment> comments = commentRepository.findAll();
        for (Comment other : comments) {
            if (other.equals(comment)) {
                comment.setLoggedIn(true);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    @GetMapping("/comment/logout")
    public Status logUserOut(@Valid @RequestBody Comment comment) {
        List<Comment> users = commentRepository.findAll();
        for (Comment other : users) {
            if (other.equals(comment)) {
                comment.setLoggedIn(false);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

}
