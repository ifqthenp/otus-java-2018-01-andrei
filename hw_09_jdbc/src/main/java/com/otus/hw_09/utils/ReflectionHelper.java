package com.otus.hw_09.utils;

import com.otus.hw_09.dataset.DataSet;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public final class ReflectionHelper
{
    private ReflectionHelper() {}

    public static <T extends DataSet> Map<String, Object> getFieldNames(final T o)
    {
        Class<?> cl = o.getClass();
        Map<String, Object> map = new HashMap<>();

        do {
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (final Field f : fields) {

                boolean isNotStatic = !Modifier.isStatic(f.getModifiers());
                boolean isNotTransient = !Modifier.isTransient(f.getModifiers());
                if (isNotStatic && isNotTransient) {

                    try {
                        map.put(f.getName(), f.get(o));
                    }
                    catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }

            cl = cl.getSuperclass();

        } while (cl != null);

        return map;
    }
}
