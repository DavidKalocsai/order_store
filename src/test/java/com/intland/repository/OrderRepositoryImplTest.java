package com.intland.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.intland.model.Order;
import com.intland.model.OrderId;
import com.intland.model.OrderStatus;

/**
 * Testing {@link OrderRepositoryImpl}
 *
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-jdbc-config.xml")
public class OrderRepositoryImplTest {
  private static final int ORDERS_SIZE = 4;
  private static final int ORDER_ID = 1;
  private static final String ORDER_GROUP = "qlpyurmyu4228492mmnprgvma";
  private static final OrderStatus ORDER_STATUS = OrderStatus.ACTIVE;
  private static final Date ORDER_DATE = new Date(2019, 8, 21);
  private static final String ORDER_DESCRIPTION = "Description";

  @Autowired
  private DataSource dataSource;

  @Autowired
  private OrderRepository orderRepository;

  @Before
  public void setup() {
    addTestData();
  }

  @Test
  public void addOrderShouldReturnThePassedInOrderWithOrderId() {
    final Order order = createAddOrder();

    Order retOrder = orderRepository.addOrder(order).get();

    validateOrder(order, retOrder);
    Assert.assertThat(1, is(equalTo(retOrder.getId())));
    Assert.assertThat(1, is(equalTo(retOrder.getVersion())));
  }

  @Test
  public void getOrderShouldReturnNewlyAddedOrder() {
    final Order order = createAddOrder();

    final Order expectedOrder = orderRepository.addOrder(order).get();
    final OrderId orderId = createOrderId(expectedOrder);
    final Order actualOrder = orderRepository.getOrder(orderId).get();

    validateOrder(expectedOrder, actualOrder);
    Assert.assertThat(1, is(equalTo(actualOrder.getId())));
    Assert.assertThat(1, is(equalTo(actualOrder.getVersion())));
  }

  @Test
  public void updateShouldUpdateNewlyAddedOrder() {
    final Order order = createAddOrder();

    final Order expectedOrder = orderRepository.addOrder(order).get();
    final Order actualOrder = orderRepository.updateOrder(expectedOrder).get();

    validateOrder(expectedOrder, actualOrder);
    Assert.assertThat(1, is(equalTo(actualOrder.getId())));
    Assert.assertThat(2, is(equalTo(actualOrder.getVersion())));
  }

  @Test
  public void getOrdersShouldReturnEveryTestOrderWhenRequested() {
    List<Order> orders = orderRepository.getOrders();
    List<Order> testData = orders.stream().filter(x -> x.getId() < 0).collect(Collectors.toList());
    Assert.assertThat(ORDERS_SIZE, is(equalTo(testData.size())));
  }

  private void addTestData() {
    final SimpleJdbcCall simpleJdbcCall =
        new SimpleJdbcCall(dataSource).withProcedureName("add_test_data");
    simpleJdbcCall.execute();
  }

  private Order createAddOrder() {
    final Order order = new Order();
    order.setId(ORDER_ID);
    order.setGroup(ORDER_GROUP);
    order.setDate(ORDER_DATE);
    order.setDescription(ORDER_DESCRIPTION);
    order.setStatus(ORDER_STATUS);
    return order;
  }

  private OrderId createOrderId(final Order order) {
    final OrderId orderId = new OrderId();
    orderId.setId(order.getId());
    orderId.setGroup(order.getGroup());
    return orderId;
  }

  private void validateOrder(final Order expectedOrder, final Order actualOrder) {
    Assert.assertThat(expectedOrder.getDate(), is(equalTo(actualOrder.getDate())));
    Assert.assertThat(expectedOrder.getGroup(), is(equalTo(actualOrder.getGroup())));
    Assert.assertThat(expectedOrder.getDescription(), is(equalTo(actualOrder.getDescription())));
    Assert.assertThat(expectedOrder.getStatus(), is(equalTo(actualOrder.getStatus())));
  }
}
