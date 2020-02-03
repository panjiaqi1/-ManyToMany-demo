package com.example.demo.repository;


import com.example.demo.entity.C;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CRepository extends JpaRepository<C, Long> {
}
