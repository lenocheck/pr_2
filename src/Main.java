import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Object> objects = new ArrayList<>();
        objects.add(checkAndCreateInstance(MyService.class));
        objects.add(checkAndCreateInstance(Main.class));

        System.out.println("Созданные объекты: " + objects);
    }

    private static Object checkAndCreateInstance(Class<?> clazz) throws Exception {
        Init annotation = clazz.getAnnotation(Init.class);

        if (annotation == null) {
            return null;
        }

        System.out.println("Аннотация @Init: " + annotation.value());

        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object instance = constructor.newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            SetValue setValue = field.getAnnotation(SetValue.class);
            if (setValue != null) {
                field.setAccessible(true);

                String val = setValue.value();

                if (field.getType() == int.class) {
                    field.set(instance, Integer.parseInt(val));
                } else {
                    field.set(instance, val);
                }
            }
        }

        return instance;
    }
}