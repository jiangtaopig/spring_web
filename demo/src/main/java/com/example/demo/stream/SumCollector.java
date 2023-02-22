package com.example.demo.stream;

import java.util.HashSet;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

public class SumCollector<T> implements Collector<T, int[], Integer> {

    private final ToIntFunction<? super T> mapper;

    public SumCollector(ToIntFunction<? super T> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Supplier<int[]> supplier() {
        // 创建一个结果存储累加的容器，默认值为 0 ，后续在此基础上累加
        return () -> new int[1];
    }

    @Override
    public BiConsumer<int[], T> accumulator() {
        return (a, b) -> {
            // 把输入的泛型类型T 转化为 int 类型
            int value = mapper.applyAsInt(b);
            // 使用之前定义的存储结果的容器，即在 supplier() 方法中定义的数组 int[1] 来保存累加结果
            a[0] += value;
        };
    }

    @Override
    public BinaryOperator<int[]> combiner() {
        // 这个是在流的并行处理上用的：并行流是将Stream切分为多个分片，然后分别对分片进行计算处理得到分片各自的结果，
        // 最后这些分片的结果需要合并为同一份总的结果，这个如何合并，就是此处的实现：
        return (a, b) -> {
            a[0] += b[0];
            return a;
        };
    }

    @Override
    public Function<int[], Integer> finisher() {
        /** 当所有元素都处理完成后，在返回结果前的对结果的最终处理操作，当然也可以选择不做任何处理，直接返回 */
        return a -> a[0];
    }

    @Override
    public Set<Characteristics> characteristics() {
        Set<Characteristics> characteristics = new HashSet<>();
        // 指定该收集器支持并发处理（前面也发现我们采用了线程安全的AtomicInteger方式）
//        characteristics.add(Characteristics.CONCURRENT);
        // 声明元素数据处理的先后顺序不影响最终收集的结果
        characteristics.add(Characteristics.UNORDERED);
        // 注意:这里没有添加下面这句，因为finisher方法对结果进行了处理，非恒等转换
        // characteristics.add(Characteristics.IDENTITY_FINISH);
        return characteristics;
    }
}
