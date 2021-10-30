package com.sayed.netter.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sayed.netter.Nettle;

@Repository
public class JdbcNettleRepository implements NettleRepository {

  private JdbcOperations jdbc;

  @Autowired
  public JdbcNettleRepository(JdbcOperations jdbc) {
    this.jdbc = jdbc;
  }

  public List<Nettle> findRecentNettles() {
    return jdbc.query(
        "select id, message, created_at, latitude, longitude" +
        " from Nettle" +
        " order by created_at desc limit 20",
        new NettleRowMapper());
  }

  public List<Nettle> findNettles(long max, int count) {
    return jdbc.query(
        "select id, message, created_at, latitude, longitude" +
        " from Nettle" +
        " where id < ?" +
        " order by created_at desc limit 20",
        new NettleRowMapper(), max);
  }

  public Nettle findOne(long id) {
    return jdbc.queryForObject(
        "select id, message, created_at, latitude, longitude" +
        " from Nettle" +
        " where id = ?",
        new NettleRowMapper(), id);
  }

  public void save(Nettle nettle) {
    jdbc.update(
        "insert into Nettle (message, created_at, latitude, longitude)" +
        " values (?, ?, ?, ?)",
        nettle.getMessage(),
        nettle.getTime(),
        nettle.getLatitude(),
        nettle.getLongitude());
  }

  private static class NettleRowMapper implements RowMapper<Nettle> {
    public Nettle mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Nettle(
          rs.getLong("id"),
          rs.getString("message"), 
          rs.getDate("created_at"), 
          rs.getDouble("longitude"), 
          rs.getDouble("latitude"));
    }
  }
  
}
