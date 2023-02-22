package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class TestJsonReplace {


    private String getJsonStr() {
        String s = "{\n" +
                "\t\"extraMap\": {\n" +
                "\t\t\"dealerCode\": \"#dealerCode#\",\n" +
                "\t\t\"applicationNo\": \"#applicationNo#\",\n" +
                "\t\t\"verificationCode\": \"#verificationCode#\"\n" +
                "\t},\n" +
                "\n" +
                "\t\"bottom\": [{\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"firstButtonType\": \"primary\",\n" +
                "\t\t\t\"firstButtonTitle\": \"核销确认\",\n" +
                "\t\t\t\"type\": \"button\"\n" +
                "\t\t},\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"type\": \"burialPoint\",\n" +
                "\t\t\t\"pointType\": \"EventPoint\",\n" +
                "\t\t\t\"pointName\": \"activityCouponWiffout\",\n" +
                "\t\t\t\"pointParams\": {\n" +
                "\t\t\t\t\"dealer_code\": \"#dealerCode#\",\n" +
                "\t\t\t\t\"dealer_name\": \"#dealerName#\",\n" +
                "\t\t\t\t\"wiffout_channel\": \"dim_app\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"next-step\": {\n" +
                "\t\t\t\t\"type\": \"submit\",\n" +
                "\t\t\t\t\"url\": \"app/writeoff/verificationConfirm\",\n" +
                "\t\t\t\t\"next-step\": {\n" +
                "\t\t\t\t\t\"type\": \"goToHomePage\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}]\n" +
                "\t}]\n" +
                "}";
        return s;
    }

    @Test
    public void testJsonReplace() {
        JSONObject templateRow = JSONObject.parseObject(getJsonStr());
        if (templateRow.containsKey("bottom")) {
            System.out.println("111");
            JSONArray jsonArray = templateRow.getJSONArray("bottom");
            String ss = " [{\n" +
                    "\t\t\"viewAttr\": {\n" +
                    "\t\t\t\"firstButtonType\": \"primary\",\n" +
                    "\t\t\t\"firstButtonTitle\": \"提交申请\",\n" +
                    "\t\t\t\"type\": \"button\"\n" +
                    "\t\t},\n" +
                    "\t\t\"actions\": [{\n" +
                    "\t\t\t\"type\": \"burialPoint\",\n" +
                    "\t\t\t\"pointType\": \"EventPoint\",\n" +
                    "\t\t\t\"pointName\": \"activityCouponWiffout\",\n" +
                    "\t\t\t\"pointParams\": {\n" +
                    "\t\t\t\t\"dealer_code\": \"#dealerCode#\",\n" +
                    "\t\t\t\t\"dealer_name\": \"#dealerName#\",\n" +
                    "\t\t\t\t\"wiffout_channel\": \"dim_app\"\n" +
                    "\t\t\t},\n" +
                    "\t\t\t\"next-step\": {\n" +
                    "\t\t\t\t\"type\": \"submit\",\n" +
                    "\t\t\t\t\"url\": \"app/writeoff/applySubmit\",\n" +
                    "\t\t\t\t\"next-step\": {\n" +
                    "\t\t\t\t\t\"type\": \"goToHomePage\"\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t}\n" +
                    "\t\t}]\n" +
                    "\t}]";
            templateRow.put("bottom", JSONArray.parseArray(ss));

            System.out.println("ss == > "+ JSONObject.toJSONString(templateRow));
        }
    }

}
