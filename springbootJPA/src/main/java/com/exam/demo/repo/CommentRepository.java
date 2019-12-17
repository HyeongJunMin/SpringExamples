package com.exam.demo.repo;

import com.exam.demo.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Repository나 JpaRepository를 상속받을 때 너무 많은 메소드가 생기는게 싫은 경우
 *
 * @RepositoryDefinition 애노테이션을 활용하게되면
 * 내가 정의한 메소드만 사용할 수 있도록 설정이 가능하다.
 * <p>
 * 공통메소드를 모아둔 MyRepository가 Repository를 상속받는 경우에도
 * MyRepository에 명시된 메소드만 사용할 수 있게된다.
 */
//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends MyRepository<Comment, Long> {
  //List<Comment> findByCommentContains(String keyword);
  //매개변수로 받은 post와 연관된 comment 중 likeCount가 매개변수로 받은 수 보다 큰 comment들만 리턴
  //Page<Comment> findByLikeCountGreaterThanAndPost(Integer likeCount, Post post, Pageable pageable);


  //키워드를 갖고있는 코멘트리스트 리턴, 대소문자 무시
  List<Comment> findByCommentContainsIgnoreCase(String keyword);

  //키워드를 가지면서 likeCount가 매개변수보다 큰 코멘트 리스트 리턴
  List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, Integer likeCount);

  //키워드를 갖고있는 코멘트리스트 likeCount 순서로 정렬해서 리턴, 대소문자 무시
  List<Comment> findByCommentContainsIgnoreCaseOrderByLikeCountDesc(String keyword);

  //키워드를 갖는 코멘트 페이지들을 pageable 조건에 맞게 리턴, 대소문자 무시
  Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);
}
