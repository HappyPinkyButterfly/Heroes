package com.heroes.backend.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HeroRow {
    private Long id;
    private String name;
    private String description;
    private String creatorUsername;
    private LocalDateTime createdAt;
}
