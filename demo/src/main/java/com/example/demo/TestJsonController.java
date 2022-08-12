package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 注解 @RestController 指定该类下的所有方法都不走视图解析器，所以里面的方法不用返回 .jsp 或者 html
 */
@RestController
public class TestJsonController {


    @RequestMapping("/testJson1")
    public String testJson1() throws JsonProcessingException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("10010");
        userInfo.setName("little pig");
        userInfo.setAge(23);

        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(userInfo);
        System.out.println("userStr >>> " + userStr);
        return userStr;
    }

    @RequestMapping("/testJson2")
    public String testJson2() throws JsonProcessingException {
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserId("10010");
        userInfo1.setName("little pig");
        userInfo1.setAge(23);

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUserId("10020");
        userInfo2.setName("big pig");
        userInfo2.setAge(33);

        List<UserInfo> userInfoList = new ArrayList<>(4);
        userInfoList.add(userInfo1);
        userInfoList.add(userInfo2);

        ObjectMapper objectMapper = new ObjectMapper();
        String userList = objectMapper.writeValueAsString(userInfoList);
        System.out.println("userList >>> " + userList);
        return userList;
    }

    @RequestMapping("/dateJson")
    public String json3() throws JsonProcessingException {
        //方式一：原始纯java日期转换：推荐使用
        // String date = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss").format(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        //方式二：使用mapper来制定日期格式，先关闭时间戳表示
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss"));
        Date date = new Date();
        return objectMapper.writeValueAsString(date);
    }

    /// ---------------------- 下面使用 fastJson ------------

    @RequestMapping("/testFastJson")
    public String testFastJson() {
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserId("10010");
        userInfo1.setName("little pig");
        userInfo1.setAge(23);

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUserId("10020");
        userInfo2.setName("big pig");
        userInfo2.setAge(33);

        List<UserInfo> userInfoList = new ArrayList<>(4);
        userInfoList.add(userInfo1);
        userInfoList.add(userInfo2);

        String jsonStr = JSON.toJSONString(userInfoList);
        return jsonStr;
    }
}
