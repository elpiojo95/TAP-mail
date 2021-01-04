package redis;

import oop.Message;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class RedisMailStore {
    private static Jedis jedis = null;

    public static Jedis getInstance() {
        if (jedis == null) {
            jedis = new Jedis("localhost", 6379);
        }
        return jedis;
    }

    public void send(Message msg) {
        jedis.lpush(msg.getReceiver().getUsername(), msg.toString());
    }

    public List<String> getMessages(String username) {
        return jedis.lrange(username, 0, -1);
    }
    
    public List<String> getAllMessages() {
        List<String> list = new ArrayList<>();
        for (String username :this.getAllUsers()) {
            list.addAll(this.getMessages(username));
        }
        return list;
    }

    public List<String> getAllUsers() {
        return new ArrayList<>(jedis.keys("*"));
    }
}
