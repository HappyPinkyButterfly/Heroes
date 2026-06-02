package com.heroes.backend.controller;

import com.heroes.backend.dao.HeroDao;
import com.heroes.backend.model.HeroRow;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RequestMapping("/heroes")
@RequiredArgsConstructor
@RestController
public class HeroJdbcController {

    private final HeroDao heroDao;

    @GetMapping
    public List<HeroRow>  getHeroes() {
        return heroDao.findAll();
    }

    @GetMapping("/{id}")
    public HeroRow getHero(@PathVariable Long id) {
        return heroDao.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hero not found")
        );
    }



}
