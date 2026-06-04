package com.heroes.backend.controller;

import com.heroes.backend.model.Mission;
import com.heroes.backend.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes/{heroId}/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public List<Mission> getByHero(@PathVariable Integer heroId) {
        return missionService.findByHeroId(heroId);
    }

    @PostMapping
    public Mission create(@PathVariable Integer heroId, @RequestBody Mission mission) {
        return missionService.create(heroId, mission);
    }
}