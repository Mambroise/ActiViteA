package com.example.activitea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.activitea.entity.CoverLetter;

public interface CoverLetterRepository extends JpaRepository<CoverLetter, Integer> {

    List<CoverLetter> findByUserId(int userId);

    CoverLetter findTopByUserIdOrderByIdDesc(int userId);
}

