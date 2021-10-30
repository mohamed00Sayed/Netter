package com.sayed.netter.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sayed.netter.Netter;

@Repository
public class JdbcNetterRepository implements NetterRepository {
  
  private JdbcOperations jdbc;

  @Autowired
  public JdbcNetterRepository(JdbcOperations jdbc) {
    this.jdbc = jdbc;
  }

  public Netter save(Netter netter) {
    jdbc.update(
        "insert into Netter (username, password, first_name, last_name, email)" +
        " values (?, ?, ?, ?, ?)",
        netter.getUsername(),
        netter.getPassword(),
        netter.getFirstName(),
        netter.getLastName(),
        netter.getEmail());
    return netter; // TODO: Determine value for id
  }

  public Netter findByUsername(String username) {
    return jdbc.queryForObject(
        "select id, username, null, first_name, last_name, email from Netter where username=?", 
        new NetterRowMapper(), 
        username);
  }
  
  private static class NetterRowMapper implements RowMapper<Netter> {
    public Netter mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Netter(
          rs.getLong("id"),
          rs.getString("username"),
          null,
          rs.getString("first_name"),
          rs.getString("last_name"),
          rs.getString("email"));
    }
  }

}
