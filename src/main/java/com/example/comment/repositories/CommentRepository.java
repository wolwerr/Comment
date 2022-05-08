package com.example.comment.repositories;

import com.example.comment.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}
