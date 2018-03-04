package com.otus.hw_05.framework;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.otus.hw_05.annotations.After;
import com.otus.hw_05.annotations.Before;
import com.otus.hw_05.annotations.Test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * {@code FrameworkDemo} class.
 */
public class FrameworkDemo
{
    public static void main(String[] args) throws IOException
    {
        String packageName = "com.otus.hw_05.tests";

        ImmutableSet<ClassPath.ClassInfo> testClasses = getClassesFromPackage(packageName);

        processTestClasses(testClasses);
    }

    /**
     * Iterates through a set of classes and passes an instance of each class
     * as an argument to {@code declaredMethodsProcessor()} method.
     *
     * @param classes set of classes names
     */
    private static void processTestClasses(final ImmutableSet<ClassPath.ClassInfo> classes)
    {
        for (final ClassPath.ClassInfo classInfo : classes) {
            try {
                declaredMethodsProcessor(Class.forName(classInfo.getName()));
            }
            catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Processes declared methods for an object and invokes methods
     * that annotated accordingly to testing framework rules.
     * <p>
     * This solution allows only single instance of {@code Before} and
     * {@code After} annotation type. Only the first class of each type
     * found in the stream of methods will be used in the framework.
     *
     * @param cl a class to process its declared methods
     */
    private static void declaredMethodsProcessor(Class<?> cl)
    {
        Method[] methods = cl.getDeclaredMethods();

        Optional<Method> before = getAnnotatedMethodFrom(methods, Before.class);
        Optional<Method> after = getAnnotatedMethodFrom(methods, After.class);

        for (final Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {

                try {

                    Object o = cl.newInstance();

                    if (before.isPresent()) {
                        String beforeMethodName = before.get().getName();
                        cl.getMethod(beforeMethodName).invoke(o);
                    }

                    String testMethodName = m.getName();
                    cl.getMethod(testMethodName).invoke(o);

                    if (after.isPresent()) {
                        String afterMethodName = after.get().getName();
                        cl.getMethod(afterMethodName).invoke(o);
                    }
                }
                catch (IllegalAccessException | InvocationTargetException |
                    NoSuchMethodException | InstantiationException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Gets the first method annotated with the given annotation type
     * in the stream of methods.
     *
     * @param methods    an array of methods
     * @param annotation an annotation type
     * @return optional method if annotated with given annotation type
     */
    private static Optional<Method> getAnnotatedMethodFrom(final Method[] methods,
                                                           final Class<? extends Annotation> annotation)
    {
        try (Stream<Method> methodStream = Stream.of(methods)) {
            return methodStream
                .filter(method -> method.isAnnotationPresent(annotation))
                .findFirst();
        }
    }


    /**
     * Utilises Google's Guava library to find classes in given package.
     *
     * @param packageName the package to find classes in
     * @return set of classes in the given package
     * @throws IOException if the attempt to read class path resources (jar files or directories) failed.
     */
    private static ImmutableSet<ClassPath.ClassInfo> getClassesFromPackage(final String packageName) throws IOException
    {
        return ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses(packageName);
    }
}
