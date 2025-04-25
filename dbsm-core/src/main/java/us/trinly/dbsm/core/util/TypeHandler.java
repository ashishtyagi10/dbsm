package us.trinly.dbsm.core.util;

import java.lang.reflect.Field;

public interface TypeHandler {
    String getSqlType(Field field);
    Object convertToDatabase(Field field, Object value);
    Object convertFromDatabase(Field field, Object value);
}
