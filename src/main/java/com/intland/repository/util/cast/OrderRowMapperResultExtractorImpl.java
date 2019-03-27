package com.intland.repository.util.cast;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intland.model.OrderWithId;
import com.intland.repository.util.names.ProcedureNames;

@Service("orderRowMapperResultExtractor")
public class OrderRowMapperResultExtractorImpl implements OrderRowMapperResultExtractor {

  @Autowired
  private TypeSafeListCaster typeSafeListCaster;

  @Override
  public Optional<OrderWithId> extractOrder(Map<String, Object> rowMappedResult) {
    final List<OrderWithId> orders = extractOrders(rowMappedResult);
    return orders.isEmpty() ? Optional.ofNullable(null) : Optional.of(orders.get(0));
  }


  @Override
  public List<OrderWithId> extractOrders(final Map<String, Object> rowMappedResult) {
    return typeSafeListCaster.castList(rowMappedResult.get(ProcedureNames.getResultSetKey()),
        OrderWithId.class);
  }


}
