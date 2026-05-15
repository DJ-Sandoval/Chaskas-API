package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.auditoria.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
