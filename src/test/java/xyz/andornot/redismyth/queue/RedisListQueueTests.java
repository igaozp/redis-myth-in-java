package xyz.andornot.redismyth.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RedisListQueueTests {
    @Resource
    private RedisListQueueService redisListQueueService;

    @Test
    @DisplayName("左进右出")
    void enqueueAndDequeueTest() {
        var value = 1;
        redisListQueueService.leftEnqueue(value);
        Assertions.assertEquals(redisListQueueService.rightDequeue(), value);
    }

    @Test
    @DisplayName("右进左出")
    void anotherEnqueueAndDequeueTest() {
        var value = 2;
        redisListQueueService.rightEnqueue(value);
        Assertions.assertEquals(redisListQueueService.leftDequeue(), value);
    }
}
