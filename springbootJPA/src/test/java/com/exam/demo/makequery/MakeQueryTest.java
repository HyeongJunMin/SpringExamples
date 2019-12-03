package com.exam.demo.makequery;

import com.exam.demo.domain.Comment;
import com.exam.demo.repo.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class MakeQueryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @Rollback(false)
    public void crud(){

        //Given
        this.createComment("spring boot", 0);
        this.createComment("spring boot!!", 100);

        log.info("============================================================");
        //When
        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("Spring");
        //Then
        Assertions.assertThat(comments.size()).isEqualTo(2);

        log.info("============================================================");
        //When
        comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("spring", 10);
        //Then
        Assertions.assertThat(comments.size()).isEqualTo(1);

        log.info("============================================================");
        //When
        comments = commentRepository.findByCommentContainsIgnoreCaseOrderByLikeCountDesc("spring");
        //Then
        Assertions.assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 100);

        log.info("============================================================");
        //When
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));
        Page<Comment> commentPage = commentRepository.findByCommentContainsIgnoreCase("spring", pageRequest);
        Assertions.assertThat(commentPage.getNumberOfElements()).isEqualTo(2);
        Assertions.assertThat(commentPage).first().hasFieldOrPropertyWithValue("likeCount", 100);
    }

    private void createComment(String content, int likeCount){
        Comment comment = new Comment();
        comment.setComment(content);
        comment.setLikeCount(likeCount);
        commentRepository.save(comment);
    }

}
