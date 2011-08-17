package net.nextep.service.util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;

/**
 * Build and execute a method using Bulk (which means by grouping element)
 * 
 * @author Vincent Demeester <vincent@demeester.fr>
 * 
 */
public class BulkBuilder<T extends Serializable> {

  private Integer limit;
  private String name;

  /**
   * Constructor the take the limit number and a name.
   */
  public BulkBuilder(String name, Integer limit) {
    this.name = name;
    this.limit = limit;
  }

  /**
   * Run the given method of the given instance for the given list in bulk.
   * 
   * The given method should take a Collection<T> as parameter.
   * 
   * @param list
   * @param instance
   * @param method
   */
  public Integer runWithBulk(List<T> list, Object instance, Method method,) 
      throws IllegalAccessException, IllegalArgumentException,
      InvocationTargetException {
    Integer bulkCount = 0;
    if (method != null && instance != null) {
      List<T> currentBulk = new ArrayList<T>();
      Iterator<T> it = list.iterator();
      while (it.hasNext()) {
        if (currentBulk.size() >= limit) {
          bulkCount++;
          // Let's call the method !
          runMethod(currentBulk, instance, method);
          // Cleaning the bulk
          currentBulk.clear();
        }
        currentBulk.add(it.next());
      }
      // Last run !
      if (!currentBulk.isEmpty()) {
        bulkCount++;
        // Let's call the method !
        runMethod(currentBulk, instance, method);
        // Cleaning the bulk
        currentBulk.clear();
      }
    } else {
      throw new IllegalArgumentException(
          "The method or instance shouldn't be null.");
    }
    return bulkCount;
  }

  /**
   * Run the given method for the given instance with the given list of T 
   * object.
   *
   * The instance method have to take a Collection of T object to work. This is
   * a current limitation that might not be in future version of the library.
   * 
   * @param currentBulk
   * @param instance
   * @param method
   */
  private void runMethod(List<T> currentBulk, Object instance, Method method)
      throws IllegalAccessException, InvocationTargetException {
    method.invoke(instance, currentBulk);
  }

  /**
   * Get the limit of a bulk.
   *
   * @return limit
   */
  public Integer getLimit() {
    return limit;
  }

  /**
   * Set the limit of a bulk
   *
   * @param limit
   */
  public void setLimit(Integer limit) {
    this.limit = limit;
  }

}
