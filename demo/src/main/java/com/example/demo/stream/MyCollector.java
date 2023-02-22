package com.example.demo.stream;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * 自定义 Collector
 * 功能：计算输入流的额 int 值的平方和
 */
public class MyCollector implements Collector<Integer, AtomicInteger, Integer> {

    private ToIntFunction<Integer> mapper;

//    public MyCollector(ToIntFunction<Integer> mapper) {
//        this.mapper = mapper;
//    }

    @Override
    public Supplier<AtomicInteger> supplier() {
        return () -> new AtomicInteger(0);
    }

    @Override
    public BiConsumer<AtomicInteger, Integer> accumulator() {
        // 每个元素进入的时候的遍历策略，当前元素值的平方与sum结果进行累加
        return (sum, current) -> {
            System.out.println("sum = " + sum + " , current = " + current);
            sum.addAndGet(current * current);
        };
    }

    @Override
    public BinaryOperator<AtomicInteger> combiner() {
        // 多个分段结果处理的策略，直接相加
        return (sum1, sum2) -> {
            System.out.println("combiner >>>>>> sum1 = " + sum1 + " , sum2 = " + sum2);
            sum1.addAndGet(sum2.get());
            return sum1;
        };
    }

    @Override
    public Function<AtomicInteger, Integer> finisher() {
        return AtomicInteger::get;
    }

    @Override
    public Set<Characteristics> characteristics() {
        Set<Characteristics> characteristics = new HashSet<>();
        // 指定该收集器支持并发处理（前面也发现我们采用了线程安全的AtomicInteger方式）
        characteristics.add(Characteristics.CONCURRENT);
        // 声明元素数据处理的先后顺序不影响最终收集的结果
        characteristics.add(Characteristics.UNORDERED);
        // 注意:这里没有添加下面这句，因为finisher方法对结果进行了处理，非恒等转换
        // characteristics.add(Characteristics.IDENTITY_FINISH);
        return characteristics;
    }
}

