package com.example.demo.stream;

import com.alibaba.fastjson.JSON;
import com.example.demo.pattern_design.A;
import com.example.demo.utils.ZhuConstant;
import lombok.Data;
import org.apache.commons.lang3.text.StrBuilder;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {

    @Test
    public void testMap() {
        Map<String, String> map1 = new HashMap<>();
        // 如果这个key在map中不存在 则插入，如果存在则返回 key 对应的值，并不会更新map中key对应的值。
        String v = map1.putIfAbsent("1", "firstPut");
        System.out.println("map = " + map1 + " , v = " + v);
        String v2 = map1.putIfAbsent("1", "secondPut");
        System.out.println("map = " + map1 + " , v2 = " + v2);
    }


    @Test
    public void testStreamOpt() {
        Apple apple1 = new Apple();
        apple1.setName("苹果1");
        apple1.setWeight(1);

        Apple apple2 = new Apple();
        apple2.setName("苹果2");
        apple2.setWeight(2);

        Apple apple3 = new Apple();
        apple3.setName("苹果3");
        apple3.setWeight(4);

        List<Apple> appleList = Arrays.asList(apple1, apple2, apple3);
        List<Integer> appleMapList = appleList.stream()
                .map(apple -> {
                    apple.setWeight(apple.getWeight() * 3);
                    return apple.getWeight();
                }).collect(Collectors.toList());


        // peek 和 map 的区别：peek 中 参数类型为 Consumer，它的 accept 方法没有返回值
        // 所以，不能形成新的流，但能修改引用类型字段的值

        List<Apple> applePeekList = appleList.stream().peek(apple -> {
            apple.setWeight(apple.getWeight() * 5);
        }).collect(Collectors.toList());

        System.out.println("appleList >>> " + JSON.toJSONString(appleList) + " , applePeekList = " + JSON.toJSONString(applePeekList));

        // flatMap 和 map不一样的地方在于，flatMap 中返回的依然是 Stream

        List<String> nameList = appleList.stream().flatMap(apple -> {
            Stream<String> stringStream = Stream.of(apple.getName());
            return stringStream;
        }).collect(Collectors.toList());

        System.out.println("nameList ======= " + nameList);
    }

    /**
     * 测试函数式接口
     */
    @Test
    public void testFunctionOpt() {

        String name = "pig";
        int age = 23;
        MyStudent myStudent = testBIFunction(name, age, (s, integer) -> {
            MyStudent myStudent1 = new MyStudent(name, age);
            return myStudent1;
        });
        System.out.println("myStudent = " + JSON.toJSONString(myStudent));
    }


    public MyStudent testBIFunction(String name, Integer age, BiFunction<String, Integer, MyStudent> consumer) {
        return consumer.apply(name, age);
    }


    @Test
    public void test1() {
        ResourceVO r1 = new ResourceVO();
        r1.setImageGroup("group1");
        r1.setImageType("type1");
        r1.setUrl("url1");
        r1.setStep("Step12");


        ResourceVO r2 = new ResourceVO();
        r2.setImageGroup("group1");
        r2.setImageType("type2");
        r2.setUrl("url2");
        r2.setStep("Step2");

        ResourceVO r3 = new ResourceVO();
        r3.setImageGroup("group2");
        r3.setImageType("type3");
        r3.setUrl("url3");
        r3.setStep("Step1");

        ResourceVO r4 = new ResourceVO();
        r4.setImageGroup("group3");
        r4.setImageType("type4");
        r4.setUrl("url4");
        r4.setStep("Step21");


        List<ResourceVO> resourceVOList = Arrays.asList(r1, r2, r3, r4);

        Map<String, String> map = resourceVOList.stream().collect(Collectors.toMap(ResourceVO::getImageGroup, re -> {
            String imageType = re.imageType;
            System.out.println("--------- imageType >>>" + imageType + ", groupType = " + re.imageGroup);
            return imageType;
        }, (k1, k2) -> {
            return k1;
        }));
        System.out.println("listMap2 = " + map);
    }

    @Test
    public void mainTest() {
        ResourceVO r1 = new ResourceVO();
        r1.setImageGroup("group1");
        r1.setImageType("type1");
        r1.setUrl("url1");
        r1.setStep("Step12");


        ResourceVO r2 = new ResourceVO();
        r2.setImageGroup("group1");
        r2.setImageType("type2");
        r2.setUrl("url2");
        r2.setStep("Step2");

        ResourceVO r3 = new ResourceVO();
        r3.setImageGroup("group2");
        r3.setImageType("type3");
        r3.setUrl("url3");
        r3.setStep("Step1");

        ResourceVO r4 = new ResourceVO();
        r4.setImageGroup("group3");
        r4.setImageType("type4");
        r4.setUrl("url4");
        r4.setStep("Step21");

        List<ResourceVO> resourceVOList = Arrays.asList(r1, r2, r3, r4);

        System.out.println("resourceVOList = " + JSON.toJSONString(resourceVOList));

        Map<String, String> map = resourceVOList.stream().collect(Collectors.toMap(ResourceVO::getImageGroup, re -> {
            String imageType = re.imageType;
            System.out.println("--------- imageType >>>" + imageType + ", groupType = " + re.imageGroup);
            return imageType;
        }, (k1, k2) -> k2));
        System.out.println("listMap2 = " + map);

        /**
         * 根据 imageGroup 将 imageType 分类
         */
        Map<String, List<String>> groupAndTypeMap = new HashMap<>();
        resourceVOList.forEach(resourceVO -> {
            List<String> imageTypes = groupAndTypeMap.computeIfAbsent(resourceVO.imageGroup, a -> new ArrayList<>());
            imageTypes.add(resourceVO.imageType);
        });


        System.out.println("groupAndTypeMap >>>>>>>>" + groupAndTypeMap);

        Map<String, List<ResourceVO>> groupedMap = resourceVOList.stream()
                .collect(Collectors.groupingBy(ResourceVO::getImageGroup));

        System.out.println("------ 111 sorted resourceVOList = " + resourceVOList);
        System.out.println("resourceVOList = " + resourceVOList.hashCode());
        sort(resourceVOList);
        System.out.println("resourceVOList 222 = " + resourceVOList.hashCode());

        System.out.println("------after sorted resourceVOList = " + resourceVOList);

        System.out.println("---------- groupedMap ------" + JSON.toJSONString(groupedMap));


        System.out.println("-----------------------------------------------");
        System.out.println("r1 hashcode before change = " + r1.hashCode());
        change(r1);
        System.out.println("r1 hashcode after change = " + r1.hashCode());
    }

    private List<ResourceVO> sort(List<ResourceVO> resourceVOList) {
        Map<Integer, List<ResourceVO>> integerListMap = new HashMap<>();

        for (ResourceVO resourceVO : resourceVOList) {
            List<ResourceVO> resourceVOS = integerListMap.computeIfAbsent(resourceVO.step.length(), ArrayList::new);
            resourceVOS.add(resourceVO);
        }

        System.out.println("---------- integerListMap ------" + integerListMap);
        List<ResourceVO> resourceVOList1 = new ArrayList<>();
        integerListMap.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(e -> {
                    resourceVOList1.addAll(e.getValue());
                });
        System.out.println("---------- resourceVOList1 ------" + resourceVOList1);
        resourceVOList = resourceVOList1;
        System.out.println("after sorted resourceVOList  = " + resourceVOList.hashCode());
        return resourceVOList;
    }


    private ResourceVO change(ResourceVO vo) {
        ResourceVO vo1 = new ResourceVO();
        vo1.setImageGroup("group3");
        vo1.setImageType("type4");
        vo1.setUrl("url4");
        vo1.setStep("Step21");
        vo = vo1;
        System.out.println(" ----- change ---- vo hashCode = " + vo.hashCode());
        return vo;
    }


    @Test
    public void testStudent() {
        MyStudent zhanshan = new MyStudent("张三", 26);
        List<Course> zhanshanCourse = Arrays.asList(new Course("语文"), new Course("数学"));
        zhanshan.setCourses(zhanshanCourse);

        MyStudent lisi = new MyStudent("李四", 30);
        List<Course> lisiCourse = Arrays.asList(new Course("语文"), new Course("数学"), new Course("英语"), new Course("计算机"));
        lisi.setCourses(lisiCourse);

        MyStudent wanwu = new MyStudent("王五", 28);
        List<Course> wanwuCourse = Arrays.asList(new Course("体育"), new Course("数学"),
                new Course("英语"), new Course("计算机"), new Course("物理"));
        wanwu.setCourses(wanwuCourse);

        List<MyStudent> myStudentList = Arrays.asList(zhanshan, lisi, wanwu);

        myStudentList = myStudentList.stream().sorted(new Comparator<MyStudent>() {
            @Override
            public int compare(MyStudent o1, MyStudent o2) {
                return o2.age - o1.age;
            }
        }).collect(Collectors.toList());


        /** 求所有学生的年龄之和 */
        Integer sumAge = myStudentList.stream().collect(Collectors.summingInt(MyStudent::getAge));
        System.out.println("所有学生的年龄之和等于 >>> " + sumAge);

        /** flapMap 就是拍平的作用，比如这里将 List<MyStudent> 通过 flatMap 操作, 拍平成 List<Course> 然后通过 map 取课程名，最后生成 List<String> */
        List<String> courses = myStudentList.stream()
                .map(myStudent -> myStudent.getCourses())
                .flatMap(Collection::stream)
                .map(Course::getCourseName)
                .collect(Collectors.toList());

        myStudentList.stream()
                .filter(myStudent -> {
                    return myStudent.name.equals("王五");
                })
                .map(myStudent -> {
                    return myStudent.name;
                });

        System.out.println("---- courses ----" + courses);

        // 演示不用 flatMap

        List<List<Course>> courseList = myStudentList.stream()
                .map(MyStudent::getCourses)
                .collect(Collectors.toList());
        System.out.println("----- courseList -----" + courseList);

        /** flatMap 用法2, flatMap 必须返回 Stream 类型 */

        List<Course> coursesL = myStudentList.stream()
                .map(MyStudent::getCourses)
                .flatMap(coursesList -> {
                    System.out.println(" >>>>>>>>>>>> coursesList  " + coursesList);
                    Stream<Course> c = coursesList.stream();
                    return c;
                }).collect(Collectors.toList());

        System.out.println("coursesL  >>> " + coursesL);


        // 过滤出课程数大于2的学生
        List<MyStudent> myStudentList1 = myStudentList.stream()
                .filter(myStudent -> {
                    return myStudent.getCourses().size() > 2;
                }).collect(Collectors.toList());

        System.out.println("--------- myStudentList1 >>>" + myStudentList1);


        // 找到第一个学生年龄大于 27岁的学生
        Optional<MyStudent> myStudent = myStudentList.stream()
                .filter(myStudent1 -> {
                    return myStudent1.age > 27;
                }).findFirst();

        System.out.println("myStudent >>> " + myStudent);


        // 找到课程数最多的学生
        Optional<MyStudent> myStudent1 = myStudentList.stream().max(new Comparator<MyStudent>() {
            @Override
            public int compare(MyStudent o1, MyStudent o2) {
                return o1.courses.size() - o2.courses.size();
            }
        });

        System.out.println("myStudent1 is " + myStudent1.get());


        Optional<MyStudent> myStudent2 = myStudentList.stream()
                .max(Comparator.comparing(MyStudent::getAge));

        System.out.println("myStudent2 >>>> is " + myStudent2.get());

        // toArray 用法
        String[] strings = myStudentList.stream().map(MyStudent::getName).toArray(String[]::new);
        System.out.println("========================================");

        for (String s : strings) {
            System.out.println(">>>> name = " + s);
        }

        List<String> nameChangeList = myStudentList.stream()
                .map(myStudent3 -> {
                    myStudent3.name += "_xxx";
                    return myStudent3.name;
                }).collect(Collectors.toList());

        List<String> stringList1 = myStudentList.stream()
                .map(MyStudent::getName)
                .collect(Collectors.toList());

        System.out.println("修改姓名后的数据2 = " + nameChangeList + ", 原始数据 >>> " + stringList1);


        /** ------------------------------------ 使用 reduce ----------------------------------------------------*/
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        // 求和方式1
        Optional<Integer> sum1 = list.stream().reduce((x, y) -> {
            return x + y;
        });

        int sum2 = list.stream().reduce(Integer::sum).get();

        System.out.println("sum = " + sum1.get() + ", sum2 = " + sum2);

        // 计算学生年龄之和
        int age = myStudentList.stream().map(MyStudent::getAge).reduce(Integer::sum).get();
        System.out.println("age >>> " + age);

        // 计算学生年龄的方式2
        int age3 = myStudentList.stream().reduce(0, (sum, s) -> sum += s.age, (age1, age2) -> {
            return age1 + age2;
        });

        System.out.println("age3 = " + age3);

        // (k1, k2) -> k1 表示过滤相同的key
        Map<String, MyStudent> myStudentMap = myStudentList.stream().collect(Collectors.toMap(MyStudent::getName, a -> a, (k1, k2) -> k1));

        Map<String, List<Course>> listMap = myStudentList.stream().collect(Collectors.toMap(MyStudent::getName, MyStudent::getCourses, (k1, k2) -> k1));

        System.out.println("---------------------------------------------------");
        System.out.println("myStudentMap = " + myStudentMap);
        System.out.println("-------- listMap -----" + listMap);
    }

    @Test
    public void testDefinedCollector() {
        Apple apple1 = new Apple();
        apple1.setName("苹果1");
        apple1.setWeight(1);

        Apple apple2 = new Apple();
        apple2.setName("苹果2");
        apple2.setWeight(2);

        Apple apple3 = new Apple();
        apple3.setName("苹果3");
        apple3.setWeight(4);

        Integer weights = Stream.of(apple1, apple2, apple3)
                .collect(new SumCollector<>(Apple::getWeight));

        System.out.println("all apples weight  >>> " + weights);

        List<Apple> sortedApples = Stream.of(apple1, apple2, apple3)
                .sorted(Comparator.comparing(Apple::getWeight))
                .collect(Collectors.toList());
    }

    @Test
    public void testGeneric() {
        /**
         * ------------------------------------------------- 泛型相关 -------------------------------------------------------------------
         * <? super T>表示类型参数的范围是 T 和 T 的超类，直至 Object
         * <? extends T>表示类型参数的范围是 T 和 T 的子类
         */

//        // 能存不能取， 为啥能存不能取，因为 <? super Number> 能确定的是 Number 的任意父类包含Number
//        // 假设就是 Number ，那么如下：Double 和 Integer 都是 Number 的子类，会被强制转为 Number
//        List<Number> numberList = new ArrayList<>();
//        numberList.add(new Double(2.5));
//        numberList.add(new Integer(2));
//
//        List<? super Number> numList = new ArrayList<>();
//        numList.add(new Integer(1));
//
//        //
//        List<? extends Number> numbers = new ArrayList<>();
//
//        Number number = numbers.get(0);
//
//
//
//        //--------------------------------------------
//
//        List<Integer> integerList = new ArrayList<>();
//        List<Number> numberList2 = new ArrayList<>();
//        copy(numberList2, integerList);

        List<?> list = new ArrayList<Integer>();
        List<Float> floatList = new ArrayList<>();
        list = floatList;

        /**
         * List <? extends Number> 确定参数的上界, 即只能传入的是 Number 的子类：可以接受 List<Integer> 或 List<Float>
         * List <? super Number> 确定的是下界, 即只能传入的是 Number 的父类：可以接受 List<Object> 但不能接受 List<Integer>
         */
//        List<? super Number> interList ;
//        List<Integer> nList = new ArrayList<>();
//        interList = nList; // 会报错



        List<Double> doubleList = new ArrayList<>();
        doubleList.add(2.0);

        printVal(doubleList);

        Comparator<Apple> appleComparator = comparing(Apple::getWeight);
        Apple apple1 = new Apple();
        apple1.setWeight(1);
        apple1.setName("apple1");

        Apple apple2 = new Apple();
        apple2.setWeight(2);
        apple2.setName("apple2");

        // test sourceTree
        int res = appleComparator.compare(apple1, apple2);

        System.out.println("res ==== "+res);

        // B 类必须是 Comparable 的实现类，否则编译报错，因为 comparing 的入参 keyExtractor 指定的类型 U 必须是 Comparable的子类或实现类
        Comparator<A> aComparator = comparing(A::getB);


        Apple[] appleList = new Apple[2];
        appleList[0] = apple1;
        appleList[1] = apple2;

        Arrays.sort(appleList, comparing(Apple::getWeight));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse("2023-06-23 15:18:00");
            boolean flag = judgeInvalidTime(date);
            System.out.println("flag = " + flag);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断 date 比当前时间的3天之前是大还是小，大 返回 true
     */
    public boolean judgeInvalidTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -3);
        Date invalidTime = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateStr = sdf.format(invalidTime);
        System.out.println(dateStr);

        // date 日期大于等于 invalidTime 返回true
        return date.after(invalidTime);
    }

    private void printVal(List<? extends Number> list) {  // ? extends Number 能取不能存, 表示的是 Number 的子类型，
        if (list != null && list.size() >0) {
            // 这里最好用 Number 类型去接，假设你用 Integer 去接，传入 list 的是 Float 就会报错，如下：
            for (Number number : list) {
                System.out.println("number = " + number);
            }
        }
    }

    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        int srcSize = src.size();
        if (srcSize > dest.size())
            throw new IndexOutOfBoundsException("Source does not fit in dest");

        ListIterator<? super T> di=dest.listIterator();
        ListIterator<? extends T> si=src.listIterator();
        for (int i=0; i<srcSize; i++) {
            di.next();
            di.set(si.next());
        }
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return (Comparator<T> & Serializable) (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
    }

    @Data
    class A {
        private String aName;

        private B b;
    }

    @Data
    class B implements Comparable<B>{
        private String bName;

        @Override
        public int compareTo(@NotNull TestStream.B o) {
            return bName.compareTo(o.bName);
        }
    }


    @Test
    public void testDefinedFilter() {
        Apple apple1 = new Apple();
        apple1.setName("苹果1");
        apple1.setWeight(1);

        Apple apple2 = new Apple();
        apple2.setName("苹果2");
        apple2.setWeight(2);

        Apple apple3 = new Apple();
        apple3.setName("苹果3");
        apple3.setWeight(3);

        Apple apple4 = new Apple();
        apple4.setName("苹果2");
        apple4.setWeight(4);

        List<Apple> appleList = new ArrayList<>();
        appleList.add(apple1);
        appleList.add(apple2);
        appleList.add(apple3);
        appleList.add(apple4);

        List<Apple> filteredApples = appleList.stream()
                .filter(ZhuConstant.distinctByKey(apple -> {
                    return apple.getName();
                }))
                .collect(Collectors.toList());

        System.out.println("filtered >>> apples =  " + JSON.toJSONString(filteredApples));


        List<BigDecimal> list = Arrays.asList(
                BigDecimal.valueOf(9.99),
                BigDecimal.valueOf(2.99),
                BigDecimal.valueOf(8.99));

        // method reference
        List<Invoice> invoices = fakeInvoice(list, Invoice::new);
    }

    static List<Invoice> fakeInvoice(List<BigDecimal> list, Function<BigDecimal, Invoice> func) {
        List<Invoice> result = new ArrayList<>();

        for (BigDecimal amount : list) {
            result.add(func.apply(amount));
        }
        return result;
    }


    @Test
    public void testMyCollector() {
        Integer result = Stream.of(new Score(1), new Score(2), new Score(3), new Score(4))
                .map(Score::getScore)
//                .map(s -> {
//                    return s.getScore();
//                })
                .collect(new MyCollector());
        System.out.println(result);


        Integer sumScore = Stream.of(new Score(1), new Score(2), new Score(3), new Score(4))
                .collect(new SumCollector<>(Score::getScore));
        System.out.println("sumScore = " + sumScore);
    }


    @Test
    public void testFindAny() {
        System.out.println("------------------- testFindAny -----------------------------------");
        boolean flag = Stream.of("hello", "world", "java", "c++")
                .anyMatch(s -> {
                    System.out.println("s >>> " + s);
                    return s.equals("java");
                });
        System.out.println("flag = " + flag);

        int a = Stream.of(1, 4, 7, 3, 3, 2)
                .filter(v -> v > 2)
                .findAny().get();
        System.out.println("a = " + a);
    }

    @Test
    public void testGroupingBy() {

        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("1", "mmm");
        String v = stringMap.computeIfPresent("1", (a, b) -> {
            System.out.println("a = "+a + " , upper = " + a.toUpperCase() + " , b = " + b);
            return a.toUpperCase(Locale.ROOT);
        });

        System.out.println("v = " + v);


        List<String> couponNameList = Arrays.asList("基础保养券", "大额维修券", "维修服务券", "尊享换新券", "大额维修券", "基础保养券");

        Map<String, AtomicInteger> map = new HashMap<>();
        couponNameList.forEach(s -> {
            AtomicInteger sameCouponNums = map.computeIfAbsent(s, a -> new AtomicInteger(0));
            sameCouponNums.addAndGet(1);
        });



        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, AtomicInteger> entry : map.entrySet()) {
            if (entry.getValue().get() > 1) {
                sb.append(entry.getKey()).append("x").append(entry.getValue().get()).append(",");
            } else {
                sb.append(entry.getKey()).append(",");
            }
        }

        String couponNames = sb.substring(0, sb.length() - 1);

        System.out.println("sb = " + couponNames);
        System.out.println("map = " + JSON.toJSONString(map));

        Map<String, List<String>> map1 = couponNameList.stream().collect(Collectors.groupingBy(s -> {
            System.out.println("s >> " + s);
            return s;
        }));
        System.out.println("map1 == " + JSON.toJSONString(map1));


      /***  自定义 groupingBy 实现 */

        Map<String, String> aa = couponNameList.stream().collect(Collectors.groupingBy(new Function<String, String>() {

            @Override
            public String apply(String s) {
//                System.out.println("s >>>-------- " + s);
                return s;
            }
        }, new Supplier<Map<String, String>>() {
            @Override
            public Map<String, String> get() {
                return new HashMap<>();
            }
        }, new Collector<String, Map<String, AtomicInteger>, String>() {
            // String 是输入数据类型
            // Map<String, AtomicInteger> 结果容器的类型
            // String 执行最后终结操作的返回值，即 下面的 finisher 方法的返回值

            @Override
            public Supplier<Map<String, AtomicInteger>> supplier() {
                return HashMap::new;
            }

            @Override
            public BiConsumer<Map<String, AtomicInteger>, String> accumulator() {
                return (map, s) -> {
//                    System.out.println("= ====== = s = " + s);
                    AtomicInteger atomicInteger = map.computeIfAbsent(s, a -> new AtomicInteger(0));
                    atomicInteger.addAndGet(1);
                };
            }

            @Override
            public BinaryOperator<Map<String, AtomicInteger>> combiner() {
                // 合并 并行处理的结果
                return (map1, map2) -> {
                    // 我们这里不是并行的，所以 combiner 方法是执行不到的
                    System.out.println("map1 = "+JSON.toJSONString(map1) + " , map2 = " + JSON.toJSONString(map2));
                    map1.putAll(map2);
                    return map1;
                };
            }

            @Override
            public Function<Map<String, AtomicInteger>, String> finisher() {
                // 计算最终的执行结果，作为 Map 的 value
                return map -> {
                    StringBuilder sb = new StringBuilder();
                    System.out.println("map = " + JSON.toJSONString(map));

                    for (Map.Entry<String, AtomicInteger> entry : map.entrySet()) {
                        if (entry.getValue().get() > 1) {
                            sb.append(entry.getKey()).append("x").append(entry.getValue().get()).append(",");
                        } else {
                            sb.append(entry.getKey()).append(",");
                        }
                    }
                    String ss = sb.substring(0, sb.length() - 1);
                    System.out.println("ss == " + ss);
                    return ss;
                };
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
        }));

        System.out.println("aa ========" + JSON.toJSONString(aa));

    }


    /**
     * -------------------------------------------------- model 定义----------------------------------------------------------------
     */

    @Data
    public class TestMM {
        private void testFuncReference() {
            new Random().ints(10).map(this::selfIncreasing)
                    .forEach(e -> {
                        System.out.println("e ==> " + e);
                    });
        }

        public int selfIncreasing(int num) {
            System.out.println("selfIncreasing >>> num = " + num);
            return num++;
        }
    }

    @Data
    public static class ResourceVO {
        private String imageGroup;
        private String imageType;
        private String url;
        private String step;
    }


    @Data
    public class MyStudent {
        private String name;
        private int age;
        private List<Course> courses;

        public MyStudent(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @Data
    public static class Course {
        private String courseName;

        public Course(String courseName) {
            this.courseName = courseName;
        }
    }

    @Test
    public void testDDD() {
        Invoice invoice = new Invoice(BigDecimal.valueOf(1.99));
        invoice.setNo("111");
        invoice.qty = 3;

        InvoiceCalculator formula = new InvoiceCalculator();

        // method reference
        BigDecimal result2 = calculate(formula, invoice, InvoiceCalculator::normal);    // 5.97
        System.out.println("result2 = " + result2);

        // method reference
        BigDecimal result4 = calculate(formula, invoice, InvoiceCalculator::promotion); // 5.37

        // 类型于下面的用法：
        // List::add 方法是一个实例方法，它的第一个参数是隐式的，即为调用这个方法的对象，而第二个参数是显式的，即为方法的参数。
        // 所以这方法的然名是 (List，tring) -> boolean,而 Bionsumer 的签名是 (list,String) -> void,
        // 所以可以使用 List::add 来代替 BiConsumer。
        // 这种情况下，第一个参数是隐式的，第二个参数是显式的，所以可以使用 List::add 来代替 Biconsumer。

        BiConsumer<List<String>, String> biConsumer = List::add;

        System.out.println("----------------------------------------------------------------------------------------------------------");
        Supplier<InvoiceCalculator> invoiceCalculatorSupplier = InvoiceCalculator::new;
        InvoiceCalculator invoiceCalculator = invoiceCalculatorSupplier.get();

        BiConsumer<InvoiceCalculator, String> addNameConsumer = InvoiceCalculator::setName;

        addName(invoiceCalculator, "zhu-jiang-tao", addNameConsumer);
        System.out.println("-------- name ----------" + invoiceCalculator.name);

        BigDecimal result5 = doSth(invoiceCalculator, InvoiceCalculator::defaultVal);
        System.out.println("result5 = " + result5);

        BiFunction<String, String, Integer> compare = String::compareToIgnoreCase;

        int res = compareIgnoreCase("a", "b", compare);
        System.out.println("res == " + res);

//        BiPredicate

        BiPredicate<String, String> predicate = String::equalsIgnoreCase;

    }

    static BigDecimal calculate(InvoiceCalculator formula, Invoice s1,
                                BiFunction<InvoiceCalculator, Invoice, BigDecimal> func) {
        return func.apply(formula, s1);
    }

    static Integer compareIgnoreCase(String a, String b, BiFunction<String, String, Integer> mapper) {
        return mapper.apply(a, b);
    }

    static boolean testPredict(String a, String b, BiPredicate<String, String> predicate) {
        return predicate.test(a, b);
    }

    /**
     * BiConsumer 是没有返回值的，如果需要返回值 可以使用 BiFunction
     */
    static void addName(InvoiceCalculator formula, String name, BiConsumer<InvoiceCalculator, String> biConsumer) {
        biConsumer.accept(formula, name);
    }

    static BigDecimal doSth(InvoiceCalculator formula, Function<InvoiceCalculator, BigDecimal> mapper) {
        return mapper.apply(formula);
    }

    @Data
    static
    class Invoice {
        String no;
        BigDecimal unitPrice;
        Integer qty;

        public Invoice() {
        }

        public Invoice(BigDecimal bigDecimal) {
            unitPrice = bigDecimal;
        }

        //... generated by IDE
    }

    static class InvoiceCalculator {

        private String name;

        public BigDecimal normal(Invoice obj) {
            return obj.getUnitPrice().multiply(BigDecimal.valueOf(obj.qty));
        }

        public BigDecimal defaultVal() {
            return BigDecimal.valueOf(20);
        }

        public BigDecimal promotion(Invoice obj) {
            return obj.getUnitPrice()
                    .multiply(BigDecimal.valueOf(obj.qty))
                    .multiply(BigDecimal.valueOf(0.9))
                    .setScale(2, RoundingMode.HALF_UP);
        }

        public void setName(String name) {
            this.name = name;
            System.out.println("--------- name = " + name);
        }

        public String getName() {
            return name;
        }
    }
}
