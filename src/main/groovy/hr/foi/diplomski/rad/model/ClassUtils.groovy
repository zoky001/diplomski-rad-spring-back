package hr.foi.diplomski.rad.model

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j


@Slf4j
@TypeChecked
final class ClassUtils {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtils.class)

  private ClassUtils() { }


  static <T> T invokeMethod(Method method, Object instance, Object[] args) {
    try {
      return (T) method.invoke(instance, args)
    }
    catch (IllegalArgumentException e) {
      LOGGER.error(
          "Error while invoking method " + method.getClass().getName() + "." + method.getName() + "(" +
              Arrays.asList(args) + ")", e)
    }
    catch (IllegalAccessException e) {
      LOGGER.error(
          "Error while invoking method " + method.getClass().getName() + "." + method.getName() + "(" +
              Arrays.asList(args) + ")", e)
    }
    catch (InvocationTargetException e) {
      LOGGER.error(
          "Error while invoking method " + method.getClass().getName() + "." + method.getName() + "(" +
              Arrays.asList(args) + ")", e)
    }
    return null
  }

  /**
   * Gets the method.
   *
   * @param className
   *          the class name
   * @param methodName
   *          the method name
   * @param parameterTypeNames
   *          the parameter type names
   * @return the method
   */
  static Method getMethod(String className, String methodName, String[] parameterTypeNames) {
    try {
      Class<?> c = Class.forName(className)
      final int len = parameterTypeNames.length
      Class<?>[] parameterTypes = new Class[len]
      for (int i = 0; i < len; i++) {
        parameterTypes[i] = Class.forName(parameterTypeNames[i])
      }
      return c.getMethod(methodName, parameterTypes)
    }
    catch (ClassNotFoundException e) {
      LOGGER.error("Required class" + className + " not found")
    }
    catch (NoSuchMethodException e) {
      LOGGER.error("Required method " + methodName + " with parameter types (" + Arrays.asList(parameterTypeNames) +
          ") not found on class " + className)
    }
    return null
  }

  /**
   * Class of.
   *
   * @param className
   *          the class name
   * @return the class
   */
  static Class<?> classOf(String className) {
    try {
      return Class.forName(className)
    }
    catch (ClassNotFoundException e) {
      LOGGER.warn("Required class " + className + " not found")
      return null
    }
  }

  static Method createMethod(String className, String methodName, Class<?>[] parameterTypes) {
    try {
      Class<?> c = Class.forName(className)
      return c.getMethod(methodName, parameterTypes)
    }
    catch (ClassNotFoundException e) {
      log.error("Required class " + className + " not found")
    }
    catch (NoSuchMethodException e) {
      log.error("Required method " + methodName + " not found on class " + className)
    }
    return null
  }

}
