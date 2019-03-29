package com.intland.repository.util.cast;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intland.model.OrderDatabaseObj;
import com.intland.repository.util.names.ProcedureNames;

/**
 * Convert the output of the rowmapper to OrderDbObj.
 */
@Service("orderRowMapperResultExtractor")
public class OrderRowMapperResultExtractorImpl implements OrderRowMapperResultExtractor {

  @Autowired
  private TypeSafeListCaster typeSafeListCaster;

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<OrderDatabaseObj> extractOrder(Map<String, Object> rowMappedResult) {
    final List<OrderDatabaseObj> orders = extractOrders(rowMappedResult);
    return orders.isEmpty() ? Optional.ofNullable(null) : Optional.of(orders.get(0));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<OrderDatabaseObj> extractOrders(final Map<String, Object> rowMappedResult) {
    return typeSafeListCaster.castList(rowMappedResult.get(ProcedureNames.getResultSetKey()),
        OrderDatabaseObj.class);
  }


}
