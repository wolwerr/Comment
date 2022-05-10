package com.example.comment.services;

import com.example.comment.enums.StatusComment;
import com.example.comment.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.comment.models.Comment;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private CommentService(CommentRepository commentRepository){this.commentRepository = commentRepository;
    }

    public Comment sendComment(Comment comment){
        comment.setInclusionDate(LocalDateTime.now());
        try {
            comment.setId(comment.getId());
            comment.setName(comment.getName());
            comment.setEmail(comment.getEmail());
            comment.setPassword(comment.getPassword());
            comment.setMessage(comment.getMessage());
            comment.setInclusionDate(comment.getInclusionDate());
            comment.setLoggedIn(comment.getLoggedIn());
            comment.setStatusComment(StatusComment.SENT);
            System.out.println("Comment sented");
        } catch (MailException e) {
            comment.setStatusComment(StatusComment.ERROR);
            System.out.println("Coment did not sented");
        } finally {
            return commentRepository.save(comment);
        }
    }

    public List<Comment> findAll(){
        return commentRepository.findAll();
    }
    public Optional<Comment> getById(Long Id){
        return commentRepository.findById(Id);
    }
}
