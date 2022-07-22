package ReflectionAndAnnotation.Lab._1_Reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class reflection = Reflection.class;

        System.out.println(reflection);
        System.out.println(reflection.getSuperclass());
        Class[] interfaces = reflection.getInterfaces();

        Arrays.stream(interfaces).forEach(System.out::println);

        System.out.println(reflection.getDeclaredConstructor().newInstance());
    }
}
