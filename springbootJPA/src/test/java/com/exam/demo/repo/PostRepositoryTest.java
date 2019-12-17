package com.exam.demo.repo;

import com.exam.demo.domain.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest //Data Access Layer에서만, Transactional옵션이 들어있기 때문에 아래 Test들에 대한 내용은 모두 롤
//@DataJpaTest는 디펜던시에 H2 DB가 있으면 H2에서만 테스트돌림(원래 쓰던 DB 말고)
public class PostRepositoryTest {

  @Autowired
  PostRepository postRepository;

  @Test
  @Rollback(false)//Transactional 옵션 때문에 하이버네이트가 쿼리를 안보내기 때문에 Rollback false로 설정해야 테스트 가
  public void repositoryCrudTest() {
    //Given
    Post post = new Post();
    post.setTitle("hello spring boot common");

    //When
    Post newPost = postRepository.save(post);
    //Then
    assertThat(newPost.getId()).isNotNull();    //assertj에 있는 메소드

    //When
    List<Post> posts = postRepository.findAll();
    //Then
    assertThat(posts.size()).isEqualTo(1);
    assertThat(posts).contains(newPost);

    //When
    Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));
    //Then
    assertThat(page.getTotalElements()).isEqualTo(1);   //총 개수
    assertThat(page.getNumber()).isEqualTo(0);  //현재 페이지 번호
    assertThat(page.getSize()).isEqualTo(10);   //현재 페이지에 보여줄 수 있는 최대 개수
    assertThat(page.getNumberOfElements()).isEqualTo(1);    //가져온 개수

    //When
    postRepository.findByTitleContains("spring", PageRequest.of(0, 10));
    //Then
    assertThat(page.getTotalElements()).isEqualTo(1);
    assertThat(page.getNumber()).isEqualTo(0);
    assertThat(page.getSize()).isEqualTo(10);
    assertThat(page.getNumberOfElements()).isEqualTo(1);

    //When
    long spring = postRepository.countByTitleContains("spring");
    //Then
    assertThat(spring).isEqualTo(1);
  }
}
