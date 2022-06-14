package xyz.andornot.redismyth.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 模拟实践 Redis 更新缓存的策略
 * <p>
 * 1. 更新数据库+删除缓存值
 * 2. 更新数据库+更新缓存
 * 3. 更新缓存+更新数据库
 * 4. 删除缓存值+更新数据库
 *
 * @author igaozp
 * @since 2022/6/14
 */
@Slf4j
@Service
public class RedisCacheExampleService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 模拟数据库不断变化的数据信息
     */
    private static final AtomicLong DB = new AtomicLong(0);

    private static final String CACHE_KEY = "sample-cache";


    /**
     * 更新数据库+删除缓存值
     * <p>
     * 查询时先查询缓存，缓存不存在则查询数据库并将数据库的值放入缓存中
     */
    public long cacheAsideQuery() {
        var cache = redisTemplate.opsForValue().get(CACHE_KEY);
        if (cache != null) {
            return (long) cache;
        }

        redisTemplate.opsForValue().set(CACHE_KEY, getDb());
        return cacheAsideQuery();
    }

    /**
     * 更新数据库+删除缓存值
     * <p>
     * 更新数据库后，删除存有旧数据的缓存
     */
    public void cacheAsideUpdate() {
        updateDb();
        redisTemplate.delete(CACHE_KEY);
    }

    /**
     * 清理缓存数据
     */
    public void clean() {
        redisTemplate.delete(CACHE_KEY);
    }

    /**
     * 模拟更新数据库
     */
    private void updateDb() {
        DB.incrementAndGet();
    }

    /**
     * 模拟获取数据库
     */
    private long getDb() {
        return DB.get();
    }
}
