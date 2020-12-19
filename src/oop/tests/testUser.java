package oop.tests;

import oop.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

public class testUser {
    private User user;
    private Calendar leoBirth;

    @Before
    public void Before() {
        leoBirth= Calendar.getInstance();
        leoBirth.set(1995, Calendar.SEPTEMBER,7);
        user =  new User("elpiojo", "Leo", leoBirth);
    }

    @Test
    public void getUsername(){
        Assert.assertEquals(user.getUsername(),"elpiojo");
    }

    @Test
    public void getName(){
        Assert.assertEquals(user.getName(),"Leo");
    }

    @Test
    public void getBirthDate(){
        Assert.assertSame(user.getBirthDate(), leoBirth);
    }

}
