package com.intland.repository.util.mapper;

/**
 * Interface to convert from one type to another.
 *
 * @param <T> - type that will be converted.
 * @param <V> - T will be converted to V.
 */
public interface Converter<T, V> {
  /**
   * Convert T type into V type.
   * 
   * @param t T typed original object.
   * @return converted object.
   */
  V convert(T t);
}
