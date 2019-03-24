package com.intland.repository.util.cast;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service("typeSafeListCaster")
public class TypeSafeListCasterImpl implements TypeSafeListCaster {

  @Override
  public <T> List<T> castList(Object obj, Class<T> clazz) {
    List<T> result = new ArrayList<>();
    if (obj instanceof List<?>) {
      for (Object o : (List<?>) obj) {
        result.add(clazz.cast(o));
      }
      return result;
    }
    return result;
  }
}
