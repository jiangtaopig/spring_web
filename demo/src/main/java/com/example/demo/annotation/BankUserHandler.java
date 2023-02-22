package com.example.demo.annotation;

import org.junit.Test;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

@Component
public class BankUserHandler {

    private String remoteCall(AbstractAPI api) throws IOException {
        //从BankAPI注解获取请求地址
        BankApi bankAPI = api.getClass().getAnnotation(BankApi.class);
        bankAPI.url();
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(api.getClass().getDeclaredFields()) //获得所有字段
                .filter(field -> field.isAnnotationPresent(BankApiField.class)) //查找标记了注解的字段
                .sorted(Comparator.comparingInt(a -> a.getAnnotation(BankApiField.class).order())) //根据注解中的order对字段排序
                .peek(field -> field.setAccessible(true)) //设置可以访问私有字段
                .forEach(field -> {
                    //获得注解
                    BankApiField bankAPIField = field.getAnnotation(BankApiField.class);
                    Object value = "";
                    try {
                        //反射获取字段值
                        value = field.get(api);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    //根据字段类型以正确的填充方式格式化字符串
                    switch (bankAPIField.type()) {
                        case "S": {
                            stringBuilder.append(String.format("%-" + bankAPIField.length() + "s", value.toString()).replace(' ', '_'));
                            break;
                        }
                        case "N": {
                            stringBuilder.append(String.format("%" + bankAPIField.length() + "s", value.toString()).replace(' ', '0'));
                            break;
                        }
                        case "M": {
                            if (!(value instanceof BigDecimal))
                                throw new RuntimeException(String.format("{} 的 {} 必须是BigDecimal", api, field));
                            stringBuilder.append(String.format("%0" + bankAPIField.length() + "d",
                                    ((BigDecimal) value).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
                            break;
                        }
                        default:
                            break;
                    }
                });
        String param = stringBuilder.toString();
        long begin = System.currentTimeMillis();
        //发请求 ....就不模拟了。
        String res = "调用银行API :" + bankAPI.desc() + " , API : " + bankAPI.url() + "  , 参数 : " + param;
        System.out.println("res = " + res);

        return res;
    }


    public void createUser(String name, String idCard, String mobile, int age) throws IOException {
        CreateUserAPI createUserAPI = new CreateUserAPI();
        createUserAPI.setName(name);
        createUserAPI.setIdCard(idCard);
        createUserAPI.setMobile(mobile);
        createUserAPI.setAge(age);
        String result = remoteCall(createUserAPI);
        System.out.println("createUser  >>> createUser : " + result);
    }

    public void pay(long orderId, BigDecimal amount) throws IOException {
        PayAPI payAPI = new PayAPI();
        payAPI.setOrderId(orderId);
        payAPI.setAmount(amount);
        String result = remoteCall(payAPI);
        System.out.println("pay  >>> pay : " + result);
    }

    @Test
    public void test() throws IOException {

        createUser("pig", "342323190009113323", "13655555555", 23);
        pay(1234567, BigDecimal.valueOf(234.5));
    }
}
