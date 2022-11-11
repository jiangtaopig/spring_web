package com.example.demo;

import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestJson {


    @Test
    public void testJson() {
        String content = "{\n" +
                "    \"extraMap\":{\n" +
                "    },\n" +
                "    \"top\":{\n" +
                "        \"center\":{\n" +
                "            \"viewAttr\":{\n" +
                "                \"title\":\"维修补助核销\"\n" +
                "            },\n" +
                "            \"params\":null,\n" +
                "            \"actions\":null\n" +
                "        }\n" +
                "    },\n" +
                "    \"bottom\":[\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"backgroundColor\":\"0xFF3A8AED\",\n" +
                "                \"left\":\"0\",\n" +
                "                \"type\":\"submit\",\n" +
                "                \"title\":\"核销申请\"\n" +
                "            },\n" +
                "            \"params\":null,\n" +
                "            \"actions\":[\n" +
                "                {\n" +
                "                    \"type\":\"submit\",\n" +
                "                    \"url\":\"app/writeoff/queryCouponUrlByCondition\",\n" +
                "                    \"next-step\":{\n" +
                "                        \"type\":\"gotoWriteOffRequest\"\n" +
                "                    }\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"content\":[\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"type\":\"divider\"\n" +
                "            },\n" +
                "            \"params\":null,\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"type\":\"text_field\",\n" +
                "                \"title\":\"车架号\",\n" +
                "                \"isRequired\":\"Y\"\n" +
                "            },\n" +
                "            \"params\":[\n" +
                "                {\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"#vin#\",\n" +
                "                    \"key\":\"vin\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"options\":\"#couponTypeOptions#\",\n" +
                "                \"type\":\"title_content_item\",\n" +
                "                \"content\":\"请选择卡券类型\",\n" +
                "                \"title\":\"卡券类型\",\n" +
                "                \"isRequired\":\"Y\"\n" +
                "            },\n" +
                "            \"params\":[\n" +
                "                {\n" +
                "\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"#couponCode#\",\n" +
                "                    \"key\":\"couponCode\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"actions\":[\n" +
                "                {\n" +
                "                    \"titleName\":\"title\",\n" +
                "                    \"type\":\"showPicker\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"options\":\"#dealerOptions#\",\n" +
                "                \"type\":\"title_content_item\",\n" +
                "                \"content\":\"请选择经销商\",\n" +
                "                \"title\":\"经销商\",\n" +
                "                \"isRequired\":\"Y\"\n" +
                "            },\n" +
                "            \"params\":[\n" +
                "                {\n" +
                "\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"#dealerCode#\",\n" +
                "                    \"key\":\"dealerCode\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"actions\":[\n" +
                "                {\n" +
                "                    \"titleName\":\"title\",\n" +
                "                    \"type\":\"showPicker\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        String pattern = "#.*?#";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);

        while (m.find()) {
            String originKeyword = m.group();
            String replaceKeyword = originKeyword.replace("#", "");

            if ("couponTypeOptions".equals(replaceKeyword)) {
                Object replacement = getCouponTypeOptions();
                if (!(replacement instanceof String)) {

                    String replaceStr = JSON.toJSONString(replacement);
                    // 需要删除原来的 "#vouchersName#" 中的 ""
                    originKeyword = '"' + originKeyword + '"';
                    content = content.replace(originKeyword, replaceStr);
                    int a = 1;
                    System.out.println("content = " + content);
                }
            }
        }
    }

    private @NotNull Object getCouponTypeOptions() {
        List couponTypeOptions = new ArrayList();

        Map couponType1 = new HashMap();
        couponType1.put("code", "SGMFW002");
        couponType1.put("name", "维修补助小额");
        couponTypeOptions.add(couponType1);

        Map couponType2 = new HashMap();
        couponType2.put("code", "SGMFW003");
        couponType2.put("name", "维修补助大额");
        couponTypeOptions.add(couponType2);

        Map couponType3 = new HashMap();
        couponType3.put("code", "SGMFW2021002");
        couponType3.put("name", "好易修服务券");
        couponTypeOptions.add(couponType3);

        Map couponType4 = new HashMap();
        couponType4.put("code", "SGMFW028");
        couponType4.put("name", "极易修服务券");
        couponTypeOptions.add(couponType4);

        Map couponType5 = new HashMap();
        couponType5.put("code", "SGMFW2021008");
        couponType5.put("name", "通易修服务券");
        couponTypeOptions.add(couponType5);

        Map couponType6 = new HashMap();
        couponType6.put("code", "SGMFW2021003");
        couponType6.put("name", "初级服务小额券");
        couponTypeOptions.add(couponType6);

        Map couponType7 = new HashMap();
        couponType7.put("code", "SGMFW2021004");
        couponType7.put("name", "初级服务大额券");
        couponTypeOptions.add(couponType7);

        Map couponType8 = new HashMap();
        couponType8.put("code", "SGMFW2021005");
        couponType8.put("name", "高级服务券");
        couponTypeOptions.add(couponType8);

        return couponTypeOptions;
    }
}
