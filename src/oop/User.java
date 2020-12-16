package oop;

import java.util.Calendar;
import java.util.Date;

public class User {
    private String username;
    private String name;
    private Calendar birthDate;

    public User(String username, String name, Calendar birthDate) {
        this.username = username;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate.getTime() +
                '}';
    }
}
