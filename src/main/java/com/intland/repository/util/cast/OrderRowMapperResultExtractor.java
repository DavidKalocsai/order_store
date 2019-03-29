package com.intland.repository.util.cast;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.intland.model.OrderDatabaseObj;

/**
 * Convert the output of the rowmapper to OrderDbObj.
 */
public interface OrderRowMapperResultExtractor {

  /**
   * Convert object to {@link OrderDatabaseObj} optional.
   *
   * @param rowMappedResult output of rowmmapper.
   * @return {@link OrderDatabaseObj} optional,
   */
  Optional<OrderDatabaseObj> extractOrder(Map<String, Object> rowMappedResult);

  /**
   * Convert object to {@link OrderDatabaseObj} optional of list.
   *
   * @param rowMappedResult output of rowmmapper.
   * @return {@link OrderDatabaseObj} optional of list,
   */
  List<OrderDatabaseObj> extractOrders(Map<String, Object> rowMappedResult);

}
