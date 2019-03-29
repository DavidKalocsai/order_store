package com.intland.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.intland.model.Order;
import com.intland.model.OrderDatabaseObj;
import com.intland.model.OrderId;
import com.intland.model.OrderStatus;

/**
 * Testing {@link OrderRepositoryImpl}.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-jdbc-config.xml")
public class OrderRepositoryImplTest {
  private static final String ORDER_GROUP = "qlpyurmyu4228492mmnprgvma";
  private static final OrderStatus ORDER_STATUS = OrderStatus.ACTIVE;
  private static final Date ORDER_DATE = new Date(2017, 03, 18); // TODO deprecated
  private static final String ORDER_DESCRIPTION = "Description";

  @Autowired
  private DataSource dataSource;

  @Autowired
  private OrderRepository orderRepository;

  @Test
  public void addOrderShouldReturnThePassedInOrderExtendedWithData() {
    final Order order = createAddOrder();

    final OrderDatabaseObj retOrder = orderRepository.addOrder(order);

    validateOrder(order, retOrder);
    Assert.assertThat(1, is(equalTo(retOrder.getGroupOrderId())));
    Assert.assertThat(1, is(equalTo(retOrder.getVersion())));
  }

  @Test
  public void getOrderShouldReturnNewlyAddedOrder() {
    final Order order = createAddOrder();

    final OrderDatabaseObj expectedOrder = orderRepository.addOrder(order);
    final OrderId orderId = createOrderId(expectedOrder);
    final OrderDatabaseObj actualOrder = orderRepository.getOrder(orderId).get();

    validateOrder(expectedOrder, actualOrder);
    Assert.assertThat(1, is(equalTo(actualOrder.getGroupOrderId())));
    Assert.assertThat(1, is(equalTo(actualOrder.getVersion())));
  }

  @Test
  public void updateShouldUpdateNewlyAddedOrder() {
    final Order order = createAddOrder();

    final OrderDatabaseObj expectedOrder = orderRepository.addOrder(order);
    final OrderDatabaseObj actualOrder = orderRepository.updateOrder(expectedOrder);

    validateOrder(expectedOrder, actualOrder);
    Assert.assertThat(1, is(equalTo(actualOrder.getGroupOrderId())));
    Assert.assertThat(2, is(equalTo(actualOrder.getVersion())));
  }

  @Test
  public void getOrdersShouldReturnEveryTestOrderWhenRequested() {
    final Order order = createAddOrder();
    final OrderDatabaseObj retOrder = orderRepository.addOrder(order);

    final List<OrderDatabaseObj> orders = orderRepository.getOrders();
    final List<OrderDatabaseObj> testData =
        orders.stream().filter(x -> x.getId() == retOrder.getId()).collect(Collectors.toList());
    Assert.assertThat(1, is(equalTo(testData.size())));
  }

  private Order createAddOrder() {
    final Order order = new Order();
    order.setGroup(ORDER_GROUP);
    order.setDate(ORDER_DATE);
    order.setDescription(ORDER_DESCRIPTION);
    order.setStatus(ORDER_STATUS);
    return order;
  }

  private OrderId createOrderId(final OrderDatabaseObj order) {
    final OrderId orderId = new OrderId();
    orderId.setId(order.getId());
    return orderId;
  }

  private void validateOrder(final Order expectedOrder, final OrderDatabaseObj actualOrder) {
    Assert.assertThat(expectedOrder.getDate(), is(equalTo(actualOrder.getDate())));
    Assert.assertThat(expectedOrder.getGroup(), is(equalTo(actualOrder.getGroup())));
    Assert.assertThat(expectedOrder.getDescription(), is(equalTo(actualOrder.getDescription())));
    Assert.assertThat(expectedOrder.getStatus(), is(equalTo(actualOrder.getStatus())));
  }
}
