package com.heroes.backend.service;

import com.heroes.backend.model.Hero;
import com.heroes.backend.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;

    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    public Hero findById(int id) {
        return heroRepository.findById(id).orElseThrow(()-> new RuntimeException("Hero not found: " + id));
    }

    public Hero create(Hero hero) {
        return heroRepository.save(hero);
    }

    public Hero update(Integer id, Hero hero) {
        Hero existingHero = findById(id);
        existingHero.setName(hero.getName());
        existingHero.setDescription(hero.getDescription());
        existingHero.setStatus(hero.getStatus());
        return heroRepository.save(existingHero);
    }

    public void delete(Integer id) {
        heroRepository.deleteById(id);
    }
}
