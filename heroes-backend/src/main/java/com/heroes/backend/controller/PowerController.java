package com.heroes.backend.controller;

import com.heroes.backend.model.Mission;
import com.heroes.backend.model.Power;
import com.heroes.backend.service.MissionService;
import com.heroes.backend.service.PowerService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/powers")
public class PowerController {

    private final PowerService powerService;

    @GetMapping
    public List<Power> getPowers() {
        return powerService.findAll();
    }
}
