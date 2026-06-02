package com.heroes.backend.repository;

import com.heroes.backend.model.Mission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MissionRepository extends CrudRepository<Mission,Integer> {

    List<Mission> findByHeroId(Integer  heroId);
}
