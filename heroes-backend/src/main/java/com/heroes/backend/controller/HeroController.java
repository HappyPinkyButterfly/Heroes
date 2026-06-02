package com.heroes.backend.controller;

import com.heroes.backend.model.Hero;
import com.heroes.backend.repository.HeroRepository;
import com.heroes.backend.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;

    @GetMapping
    public List<Hero> getHeroes() {
        return heroService.findAll();
    }

    @GetMapping("/{id}")
    public Hero getHero(@PathVariable Integer id) {
        return heroService.findById(id);
    }

    @PostMapping
    public Hero createHero(@RequestBody Hero hero) {
        return heroService.create(hero);
    }

    @PutMapping("/{id}")
    public Hero updateHero(@PathVariable Integer id, @RequestBody Hero hero) {
        return heroService.update(id, hero);
    }

    @DeleteMapping("/{id}")
    public void deleteHero(@PathVariable Integer id) {
        heroService.delete(id);
    }
}
