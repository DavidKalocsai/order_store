package com.intland.repository.util.cast;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.intland.model.OrderDbObj;

/**
 * Convert the output of the rowmapper to OrderDbObj.
 */
public interface OrderRowMapperResultExtractor {

  /**
   * Convert object to {@link OrderDbObj} optional.
   *
   * @param rowMappedResult output of rowmmapper.
   * @return {@link OrderDbObj} optional,
   */
  Optional<OrderDbObj> extractOrder(Map<String, Object> rowMappedResult);

  /**
   * Convert object to {@link OrderDbObj} optional of list.
   *
   * @param rowMappedResult output of rowmmapper.
   * @return {@link OrderDbObj} optional of list,
   */
  List<OrderDbObj> extractOrders(Map<String, Object> rowMappedResult);

}
