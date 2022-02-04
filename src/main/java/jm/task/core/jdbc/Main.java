package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.lang.*;

public class Main {


    public static void main(String[] args) {

        // создаем юзерсервис
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        // добавляем пользователей в таблицу
        userService.saveUser("Joseph", "Joestar", (byte) 24);
        userService.saveUser("Jotaro", "Kujo", (byte) 18);
        userService.saveUser("Josuke", "Higashikata", (byte) 18);
        userService.saveUser("Jolyne", "Cujoh", (byte) 20);

        userService.getAllUsers();
        // очистка таблицы
        userService.cleanUsersTable();
        // удаление таблицы
        userService.dropUsersTable();

        // отака фигня пацаны :o
    }
}


