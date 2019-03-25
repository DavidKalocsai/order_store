package com.intland.repository;

import java.util.List;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.intland.model.Order;
import junit.framework.Assert;

/**
 * Testing {@link OrderRepositoryImpl}
 *
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-jdbc-config.xml")
public class OrderRepositoryImplTest {
  @Autowired
  private DataSource dataSource;

  @Autowired
  private OrderRepository orderRepository;

  @Before
  public void setup() {
    final SimpleJdbcCall simpleJdbcCall =
        new SimpleJdbcCall(dataSource).withProcedureName("add_test_data");
    simpleJdbcCall.execute();
  }

  @Test
  public void testOrders() {
    List<Order> orders = orderRepository.getOrders();
    Assert.assertEquals(4, orders.size());
    System.out.println(orders);

  }
}
