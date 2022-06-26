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

    public void leftEnqueue(Object obj) {
        redisTemplate.opsForList().leftPush(LIST_QUEUE_KEY, obj);
    }

    public Object rightDequeue() {
        return redisTemplate.opsForList().rightPop(LIST_QUEUE_KEY);
    }

    public void rightEnqueue(Object obj) {
        redisTemplate.opsForList().rightPush(LIST_QUEUE_KEY, obj);
    }

    public Object leftDequeue() {
        return redisTemplate.opsForList().leftPop(LIST_QUEUE_KEY);
    }
}
