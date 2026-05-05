package com.api.BackChaskas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    List<T> findByActiveTrue();           // Común en muchas entidades
    List<T> findByActiveFalse();
}
