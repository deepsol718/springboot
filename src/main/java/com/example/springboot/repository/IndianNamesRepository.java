package com.example.springboot.repository;

import com.example.springboot.entity.IndianName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndianNamesRepository extends JpaRepository<IndianName, Integer> {
}