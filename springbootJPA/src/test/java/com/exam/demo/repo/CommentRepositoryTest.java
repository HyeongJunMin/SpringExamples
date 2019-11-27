package com.exam.demo.repo;

import com.exam.demo.domain.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @Rollback(false)
    public void crud(){
        //Given
        Comment comment = new Comment();
        comment.setComment("Hello Comment");
        commentRepository.save(comment);

        //When
        List<Comment> all = commentRepository.findAll();
        //Then
        assertThat(all.size()).isEqualTo(1);

        //When
        long count = commentRepository.count();
        //Then
        assertThat(count).isEqualTo(1);

        //When
        Comment cmtNullChk = commentRepository.findById(100L);
        //Then
        assertThat(cmtNullChk).isNull();
        
        //When Optional<Comment> byId = commentRepository.findByIdReturnOptional(100L);
        //Then assertThat(byId).isEmpty(); Comment orElseThrow = byId.orElseThrow(IllegalAccessError::new); //byId가 없으면 예외생성
    }
}
