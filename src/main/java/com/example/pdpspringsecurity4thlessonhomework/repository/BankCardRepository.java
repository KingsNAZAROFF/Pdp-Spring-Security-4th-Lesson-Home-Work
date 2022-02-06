package com.example.pdpspringsecurity4thlessonhomework.repository;

import com.example.pdpspringsecurity4thlessonhomework.entity.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCardRepository extends JpaRepository<BankCard,Integer> {
    BankCard findByUserName(String userName);
    boolean existsByNumber(long number);
    boolean existsByUsername(String username);
    BankCard findByNumber(long number);
}
