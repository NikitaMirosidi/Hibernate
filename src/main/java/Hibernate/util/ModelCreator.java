package Hibernate.util;

import Hibernate.model.BaseModel;
import Hibernate.repository.BaseRepository;
import Hibernate.repository.RepositoryCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class ModelCreator {

    //private final static ObjectMapper MAPPER = new ObjectMapper();

    @SneakyThrows
    public static <T extends BaseModel> T create(Class<T> modelClass, Scanner scanner, String purpose, boolean isSimple) {

        System.out.println("Создание объекта типа " + modelClass.getSimpleName() + ":");
        Map<String, Object> objectMap = new HashMap<>();
        List<Field> fields = Arrays.stream(modelClass.getDeclaredFields())
                .filter(a -> !Modifier.isStatic(a.getModifiers()))
                .collect(Collectors.toList());
        List<Field> simpleFields = new ArrayList<>();

        if(purpose.equals("save")) {
            simpleFields = fields.stream()
                    .filter(a -> a.isAnnotationPresent(Column.class))
                    .filter(a -> !a.isAnnotationPresent(Id.class))
                    .collect(Collectors.toList());
        }

        if(purpose.equals("update")) {
            simpleFields = fields.stream()
                    .filter(a -> a.isAnnotationPresent(Column.class))
                    .collect(Collectors.toList());
        }

        List<Field> relationFields = fields.stream()
                .filter(a -> !a.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        if(isSimple) {
            System.out.println("1 - выбрать объект из базы\n" +
                    "2 - создать новый объект");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    return getFromBase(modelClass, scanner);
                case "2":
                    objectMap.putAll(simpleFields(simpleFields, scanner));
                    break;
                default:
                    System.out.println("Поддерживаемая функция не выбрана\n" +
                            "Попробуйте еще раз\n");
                    break;
            }
        }
        else {
            objectMap.putAll(simpleFields(simpleFields, scanner));
            objectMap.putAll(relationFields(relationFields, scanner));
        }

        T model = modelClass.getDeclaredConstructor().newInstance();
        if(purpose.equals("save")) {
            List<Field> fieldsNoId = fields.stream()
                    .filter(f -> !f.isAnnotationPresent(Id.class))
                    .collect(Collectors.toList());
            for (Field field : fieldsNoId) {
                field.setAccessible(true);
                field.set(model, objectMap.get(field.getName()));
            }
        }
        if(purpose.equals("update")) {
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(model, objectMap.get(field.getName()));
            }
        }
        return model;
        //return MAPPER.convertValue(objectMap, modelClass);
    }

    @SneakyThrows
    private static <T extends BaseModel> T getFromBase(Class<T> modelClass, Scanner scanner) {

        BaseRepository<T> of = RepositoryCreator.of(modelClass);
        System.out.println("Объекты имеющиеся в базе:\n" + of.getAll());
        int id = getInt("ID",scanner);

        return of.getByIdUnproxy(id);
    }

    private static Map<String, Object> simpleFields(List<Field> fields, Scanner scanner) {

        Map<String, Object> objectMap = new HashMap<>();
        Map<String, String> nameToType = fields.stream()
                .collect(Collectors.toMap(Field::getName, a -> a.getType().getSimpleName()));

        for (String fieldName : nameToType.keySet()) {
            if(nameToType.get(fieldName).equals("int")) {
                objectMap.put(fieldName, ModelCreator.getInt(fieldName, scanner));
            }

            if(nameToType.get(fieldName).equals("String")) {
                objectMap.put(fieldName, ModelCreator.getString(fieldName, scanner));
            }
        }

        return objectMap;
    }

    private static Map<String, Object> relationFields(List<Field> fields, Scanner scanner) {

        Map<String, Object> objectMap = new HashMap<>();
        Reflections reflections = new Reflections("Hibernate.model");
        Map<String, ? extends Class<?>> models = reflections.getTypesAnnotatedWith(Entity.class).stream()
                .collect(Collectors.toMap(a -> a.getSimpleName().toLowerCase(), a -> a));
        Map<String, String> nameToType = fields.stream()
                .collect(Collectors.toMap(Field::getName, a -> a.getGenericType().getTypeName()));

        for(String fieldName : nameToType.keySet()) {

            boolean a = true;

            while (a) {

                System.out.println("Хотите заполнить поле " + fieldName + "?\n" +
                        "1 - да\n" +
                        "0 - нет");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        String typeName = nameToType.get(fieldName);
                        objectMap.putAll(createNestedModel(fieldName, typeName, models, scanner));
                        a = false;
                        break;
                    case "0":
                        a = false;
                        break;
                    default:
                        System.out.println("Поддерживаемая функция не выбрана\n" +
                                "Попробуйте еще раз\n");
                        break;
                }
            }
        }

        return objectMap;
    }

    private static <T extends BaseModel> Map<String, Object> createNestedModel(String fieldName, String typeName, Map<String, ? extends Class<?>> models, Scanner scanner) {

        Map<String, Object> objectMap = new HashMap<>();

        if(typeName.toLowerCase().startsWith("java.util.set")) {

            String clearTypeName = typeName.replace("java.util.Set<Hibernate.model.", "").replace(">", "");
            Class<?> aClass = models.get(clearTypeName.toLowerCase());
            Set<T> nestedModels = new HashSet<>();
            nestedModels.add(ModelCreator.create((Class<T>) aClass, scanner, "save", true));

            boolean a = true;

            while (a) {

                System.out.println("Хотите добавить еще один объект типа " +  clearTypeName + "?\n" +
                        "1 - да\n" +
                        "0 - нет");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        nestedModels.add(ModelCreator.create((Class<T>) aClass, scanner, "save", true));
                        break;
                    case "0":
                        a = false;
                        break;
                    default:
                        System.out.println("Поддерживаемая функция не выбрана\n" +
                                "Попробуйте еще раз\n");
                        break;
                }
            }

            objectMap.put(fieldName, nestedModels);
        }
        else {
            String clearTypeName = typeName.replace("Hibernate.model.", "");
            Class<?> aClass = models.get(clearTypeName.toLowerCase());
            objectMap.put(fieldName, ModelCreator.create((Class<T>) aClass, scanner, "save", true));
        }

        return objectMap;
    }

    public static int getInt(String fieldName, Scanner scanner) {

        int value;

        while (true) {
            System.out.print("Укажите значение для '" + fieldName + "': ");

            try {
                value = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Значение для '" + fieldName + "' указано неверно.\n");
            }
        }

        return value;
    }

    public static String getString(String fieldName, Scanner scanner) {

        String value;

        if(fieldName.toLowerCase().contains("date")) {
            return getCorrectDate(scanner);
        }

        System.out.print("Укажите значение для '" + fieldName + "': ");
        value = scanner.nextLine();

        return value;
    }

    private static String getCorrectDate(Scanner scanner) {

        boolean a = true;
        String[] date;
        int errorCounter;
        String correctDate = "";

        while(a) {
            System.out.print("Укажите дату в формате год-месяц-день цифрами: ");
            date = scanner.nextLine().split("-");
            errorCounter = 0;

            if(date.length != 3) {
                System.out.println("Дата указана неверно!\nНе соблюден формат.");
                continue;
            }

            errorCounter += verifyDatePart("Год", date[0], 1, 9999);
            errorCounter += verifyDatePart("Месяц", date[1], 1, 12);
            errorCounter += verifyDatePart("День", date[2], 1, 31);

            if (errorCounter == 0) {
                correctDate = String.join("-", date);
                a = false;
            }
        }

        return correctDate;
    }

    private static int verifyDatePart(String dateName, String datePart, int min, int max) {

        try {
            int date = Integer.parseInt(datePart);

            if(date < min || date > max) {
                System.out.println(dateName + " указан неверно!\nЗначение выходит за допустимые пределы (" + min + " <= " + dateName + " <= " + max + ").");
                return 1;
            }
            else {
                return 0;
            }
        }
        catch (NumberFormatException e) {
            System.out.println(dateName + " указан неверно!\nИспользуйте цифры чтобы указать " + dateName + ".");
            return 1;
        }
    }
}