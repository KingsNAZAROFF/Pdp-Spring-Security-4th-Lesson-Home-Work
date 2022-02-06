package com.example.pdpspringsecurity4thlessonhomework.service;

import com.example.pdpspringsecurity4thlessonhomework.entity.BankCard;
import com.example.pdpspringsecurity4thlessonhomework.entity.Income;
import com.example.pdpspringsecurity4thlessonhomework.entity.Outcome;
import com.example.pdpspringsecurity4thlessonhomework.payload.ApiResponse;
import com.example.pdpspringsecurity4thlessonhomework.payload.OutcomeDto;
import com.example.pdpspringsecurity4thlessonhomework.repository.BankCardRepository;
import com.example.pdpspringsecurity4thlessonhomework.repository.IncomeRepository;
import com.example.pdpspringsecurity4thlessonhomework.repository.OutcomeRepository;
import com.example.pdpspringsecurity4thlessonhomework.security.jwt.JwtFilter;
import com.example.pdpspringsecurity4thlessonhomework.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class OutcomeService {
    @Autowired
    OutcomeRepository outcomeRepository;
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    BankCardRepository bankCardRepository;
    @Autowired
    JwtProvider jwtProvider;



    public ApiResponse sendAmount(HttpServletRequest request,OutcomeDto outcomeDto){



        String token = request.getHeader("Authorization");
        token=token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        boolean byUsername = bankCardRepository.existsByUsername(username);

        if (!byUsername){
            return new ApiResponse("Siz accountingizga hech qanday bank kartasini qo'shmagansiz",false);
        }
        boolean existsByNumber = bankCardRepository.existsByNumber(outcomeDto.getToCardId());
        if (!existsByNumber){
            return new ApiResponse("Bunday bank kartasi mavjud emas",false);
        }
        BankCard bankCardRepositoryByUserName = bankCardRepository.findByUserName(username);
        if (bankCardRepositoryByUserName.getBalance()< outcomeDto.getAmount()*1.01){
            return new ApiResponse("Xisobingizda yetarli mablag' mavjud emas",false);
        }

        BankCard cardIncome = bankCardRepository.findByNumber(outcomeDto.getToCardId());

        Date date = new Date();
        Outcome outcome = new Outcome();
        outcome.setFromCardId(bankCardRepositoryByUserName.getNumber());
        outcome.setToCardId(outcome.getToCardId());
        outcome.setAmount(outcomeDto.getAmount());
        outcome.setDate(date);
        outcome.setCommissionAmount(outcomeDto.getAmount()*0.01);

        Income income = new Income();
        income.setFromCardId(bankCardRepositoryByUserName.getNumber());
        income.setToCardId(outcomeDto.getToCardId());
        income.setAmount(outcomeDto.getAmount());
        income.setDate(date);
        double balanceForIncome = cardIncome.getBalance()+ outcome.getAmount();
        cardIncome.setBalance(balanceForIncome);

        double newBalance = bankCardRepositoryByUserName.getBalance()-outcomeDto.getAmount()*1.01;
        bankCardRepositoryByUserName.setBalance(newBalance);

        bankCardRepository.save(bankCardRepositoryByUserName);
        outcomeRepository.save(outcome);
        incomeRepository.save(income);
        bankCardRepository.save(cardIncome);
        return new ApiResponse("Pul o'tkazmasi muvaffaqiyatli amalga oshirildi",true);


    }
    public List<Outcome> getAllOutcomes(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token=token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        boolean byUsername = bankCardRepository.existsByUsername(username);
        if (!byUsername){
            return null;
        }
        BankCard bankCard = bankCardRepository.findByUserName(username);
        List<Outcome> outcomeList = outcomeRepository.findAllByFromCardId(bankCard.getNumber());
        return outcomeList;

    }
    public List<Income> getAllIncomes(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token=token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        boolean byUsername = bankCardRepository.existsByUsername(username);
        if (!byUsername){
            return null;
        }
        BankCard bankCard = bankCardRepository.findByUserName(username);
        List<Income> incomeList = incomeRepository.findAllByToCardId(bankCard.getNumber());
        return incomeList;

    }
}
