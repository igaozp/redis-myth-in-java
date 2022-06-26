package xyz.andornot.redismyth.queue;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 基于 Redis Lists 队列示例
 *
 * @author igaozp
 * @since 2022/6/26
 */
@Service
public class RedisListQueueService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String LIST_QUEUE_KEY = "list-queue";

    public void enqueue(Object obj) {
        redisTemplate.opsForList().leftPush(LIST_QUEUE_KEY, obj);
    }

    public Object dequeue() {
        return redisTemplate.opsForList().rightPop(LIST_QUEUE_KEY);
    }
}
