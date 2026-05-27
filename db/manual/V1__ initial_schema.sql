CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       username VARCHAR(50) UNIQUE NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) DEFAULT 'USER',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE heroes (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        description TEXT,
                        status VARCHAR(20) DEFAULT 'active',
                        user_id UUID NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_hero_user
                            FOREIGN KEY (user_id)
                                REFERENCES users(id)
                                ON DELETE CASCADE
);

CREATE TABLE missions  (
                           id SERIAL PRIMARY KEY,
                           title VARCHAR(200) NOT NULL,
                           description TEXT,
                           hero_id INTEGER NOT NULL,
                           user_id UUID NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           CONSTRAINT fk_mission_hero
                               FOREIGN KEY (hero_id)
                                   REFERENCES heroes(id)
                                   ON DELETE CASCADE,
                           CONSTRAINT fk_mission_user
                               FOREIGN KEY (user_id)
                                   REFERENCES users (id)
                                   ON DELETE CASCADE
);

CREATE TABLE powers (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(50) UNIQUE NOT NULL

);

CREATE TABLE heroes_powers (
                               hero_id INTEGER NOT NULL,
                               power_id INTEGER NOT NULL,
                               PRIMARY KEY (hero_id, power_id),
                               CONSTRAINT fk_hp_hero FOREIGN KEY (hero_id) REFERENCES heroes(id) ON DELETE CASCADE,
                               CONSTRAINT fk_hp_power FOREIGN KEY (power_id) REFERENCES powers(id) ON DELETE CASCADE

);

INSERT INTO users (username, email, password, role) VALUES
                                                        ('tony_stark', 'tony@stark.com', '$2a$10$dummyhash1', 'USER'),
                                                        ('bruce_wayne', 'bruce@wayne.com', '$2a$10$dummyhash2', 'USER');

INSERT INTO powers (name) VALUES
                              ('Flight'),
                              ('Super Strength'),
                              ('Laser Eyes'),
                              ('Genius Intellect');

INSERT INTO heroes (name, description, user_id) VALUES
                                                    ('Iron Man', 'Genius billionaire with powered suit', (SELECT id FROM users WHERE username = 'tony_stark')),
                                                    ('Spider-Man', 'Friendly neighborhood web-slinger', (SELECT id FROM users WHERE username = 'tony_stark')),
                                                    ('Batman', 'Dark Knight of Gotham', (SELECT id FROM users WHERE username = 'bruce_wayne')),
                                                    ('Wonder Woman', 'Amazon princess warrior', (SELECT id FROM users WHERE username = 'bruce_wayne')),
                                                    ('Black Widow', 'Master spy and assassin', (SELECT id FROM users WHERE username = 'tony_stark'));

INSERT INTO missions (title, description, hero_id, user_id) VALUES
                                                                ('Stop alien invasion', 'Aliens are attacking NYC',
                                                                 (SELECT id FROM heroes WHERE name = 'Iron Man'),
                                                                 (SELECT id FROM users WHERE username = 'tony_stark')),
                                                                ('Rescue hostage', 'Bank robbery downtown',
                                                                 (SELECT id FROM heroes WHERE name = 'Spider-Man'),
                                                                 (SELECT id FROM users WHERE username = 'tony_stark')),
                                                                ('Investigate Riddler', 'Find clues in Gotham',
                                                                 (SELECT id FROM heroes WHERE name = 'Batman'),
                                                                 (SELECT id FROM users WHERE username = 'bruce_wayne')),
                                                                ('Defend Themyscira', 'Protect the island',
                                                                 (SELECT id FROM heroes WHERE name = 'Wonder Woman'),
                                                                 (SELECT id FROM users WHERE username = 'bruce_wayne')),
                                                                ('Infiltrate Hydra base', 'Gather intelligence',
                                                                 (SELECT id FROM heroes WHERE name = 'Black Widow'),
                                                                 (SELECT id FROM users WHERE username = 'tony_stark'));


INSERT INTO heroes_powers (hero_id, power_id) VALUES
                                                  ((SELECT id FROM heroes WHERE name = 'Iron Man'), (SELECT id FROM powers WHERE name = 'Flight')),
                                                  ((SELECT id FROM heroes WHERE name = 'Iron Man'), (SELECT id FROM powers WHERE name = 'Genius Intellect')),
                                                  ((SELECT id FROM heroes WHERE name = 'Spider-Man'), (SELECT id FROM powers WHERE name = 'Super Strength')),
                                                  ((SELECT id FROM heroes WHERE name = 'Batman'), (SELECT id FROM powers WHERE name = 'Genius Intellect')),
                                                  ((SELECT id FROM heroes WHERE name = 'Wonder Woman'), (SELECT id FROM powers WHERE name = 'Flight')),
                                                  ((SELECT id FROM heroes WHERE name = 'Wonder Woman'), (SELECT id FROM powers WHERE name = 'Super Strength')),
                                                  ((SELECT id FROM heroes WHERE name = 'Black Widow'), (SELECT id FROM powers WHERE name = 'Laser Eyes'));












