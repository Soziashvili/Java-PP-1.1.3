package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ben", "Affleck", (byte) 44);
        userService.saveUser("Christian", " Bale", (byte) 31);
        userService.saveUser("Michael", "Keaton", (byte) 39);
        userService.saveUser("Robert", "Pattinson", (byte) 37);
        userService.getAllUsers().forEach(user -> System.out.println(user.toString()));
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
