package com.test.annotation;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ResourceLoader;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ExtClassPathXmlApplicationContext {
    // 扫包范围
    private String packageName;
    private ConcurrentHashMap<String, Object> beans;


    public ExtClassPathXmlApplicationContext(String packageName) throws Exception {
        this.packageName = packageName;
        beans = new ConcurrentHashMap<>();
        // 初始化beans Service注解
        initBeans();
        // 初始化属性 及 Autowired注解
        initAttributes();
    }

    private void initAttributes() throws Exception {
        for (Object o : beans.keySet()) {
            System.out.println("key=" + o + " value=" + beans.get(o));
            // 依赖注入
            attributeAssign(beans.get(o));
        }
    }


    // 初始化对象
    public void initBeans() throws IllegalArgumentException, IllegalAccessException {
        // 1.扫包
        List<Class<?>> classes = ClassUtil.getClasses(packageName);
        // 2.判断是否有注解
        ConcurrentHashMap<String, Object> findClassExistAnnotation = findClassExistAnnotation2(classes);
        if (findClassExistAnnotation == null || findClassExistAnnotation.isEmpty()) {
            throw new RuntimeException("该包下没有这个注解");
        }
    }

    public Object getBean(String beanId) throws Exception {
        if (beanId == null || StringUtils.isEmpty(beanId)) {
            throw new RuntimeException("beanId不能为空");
        }
        Object class1 = beans.get(beanId);
        if (class1 == null) {
            throw new RuntimeException("该包下没有BeanId为" + beanId + "的类");
        }
        return class1;
    }

    // 是否有注解
    public ConcurrentHashMap<String, Object> findClassExistAnnotation(List<Class<?>> classes)
            throws IllegalArgumentException {
        for (Class<?> class1 : classes) {
            System.out.println(" >>>>> className = " + class1.getName());
            String className = class1.getName();
            if (!class1.isInterface() && !className.equals("")) {
                if (className.contains("Util") || className.contains("Context")) {
                    continue;
                }
                className = className.substring(className.indexOf(".", packageName.length())+1);
                String beanId = className;
                System.out.println("findClassExistAnnotation >>> beanId = " + beanId);
                if (StringUtils.isNotEmpty(beanId)) {
                    // 获取当前类名
                    beanId = toLowerCaseFirstOne(beanId);
                }
                Object newInstance = newInstance(class1);
                beans.put(beanId, newInstance);
            }
        }
        System.out.println("beans = " + JSON.toJSONString(beans));
        return beans;
    }

    public ConcurrentHashMap<String, Object> findClassExistAnnotation2(List<Class<?>> classes)
            throws IllegalArgumentException {
        for (Class<?> class1 : classes) {
            System.out.println(" >>>>> className = " + class1.getName());
            ZjtService annotation = class1.getAnnotation(ZjtService.class);

            if (annotation != null) { // 只动态生成包含 ZjtService 注解的类的实例, 类似于 Spring 的依赖注入的注解类 @Component
                // beanId 类名小写
                String beanId = annotation.value();
                System.out.println("findClassExistAnnotation >>> beanId = " + beanId);
                if (StringUtils.isEmpty(beanId)) {
                    // 获取当前类名
                    beanId = toLowerCaseFirstOne(class1.getName());
                }
                Object newInstance = newInstance(class1);
                beans.put(beanId, newInstance);
            }
        }
        System.out.println("beans = " + JSON.toJSONString(beans));
        return beans;
    }

    // 首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    // 通过反射生成对象
    public Object newInstance(Class<?> classInfo) {
        try {
            return classInfo.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("反射生成对象失败" + e.getMessage());
        }
    }

    // 依赖注入实现原理
    public void attributeAssign(Object object) throws Exception {
        // 1.使用反射机制获取当前类的所有属性
        Field[] declaredFields = object.getClass().getDeclaredFields();
        // 2.判断当前类是否存在注解
        for (Field field : declaredFields) {
            ZjtAutoWired annotation = field.getAnnotation(ZjtAutoWired.class);
            if (annotation != null) {
                // 获取属性名称
                String name = field.getName();
                System.out.println("----- attributeAssign --- name = " + name + " , object = " + object);
                // 根据beanName查找对象
                Object newBean = getBean(name);
                // 3.默认使用属性名称,查找bean容器对象
                field.setAccessible(true);
                // 给属性赋值 将对象注入到 属性中
                field.set(object, newBean);
            }
        }
    }
}
