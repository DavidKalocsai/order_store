package com.intland.repository.util.cast;

import java.util.List;

/**
 * Interface to cast object to T typed list.
 */
public interface TypeSafeListCaster {

  /**
   * Convert Object to T typed list.
   *
   * @param obj to convert.
   * @param clazz T type
   * @return list
   */
  <T> List<T> castList(Object obj, Class<T> clazz);

}
