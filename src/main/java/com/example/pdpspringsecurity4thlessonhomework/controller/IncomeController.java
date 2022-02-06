package com.example.pdpspringsecurity4thlessonhomework.controller;

import com.example.pdpspringsecurity4thlessonhomework.entity.Income;
import com.example.pdpspringsecurity4thlessonhomework.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
    @Autowired
    OutcomeService outcomeService;

    @GetMapping("/myIncomes")
    public ResponseEntity<?> getAllIncomes(HttpServletRequest request){
        List<Income> allIncomes = outcomeService.getAllIncomes(request);
        return ResponseEntity.status(allIncomes!=null?200:204).body(allIncomes);
    }
}
