package org.itstep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task01 {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("vitek", "wghtd", "Виктор Верепьянко"),
                new User("max", "ogtyh", "Максим Шаповалов"),
                new User("dima", "adscw", "Дмитрий Кочетов")
        );

        users.stream().forEach((user) -> System.out.println(user.toString()));

        // Запись объекта в файл (Серіалізація)
        try (OutputStream out = new FileOutputStream("e:\\JAVA\\Projects\\GitHub Homeworks\\Lesson069\\src\\main\\webapp\\resources\\users.db");
             ObjectOutputStream objectOut = new ObjectOutputStream(out)) {
            objectOut.writeObject(users);    // запись объектов в файл
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Создание объекта из файла (Десеріалізація)
        List<User> copyUsers = null;
        try (InputStream in = new FileInputStream("e:\\JAVA\\Projects\\GitHub Homeworks\\Lesson069\\src\\main\\webapp\\resources\\users.db");
             ObjectInputStream objectInput = new ObjectInputStream(in)) {
            copyUsers = (List<User>) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        copyUsers.stream().forEach((user) -> System.out.println(user.toString()));
    }
}
