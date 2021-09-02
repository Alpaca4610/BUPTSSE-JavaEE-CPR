package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Class;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class, String> {
    Optional<Class> findById(String s);
}
