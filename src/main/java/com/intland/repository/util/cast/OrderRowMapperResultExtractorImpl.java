package com.intland.repository.util.cast;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intland.model.OrderDbObj;
import com.intland.repository.util.names.ProcedureNames;

@Service("orderRowMapperResultExtractor")
public class OrderRowMapperResultExtractorImpl implements OrderRowMapperResultExtractor {

  @Autowired
  private TypeSafeListCaster typeSafeListCaster;

  @Override
  public Optional<OrderDbObj> extractOrder(Map<String, Object> rowMappedResult) {
    final List<OrderDbObj> orders = extractOrders(rowMappedResult);
    return orders.isEmpty() ? Optional.ofNullable(null) : Optional.of(orders.get(0));
  }


  @Override
  public List<OrderDbObj> extractOrders(final Map<String, Object> rowMappedResult) {
    return typeSafeListCaster.castList(rowMappedResult.get(ProcedureNames.getResultSetKey()),
        OrderDbObj.class);
  }


}
