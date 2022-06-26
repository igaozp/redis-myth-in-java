package xyz.andornot.redismyth.cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RedisCacheExampleTests {
    @Resource
    private RedisCacheExampleService redisCacheExampleService;

    @Test
    void cacheAsideTest() {
        Assertions.assertEquals(redisCacheExampleService.cacheAsideQuery(), 0L, "初次查询，Redis 缓存初始化");

        redisCacheExampleService.cacheAsideUpdate();
        Assertions.assertEquals(redisCacheExampleService.cacheAsideQuery(), 1L, "修改模拟数据库数据后，删除 Redis 缓存并重新加载");
    }

    @AfterEach
    void clean() {
        redisCacheExampleService.clean();
    }
}
