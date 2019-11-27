package com.exam.demo.repo;

import com.exam.demo.domain.Comment;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 여러 레파지토리에서 공통으로 사용하고 싶은 메소드가 있을 때 공통 메소드들을 설정하는 방법
 */
@NoRepositoryBean
public interface MyRepository<T, Id extends Serializable> extends Repository<T, Id> {

    //타입 T와 T를 상속받는 하위타입 E까지 허용
    //런타임 시점에 null체크
    <E extends T> E save(@NonNull E entity);

    List<T> findAll();

    long count();

    //엔티티타입 리턴
    @Nullable
    <E extends T> E findById(Id id);

    //Optional 인터페이스가 제공하는 메소드를 통해 검사 가능
    //<E extends T> Optional<E> findById(Id id);
}
