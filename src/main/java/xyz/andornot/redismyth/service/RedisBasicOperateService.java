package xyz.andornot.redismyth.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Redis 简单操作
 *
 * @author igaozp
 * @since 2022/5/16
 */
@Service
public class RedisBasicOperateService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void sampleSetValue() {
        redisTemplate.opsForValue().set("sample-key", 1);
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong("sample-long", redisTemplate.getRequiredConnectionFactory());
        redisAtomicLong.addAndGet(1);
    }
}
