package laba1;
import java.lang.reflect.*;
public class Zavd1_1 {
    public static String getTypeDescription(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return getTypeDescription(clazz);
        } catch (ClassNotFoundException e) {
            return "Class not found: " + typeName;
        }
    }
    public static String getTypeDescription(Class<?> clazz) {
        StringBuilder description = new StringBuilder();
        description.append("Package: ").append(clazz.getPackage()).append("\n");
        int modifiers = clazz.getModifiers();
        description.append("Modifiers: ").append(Modifier.toString(modifiers)).append("\n");
        description.append("Class: ").append(clazz.getSimpleName()).append("\n");
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            description.append("Superclass: ").append(superclass.getSimpleName()).append("\n");
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            description.append("Implemented interfaces: ");
            for (Class<?> anInterface : interfaces) {
                description.append(anInterface.getSimpleName()).append(", ");
            }
            description.delete(description.length() - 2, description.length());
            description.append("\n");
        }
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length > 0) {
            description.append("Fields:\n");
            for (Field field : fields) {
                description.append("\t").append(Modifier.toString(field.getModifiers()))
                        .append(" ").append(field.getType().getSimpleName())
                        .append(" ").append(field.getName()).append("\n");
            }
        }
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length > 0) {
            description.append("Constructors:\n");
            for (Constructor<?> constructor : constructors) {
                description.append("\t").append(Modifier.toString(constructor.getModifiers()))
                        .append(" ").append(constructor.getName()).append("(");
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    description.append(parameterTypes[i].getSimpleName());
                    if (i < parameterTypes.length - 1) {
                        description.append(", ");
                    }
                }
                description.append(")\n");
            }
        }
        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length > 0) {
            description.append("Methods:\n");
            for (Method method : methods) {
                description.append("\t").append(Modifier.toString(method.getModifiers()))
                        .append(" ").append(method.getReturnType().getSimpleName())
                        .append(" ").append(method.getName()).append("(");
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    description.append(parameterTypes[i].getSimpleName());
                    if (i < parameterTypes.length - 1) {
                        description.append(", ");
                    }
                }
                description.append(")\n");
            }
        }
        return description.toString();
    }
    public static void main(String[] args) {
        System.out.println(getTypeDescription("java.util.ArrayList"));
        System.out.println(getTypeDescription("int"));
        System.out.println(getTypeDescription("java.lang.String"));
    }
}