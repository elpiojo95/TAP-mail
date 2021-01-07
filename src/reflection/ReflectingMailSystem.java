package reflection;

import oop.FileMailStore;
import oop.MailStore;
import oop.MemoryMailStore;
import patterns.EnhancedMailSystem;
import patterns.MailStoreCipherDecorator;
import patterns.MailStoreReverseDecorator;
import redis.MailStoreToRedisAdapter;
import redis.RedisMailStore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

@Config(store = "redis.RedisMailStore", log = true)
public class ReflectingMailSystem extends EnhancedMailSystem {

    public ReflectingMailSystem() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        super();
        super.mStore = (MailStore) Proxy.newProxyInstance(MailStoreInvocationHandler.class.getClassLoader(),
                new Class[]{MailStore.class},
                new MailStoreInvocationHandler(getMailStore())
        );

        super.userList = mStore.getAllUsers();
    }

    private MailStore getMailStore() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        MailStore mailstore;
        Config config = ReflectingMailSystem.class.getAnnotation(Config.class);
        Class<?> clazz = Class.forName(config.store());
        Object o = clazz.getConstructor().newInstance();

        if (o.getClass() == RedisMailStore.class) {
            mailstore = new MailStoreToRedisAdapter((RedisMailStore) o);
        } else if (o.getClass() == FileMailStore.class) {
            mailstore = new MailStoreReverseDecorator(new MailStoreCipherDecorator((FileMailStore) o));
        } else {
            mailstore = (MemoryMailStore) o;
        }
        return mailstore;
    }
}
