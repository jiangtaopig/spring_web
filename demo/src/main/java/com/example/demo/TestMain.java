package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.UserInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain {
    public static void main(String[] args) throws ParseException {

        System.out.println("sss = " + CustomerSourceEnum.AUTO_ISSUE.getMessage()+", code = " + CustomerSourceEnum.AUTO_ISSUE.getCode());

        if ("MSG_STORE_REMIND".equals(PushType.MSG_STORE_REMIND.name())) {
            System.out.println("---------------");
        } else {
            System.out.println("xxxxxxxxxxxx");
        }


        List<String> stringList = Collections.singletonList(PushType.MSG_STORE_REMIND.name());
        System.out.println(stringList.get(0));


        String businessData = "{\"modelName\":\"桑塔纳SVW7182HQD轿车\",\"licenseNo\":\"豫SF7098\",\"receiver\":\"APPTEST\",\"dealerCode\":\"VW00772\"," +
                "\"coupons\":\"大额维修券\",\"arrivalTime\":\"2022-10-19 14:55:50\",\"vin\":\"LSVT413379N511098\",\"taskId\":\"1222\"}";

        UserInfo userInfo = new UserInfo();
        userInfo.setAge(23);
        userInfo.setName("zz");
        userInfo.setUserId("11011");

        UserInfo.Address address = new UserInfo.Address();
        address.setRoom(601);
        address.setStreet("北新泾街道");

        userInfo.setAddress(address);

        String personStr = JSONObject.toJSONString(userInfo);

        JSONObject templateRow = JSONObject.parseObject(businessData);
        String taskId = templateRow.getString("taskId");
        System.out.println("task id = " + taskId);
        if (templateRow.containsKey("taskId")) {
            System.out.println("-------111");
            templateRow.put("renewData", personStr);
        }

        String aa = templateRow.toJSONString();
        System.out.println(aa);

        String time = "2022-03-17";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = simpleDateFormat.parse(time);
        long dd = date.getTime();
        System.out.println("dd = " + dd);

        String tt = "我是中国移动验证码是1234766哈哈236644";
        String code = patternCode(tt);

        System.out.println(">>>>>>> code <<<<<<<<<<< " + code);
    }


    /**
     * 提取4-6位的数字
     * @param patternContent
     * @return
     */
    private static String patternCode(String patternContent) {
        Pattern p = Pattern.compile("(?<!\\d)\\d{4,6}(?!\\d)"); //(\d{4,6})
        Matcher matcher = p.matcher(patternContent);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
