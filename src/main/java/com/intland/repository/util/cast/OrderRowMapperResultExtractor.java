package com.intland.repository.util.cast;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.intland.model.OrderDbObj;


public interface OrderRowMapperResultExtractor {

  Optional<OrderDbObj> extractOrder(Map<String, Object> rowMappedResult);

  List<OrderDbObj> extractOrders(Map<String, Object> rowMappedResult);

}
