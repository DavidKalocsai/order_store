package com.pluralsight.repository;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import com.pluralsight.model.Order;
import com.pluralsight.repository.util.OrderRowMapper;

@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {

  @Autowired
  private DataSource dataSource;

  @Override
  public Order addOrder(final Order order) {
    // jdbcTemplate.update("insert into ride (name, duration) values (?,?)", ride.getName(),
    // ride.getDuration());

    // KeyHolder keyHolder = new GeneratedKeyHolder();
    // jdbcTemplate.update(new PreparedStatementCreator() {
    //
    // @Override
    // public PreparedStatement createPreparedStatement(Connection con)
    // throws SQLException {
    //
    // PreparedStatement ps = con.prepareStatement("insert into ride (name, duration) values (?,?)",
    // new String [] {"id"});
    // ps.setString(1, ride.getName());
    // ps.setInt(2, ride.getDuration());
    // return ps;
    // }
    // }, keyHolder);
    //
    // Number id = keyHolder.getKey();

    /*
     * SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
     * 
     * insert.setGeneratedKeyName("id");
     * 
     * Map<String, Object> data = new HashMap<>(); data.put("name", ride.getName());
     * data.put("duration", ride.getDuration());
     * 
     * List<String> columns = new ArrayList<>(); columns.add("name"); columns.add("duration");
     * 
     * insert.setTableName("ride"); insert.setColumnNames(columns); Number id =
     * insert.executeAndReturnKey(data);
     * 
     * return getOrder(id.intValue());
     */
    return order;
  }

  @Override
  public Order getOrder(final Integer id, final String group) {
    final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource)
        .withProcedureName("get_order").returningResultSet("orders", new OrderRowMapper());
    SqlParameterSource parameters =
        new MapSqlParameterSource().addValue("id", id).addValue("group_name", group);
    Map<String, Object> out = simpleJdbcCall.execute(parameters);

    return ((List<Order>) out.get("orders")).get(0);
  }

  @Override
  public List<Order> getOrders() {

    /*
     * List<Order> rides = jdbcTemplate.query("select * from ride", new OrderRowMapper());
     * 
     * return rides;
     */
    return null;
  }

  @Override
  public Order updateOrder(Order order) {
    /*
     * jdbcTemplate.update("update ride set name = ?, duration = ? where id = ?", ride.getName(),
     * ride.getDuration(), ride.getId());
     */
    return order;
  }

  @Override
  public void updateOrders(List<Order> orders) {
    // jdbcTemplate.batchUpdate("update ride set ride_date = ? where id = ?", pairs);
  }

  @Override
  public void deleteOrder(Integer id) {
    // jdbcTemplate.update("delete from ride where id = ?", id);
    /*
     * NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
     * 
     * Map<String, Object> paramMap = new HashMap<>(); paramMap.put("id", id);
     * 
     * namedTemplate.update("delete from ride where id = :id", paramMap);
     */
  }
}
