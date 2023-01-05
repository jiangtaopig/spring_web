package com.example.demo;

import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {

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

        Map<String, List<String>> listMap = resourceVOList.stream().collect(Collectors.toMap(ResourceVO::getImageGroup, re -> {
            String imageType = re.imageType;
            System.out.println("imageType >>>" + imageType + ", groupType = " + re.imageGroup);
            return new ArrayList<>();
        }, (k1, k2) -> k1));

        System.out.println("listMap = " + listMap);


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


        System.out.println("---------- groupedMap ------" + groupedMap);

        /**
         *
         Map<String, List<String>> map = resourceVOList.stream()
         .collect(Collectors.groupingBy(ResourceVO::getImageGroup, new Collector<ResourceVO, Object, List<String>>() {

        @Override public Supplier<Object> supplier() {
        return ArrayList<String>::new;
        }

        @Override public BiConsumer<Object, ResourceVO> accumulator() {
        return Map::put;
        }

        @Override public BinaryOperator<Object> combiner() {
        return null;
        }

        @Override public Function<Object, List<String>> finisher() {
        return null;
        }

        @Override public Set<Characteristics> characteristics() {
        return null;
        }
        }));
         */


        List<String> steps = Arrays.asList("Step1", "Step5", "Step3", "Step21", "Step2", "Step11");

//        steps.stream().sorted(Comparator.comparing())


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
        integerListMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(e -> {
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

    /**
     * stream不存储数据，而是按照特定的规则对数据进行计算，一般会输出结果。
     * stream不会改变数据源，通常情况下会产生一个新的集合或一个值。
     * stream具有延迟执行特性，只有调用终端操作时，中间操作才会执行。
     */
    @Test
    public void testStudent() {
        MyStudent zhanshan = new MyStudent("张三", 26);
        List<Course> zhanshanCourse = Arrays.asList(new Course("语文"), new Course("数学"));
        zhanshan.setCourses(zhanshanCourse);

        MyStudent lisi = new MyStudent("张三", 30);
        List<Course> lisiCourse = Arrays.asList(new Course("语文"), new Course("数学"), new Course("英语"), new Course("计算机"));
        lisi.setCourses(lisiCourse);

        MyStudent wanwu = new MyStudent("王五", 28);
        List<Course> wanwuCourse = Arrays.asList(new Course("体育"), new Course("数学"),
                new Course("英语"), new Course("计算机"), new Course("物理"));
        wanwu.setCourses(wanwuCourse);

        List<MyStudent> myStudentList = Arrays.asList(zhanshan, lisi, wanwu);

        // flapMap 就是拍平的作用，比如这里将 List<MyStudent> 通过 flatMap 操作 拍平成 List<Course> 然后通过 map 取课程名，最后生成 List<String>
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

        // flatMap 用法2, flatMap 必须返回 Stream 类型

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

        List<String> stringList = myStudentList.stream()
                .map(myStudent3 -> {
                    MyStudent myStudent4 = new MyStudent(myStudent3.getName() + "**", myStudent3.age);
                    return myStudent4.name;
                }).collect(Collectors.toList());

        List<String> stringList1 = myStudentList.stream()
                .map(MyStudent::getName)
                .collect(Collectors.toList());

        System.out.println("修改姓名后的数据 = " + stringList + ", 原始数据 >>> " + stringList1);

        //------------------------------------ 使用 reduce ------------
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
    public void tesll() {
        String testData = "{\n" +
                "\t\"coupon\": [],\n" +
                "\t\"dealerCode\": \"VW00981\",\n" +
                "\t\"plateNo\": \"湘E88Y89\",\n" +
                "\t\"title\": \"到店提醒\",\n" +
                "\t\"carYear\": \"2019\",\n" +
                "\t\"content\": \"湘E88Y89（朗逸启航三厢 1.5L MT风尚版 2019款）已进店，剩余46天保险到期，未分配，任务状态：未跟进\",\n" +
                "\t\"vehicleBrand\": \"上汽大众\",\n" +
                "\t\"modelName\": \"大众汽车SVW71517CF轿车\",\n" +
                "\t\"expireDay\": 46,\n" +
                "\t\"vehicleSeries\": \"朗逸\",\n" +
                "\t\"exhaustScale\": 1498.000,\n" +
                "\t\"vehicleAlias\": \"朗逸启航三厢 1.5L MT风尚版\",\n" +
                "\t\"taskId\": 122947520\n" +
                "}";

        System.out.println(testData);
    }


    @Data
    public static class ResourceVO {
        private String imageGroup;
        private String imageType;
        private String url;
        private String step;
    }


    @Data
    public static class MyStudent {
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
}
