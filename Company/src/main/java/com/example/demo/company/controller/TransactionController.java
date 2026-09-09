package com.example.demo.company.controller;

import com.example.demo.company.enums.TransactionStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @PostMapping("/process")
    @Transactional
    public ResponseEntity<Map<String, Object>> processTransaction() {

        Map<String, Object> response =
                new LinkedHashMap<>();

        response.put("message",
                "Transaction processed successfully");

        response.put("status",
                TransactionStatus.SUCCESS);

        return ResponseEntity.ok(response);
    }
}