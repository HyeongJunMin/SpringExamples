package com.example.querydsl.repository;

import com.example.querydsl.domain.AbstractEntity;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Transactional
public class GenericRepository<T extends AbstractEntity> {

  protected EntityManager em;

  private final Class<T> entityClass;

  public void save(T entity) {
    if (entity.getId() == null) {
      em.persist(entity);
    } else {
      em.merge(entity);
    }
  }

  public T findOne(Long id) {
    return em.find(entityClass, id);
  }

  public List<T> findAll() {
    return this.em.createQuery("select obj from " + this.entityClass.getName() + " obj").getResultList();
  }

}
