package com.intland.repository.util.cast;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.intland.model.Order;


public interface OrderRowMapperResultExtractor {

  Optional<Order> extractOrder(Map<String, Object> rowMappedResult);

  List<Order> extractOrders(Map<String, Object> rowMappedResult);

}
