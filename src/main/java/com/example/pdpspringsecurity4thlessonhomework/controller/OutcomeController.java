package com.example.pdpspringsecurity4thlessonhomework.controller;

import com.example.pdpspringsecurity4thlessonhomework.entity.Outcome;
import com.example.pdpspringsecurity4thlessonhomework.payload.ApiResponse;
import com.example.pdpspringsecurity4thlessonhomework.payload.OutcomeDto;
import com.example.pdpspringsecurity4thlessonhomework.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/outcome")
public class OutcomeController {

    @Autowired
    OutcomeService outcomeService;


    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendAmount(HttpServletRequest request, @RequestBody OutcomeDto outcomeDto){
        ApiResponse apiResponse = outcomeService.sendAmount(request, outcomeDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);

    }
    @GetMapping("/myOutcomes")
    public ResponseEntity<?> getAll(HttpServletRequest request){
        List<Outcome> allOutcomes = outcomeService.getAllOutcomes(request);
        return ResponseEntity.status(allOutcomes!=null?200:204).body(allOutcomes);
    }
}
