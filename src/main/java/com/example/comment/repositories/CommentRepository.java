package com.example.comment.repositories;

import com.example.comment.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Long> {
Optional<Comment> findCommentByEmail(String email);
}
