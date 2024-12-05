package com.example.springboot.dao;

import com.example.springboot.model.IndianName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndianNamesDAO extends JpaRepository<IndianName, Integer> {
}