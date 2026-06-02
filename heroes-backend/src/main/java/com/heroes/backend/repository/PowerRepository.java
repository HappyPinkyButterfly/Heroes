package com.heroes.backend.repository;

import com.heroes.backend.model.Power;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PowerRepository extends JpaRepository<Power,Long> {
}
