package redis;

import oop.Message;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * Back-End storage based in redis server implementation
 */
public class RedisMailStore {
    private static Jedis jedis = null;

    /**
     * getter of RedisMailStore instance
     */
    public RedisMailStore() {
        getInstance();
    }

    /**
     * singleton of RedisMailStore instance
     */
    public static Jedis getInstance() {
        if (jedis == null) {
            jedis = new Jedis("localhost", 6379);
        }
        return jedis;
    }

    /**
     * Send a mail to the storage
     * @param msg message to send
     */
    public void send(Message msg) {
        jedis.lpush(msg.getReceiver().getUsername(), msg.toString());
    }

    /**
     * Get the messages of certain receiver of the storage
     * @param username username of the receiver
     * @return list of messages
     */
    public List<String> getMessages(String username) {
        return jedis.lrange(username, 0, -1);
    }

    /**
     * Get all the messages of the system of the storage
     * @return list of messages
     */
    public List<String> getAllMessages() {
        List<String> list = new ArrayList<>();
        for (String username :this.getAllUsers()) {
            list.addAll(this.getMessages(username));
        }
        return list;
    }

    /**
     * get all the users of the system of the storage
     * @return list of users
     */
    public List<String> getAllUsers() {
        return new ArrayList<>(jedis.keys("*"));
    }
}
