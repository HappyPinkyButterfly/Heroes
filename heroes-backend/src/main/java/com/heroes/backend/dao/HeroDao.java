package com.heroes.backend.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.heroes.backend.model.HeroRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HeroDao {

    private final JdbcTemplate jdbcTemplate;

    private HeroRow mapRow(ResultSet rs) throws SQLException {
        HeroRow heroRow = new HeroRow();
        heroRow.setId(rs.getLong("id"));
        heroRow.setName(rs.getString("name"));
        heroRow.setDescription(rs.getString("description"));
        heroRow.setCreatorUsername(rs.getString("username"));
        heroRow.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return heroRow;
    }

    public List<HeroRow> findAll() {
        String sql = """
                 SELECT h.id, h.name, h.description, h.created_at, u.username
                            FROM heroes h
                            JOIN users u ON h.user_id = u.id
                            ORDER BY h.id
                """;

        return jdbcTemplate.query(sql,(rs, rowNum) -> mapRow(rs));
    }

    public Optional<HeroRow> findById(Long id) {
        String sql = """
                 SELECT h.id, h.name, h.description, h.created_at, u.username
                            FROM heroes h
                            JOIN users u ON h.user_id = u.id
                            WHERE h.id = ?
                """;
        List<HeroRow> results =  jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs), id);
        return results.stream().findFirst();
    }
}
