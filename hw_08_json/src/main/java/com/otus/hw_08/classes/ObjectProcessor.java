package com.otus.hw_08.classes;

import com.otus.hw_08.interfaces.JsonSerializer;

import javax.json.*;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.Collection;

public class ObjectProcessor implements JsonSerializer
{
    private static JsonBuilderFactory factory = Json.createBuilderFactory(null);

    @Override
    public String getJsonFrom(final Object o)
    {
        if (o == null) {
            return "null";
        }

        Class<?> cl = o.getClass();

        if (isNumberOrBoolean(cl)) {
            return o.toString();
        }

        if (isCharacterOrString(cl)) {
            return String.format("\"%s\"", o);
        }

        if (isCollection(cl)) {
            Collection<?> coll = (Collection<?>) o;
            Object[] arr = coll.toArray();
            return getJsonFrom(arr);
        }

        if (cl.isArray()) {

            JsonArrayBuilder jab = factory.createArrayBuilder();

            for (int i = 0; i < Array.getLength(o); i++) {
                Object val = Array.get(o, i);
                if (val != null) {

                    Class<?> valClass = val.getClass();

                    if (valClass.equals(Integer.class)) jab.add((Integer) val);
                    else if (valClass.equals(Double.class)) jab.add((Double) val);
                    else if (valClass.equals(Short.class)) jab.add((Short) val);
                    else if (valClass.equals(Byte.class)) jab.add((Byte) val);
                    else if (valClass.equals(Float.class)) jab.add((Float) val);
                    else if (valClass.equals(Long.class)) jab.add((Long) val);
                    else if (valClass.equals(Character.class)) jab.add(String.valueOf(val));
                    else if (valClass.equals(String.class)) jab.add(String.valueOf(val));
                    else if (valClass.equals(Boolean.class)) jab.add((Boolean) val);
                    else jab.add(getJsonFrom(val));
                } else {
                    jab.addNull();
                }
            }

            return replaceCharacters(jab.build());

        } else {

            JsonObjectBuilder job = factory.createObjectBuilder();

            do {
                Field[] fields = cl.getDeclaredFields();
                AccessibleObject.setAccessible(fields, true);
                for (Field f : fields) {
                    boolean isNotStatic = !Modifier.isStatic(f.getModifiers());
                    boolean isNotTransient = !Modifier.isTransient(f.getModifiers());
                    if (isNotStatic && isNotTransient) {
                        try {
                            Class<?> t = f.getType();
                            Object val = f.get(o);

                            if (val != null) {
                                if (t.equals(int.class)) job.add(f.getName(), (int) val);
                                else if (t.equals(double.class)) job.add(f.getName(), (double) val);
                                else if (t.equals(short.class)) job.add(f.getName(), (short) val);
                                else if (t.equals(byte.class)) job.add(f.getName(), (byte) val);
                                else if (t.equals(float.class)) job.add(f.getName(), (float) val);
                                else if (t.equals(long.class)) job.add(f.getName(), (long) val);
                                else if (t.equals(char.class)) job.add(f.getName(), String.valueOf(val));
                                else if (t.equals(String.class)) job.add(f.getName(), String.valueOf(val));
                                else if (t.equals(boolean.class)) job.add(f.getName(), (boolean) val);
                                else job.add(f.getName(), getJsonFrom(val));
                            }
                        }
                        catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

                cl = cl.getSuperclass();

            } while (cl != null);

            return replaceCharacters(job.build());
        }
    }

    private String replaceCharacters(final JsonValue value)
    {
        return value.toString()
            .replace("\"{", "{")
            .replace("}\"", "}")
            .replace("\\\"", "\"")
            .replace("\"[", "[")
            .replace("]\"", "]");
    }

    private boolean isCollection(final Class<?> c)
    {
        return Collection.class.isAssignableFrom(c);
    }

    private boolean isNumberOrBoolean(Class<?> c)
    {
        return Number.class.isAssignableFrom(c) || Boolean.class.isAssignableFrom(c);
    }

    private boolean isCharacterOrString(Class<?> c)
    {
        return Character.class.isAssignableFrom(c) || String.class.isAssignableFrom(c);
    }
}
