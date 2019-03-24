package com.intland.repository.util.cast;

import java.util.List;


public interface TypeSafeListCaster {

  <T> List<T> castList(Object obj, Class<T> clazz);

}
