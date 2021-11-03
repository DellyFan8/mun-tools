package com.example.application.database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswersRepository extends JpaRepository<Votes, Integer> {
}
