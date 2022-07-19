package com.example.comment;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import com.example.comment.services.CommentService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.comment.models.Comment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@EnableJpaRepositories
class CommentApplicationTests {

    @Autowired
    CommentService commentService;

    @Test
    void contextLoads() {
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Teste de persistir comment")
    void deveriaCriarUmComent() throws Exception {
        Comment comment = Comment.builder().build(
        );

        commentService.sendComment(comment);

        assertThat( comment.getId(), notNullValue());


    }

}
