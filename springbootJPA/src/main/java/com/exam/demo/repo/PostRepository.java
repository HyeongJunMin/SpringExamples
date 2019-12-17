package com.exam.demo.repo;

import com.exam.demo.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository가 갖는 메소드를 사용할 수 있으면서 직접 정의한 메소드도 사용할 수 있다
 */
public interface PostRepository extends JpaRepository<Post, Long> {

  //특정 키워드를 갖는 엔티티의 수
  long countByTitleContains(String title);

  //특정 키워드를 갖는 목록을 찾는 메소드
  Page<Post> findByTitleContains(String title, Pageable pageable);

}
