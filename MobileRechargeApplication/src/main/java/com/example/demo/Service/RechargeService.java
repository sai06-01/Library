package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Exception.RechargeNotFoundException;
import com.example.demo.model.Recharge;

@Service
public class RechargeService {

    List<Recharge> recharges = new ArrayList<>(Arrays.asList(

        new Recharge(101, "9876543210",
                "Jio", 299, "Prepaid"),

        new Recharge(102, "9123456789",
                "Airtel", 399, "Prepaid"),

        new Recharge(103, "9988776655",
                "Vi", 249, "Prepaid"),

        new Recharge(104, "9012345678",
                "BSNL", 199, "Prepaid")
    ));

    // Get all recharges
    public List<Recharge> getAllRecharges() {
        return recharges;
    }

    // Add recharge
    public Recharge addRecharge(Recharge recharge) {

        recharges.add(recharge);

        return recharge;
    }

    // Search recharge by ID
    public Recharge getRechargeById(int rechargeId) {

        return recharges.stream()
                .filter(recharge ->
                    recharge.getRechargeId() == rechargeId)
                .findFirst()
                .orElseThrow(() ->
                    new RechargeNotFoundException(
                        "Recharge ID not found : " + rechargeId));
    }

    // Delete recharge
    public boolean deleteRecharge(int rechargeId) {

        Recharge recharge = getRechargeById(rechargeId);

        recharges.remove(recharge);

        return true;
    }
}