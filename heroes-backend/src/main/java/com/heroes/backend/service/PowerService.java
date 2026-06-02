package com.heroes.backend.service;

import com.heroes.backend.model.Power;
import com.heroes.backend.repository.MissionRepository;
import com.heroes.backend.repository.PowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PowerService {

    private final PowerRepository powerRepository;

    public List<Power> findAll() {
        return powerRepository.findAll();
    }
}
