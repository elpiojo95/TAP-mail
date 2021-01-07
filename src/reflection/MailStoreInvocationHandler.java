package reflection;

import oop.MailStore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MailStoreInvocationHandler implements InvocationHandler {
    private MailStore mailStore;

    public MailStoreInvocationHandler(MailStore mailStore) {
        this.mailStore = mailStore;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean log = ReflectingMailSystem.class.getAnnotation(Config.class).log();
        if (log) {
            System.out.println(method.getName());
        }
        return method.invoke(mailStore, args);
    }
}
