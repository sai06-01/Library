package com.example.demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.RechargeService;
import com.example.demo.model.Recharge;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recharges")
public class RechargeController {

    private RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    // Get all recharges
    @GetMapping
    public List<Recharge> getAllRecharges() {
        return rechargeService.getAllRecharges();
    }

    // Add recharge
    @PostMapping
    public Recharge addRecharge(
            @Valid @RequestBody Recharge recharge) {

        return rechargeService.addRecharge(recharge);
    }

    // Search recharge by ID
    @GetMapping("/{rechargeId}")
    public Recharge getRechargeById(
            @PathVariable int rechargeId) {

        return rechargeService.getRechargeById(rechargeId);
    }

    // Delete recharge
    @DeleteMapping("/{rechargeId}")
    public String deleteRecharge(
            @PathVariable int rechargeId) {

        rechargeService.deleteRecharge(rechargeId);

        return "Recharge deleted successfully";
    }
}