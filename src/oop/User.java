package oop;

import java.util.Date;

public class User {
    private String username;
    private String name;
    private Date birthDate;

    public User(String username, String name, Date birthDate) {
        this.username = username;
        this.name = name;
        this.birthDate = birthDate;
    }
}
