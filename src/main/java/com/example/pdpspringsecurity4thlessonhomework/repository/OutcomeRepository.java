package com.example.pdpspringsecurity4thlessonhomework.repository;

import com.example.pdpspringsecurity4thlessonhomework.entity.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome,Integer> {
    List<Outcome> findAllByFromCardId(long fromCardId);

}
