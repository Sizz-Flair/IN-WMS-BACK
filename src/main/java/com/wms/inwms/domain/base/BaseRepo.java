package com.wms.inwms.domain.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepo<T, ID extends java.io.Serializable> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {}
