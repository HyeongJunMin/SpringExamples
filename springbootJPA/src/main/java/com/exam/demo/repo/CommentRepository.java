package com.exam.demo.repo;

import com.exam.demo.domain.Comment;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/**
 * Repository나 JpaRepository를 상속받을 때 너무 많은 메소드가 생기는게 싫은 경우
 * @RepositoryDefinition 애노테이션을 활용하게되면
 * 내가 정의한 메소드만 사용할 수 있도록 설정이 가능하다.
 *
 * 공통메소드를 모아둔 MyRepository가 Repository를 상속받는 경우에도
 * MyRepository에 명시된 메소드만 사용할 수 있게된다.
 */
@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends MyRepository<Comment, Long>{
}
