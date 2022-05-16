package xyz.andornot.redismyth.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import xyz.andornot.redismyth.RedisMythApplication;
import xyz.andornot.redismyth.service.RedisBasicOperateService;

import java.util.concurrent.TimeUnit;

/**
 * Redis 基础操作性能测试
 *
 * @author igaozp
 * @since 2022/5/16
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class RedisBasicOperateBenchmark {
    private ConfigurableApplicationContext context;
    private RedisBasicOperateService redisBasicOperateService;

    public static void main(String[] args) throws RunnerException {
        var opt = new OptionsBuilder()
                .include(RedisBasicOperateBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(5)
                .build();
        new Runner(opt).run();
    }

    @Setup
    public void init() {
        context = SpringApplication.run(RedisMythApplication.class);
        redisBasicOperateService = context.getBean(RedisBasicOperateService.class);
    }

    @TearDown
    public void clean() {
        context.close();
    }

    @Benchmark
    public void sampleAddBenchmark() {
        redisBasicOperateService.sampleSetValue();
    }
}
