package com.example.springboot.dao;

import com.example.springboot.model.IndianNameModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IndianNameDao {

    private final JdbcTemplate jdbcTemplate;

    IndianNameDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public IndianNameModel getNameById(int id){
        String sql = "select * from learnspringboot.indian_names where id = ?";
        return jdbcTemplate.queryForObject(sql, new NameRowMapper(), id);
    }

    private static class NameRowMapper implements RowMapper<IndianNameModel> {
        @Override
        public IndianNameModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            IndianNameModel nameModel = new IndianNameModel();
            nameModel.setId(rs.getInt("id"));
            nameModel.setName(rs.getString("name"));

            return nameModel;
        }
    }
}
