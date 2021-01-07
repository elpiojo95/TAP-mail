package oop;

import java.util.Calendar;

/**
 * Represents the user structure
 */
public class User implements Comparable<User> {
    private String username;
    private String name;
    private Calendar birthDate;

    /**
     * Class constructor
     * @param username username of user
     * @param name name of user
     * @param birthDate birthdate of user
     */
    public User(String username, String name, Calendar birthDate) {
        this.username = username;
        this.name = name;
        this.birthDate = birthDate;
    }

    /**
     * getter of username
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter of name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * getter of birthdate
     * @return Calendar birthDate
     */
    public Calendar getBirthDate() {
        return birthDate;
    }

    /**
     * User to string
     * @return String user
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate.getTime().toString() +
                '}';
    }

    /**
     * interface Comparator
     * @param otherUser user to compare with
     * @return int (<0) - otherUser before user ,(==0) - equals , (>0) - user before otherUser
     */
    @Override
    public int compareTo(User otherUser) {
        return this.username.compareTo(otherUser.getUsername());
    }
}
