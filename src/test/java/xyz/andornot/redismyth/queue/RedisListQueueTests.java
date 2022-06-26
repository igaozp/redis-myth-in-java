package xyz.andornot.redismyth.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RedisListQueueTests {
    @Resource
    private RedisListQueueService redisListQueueService;

    @Test
    void enqueueAndDequeue() {
        var value = 1;
        redisListQueueService.enqueue(value);
        Assertions.assertEquals(redisListQueueService.dequeue(), value);
    }
}
