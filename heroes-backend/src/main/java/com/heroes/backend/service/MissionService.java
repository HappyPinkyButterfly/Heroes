package com.heroes.backend.service;

import com.heroes.backend.model.Hero;
import com.heroes.backend.model.Mission;
import com.heroes.backend.repository.HeroRepository;
import com.heroes.backend.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final HeroRepository heroRepository;

    public List<Mission> findByHeroId(Integer heroId) {
        return missionRepository.findByHeroId(heroId);
    }

    public Mission create(Integer heroId, Mission mission) {
        Hero hero = heroRepository.findById(heroId)
                .orElseThrow(() -> new RuntimeException("Hero not found: " + heroId));
        mission.setHero(hero);
        return missionRepository.save(mission);
    }
}