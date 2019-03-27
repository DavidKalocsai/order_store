package com.intland.repository.util.cast;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.intland.model.OrderWithId;


public interface OrderRowMapperResultExtractor {

  Optional<OrderWithId> extractOrder(Map<String, Object> rowMappedResult);

  List<OrderWithId> extractOrders(Map<String, Object> rowMappedResult);

}
