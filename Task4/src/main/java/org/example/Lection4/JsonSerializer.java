package org.example.Lection4;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonSerializer {

    public static String serialize(Object obj) throws Exception {
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(JsonField.class)) {
                JsonField annotation = field.getAnnotation(JsonField.class);
                String jsonName = annotation.name();
                Object value = field.get(obj);
                jsonMap.put(jsonName, value);
            }
        }
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            sb.append('"')
                    .append(entry.getKey())
                    .append("\": \"")
                    .append(entry.getValue())
                    .append("\", ");
        }
        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setFirstName("Имя");
        person.setLastName("Фамилия");
        System.out.println(JsonSerializer.serialize(person));
    }
}

// Пример класса для тестирования
class Person {
    @JsonField(name = "first_name")
    private String firstName;

    @JsonField(name = "last_name")
    private String lastName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}