package com.example.energyapi.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/energy")
public class EnergyController {

    @GetMapping("/current")
    public Map<String, Object> getCurrentData() {
        return Map.of(
                "communityPool", "78.54%",
                "gridPortion", "7.23%",
                "communityProduced", "143.024 kWh",
                "communityUsed", "130.101 kWh",
                "gridUsed", "14.75 kWh"
        );
    }

    @GetMapping("/history")
    public Map<String, Object> getHistoricalData(
            @RequestParam String from,
            @RequestParam String to) {
        return Map.of(
                "from", from,
                "to", to,
                "communityUsed", "130.101 kWh",
                "gridUsed", "14.75 kWh"
        );
    }
}
