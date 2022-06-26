package xyz.andornot.redismyth.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest
public class RedisPubSubQueueTests {
    @Resource
    private RedisMessagePublisher redisMessagePublisher;

    @Test
    void test() throws InterruptedException {
        var message = "Message " + UUID.randomUUID();
        redisMessagePublisher.publish(message);
        Thread.sleep(1000);
        Assertions.assertTrue(RedisMessageSubscriber.messageList.get(0).contains(message));
    }
}
