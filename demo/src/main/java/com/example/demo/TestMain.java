package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMain<content> {
    public static void main(String[] args) throws ParseException {

        System.out.println("sss = " + CustomerSourceEnum.AUTO_ISSUE.getMessage()+", code = " + CustomerSourceEnum.AUTO_ISSUE.getCode());

        System.out.println("---" + PushType.MSG_STORE_REMIND.getValue()+", code = " + PushType.MSG_STORE_REMIND.name());

        if ("MSG_STORE_REMIND".equals(PushType.MSG_STORE_REMIND.toString())) {
            System.out.println("------- eqaul--------");
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

    @Test
    public void testJsonMap() {

        BigDecimal bigDecimal = BigDecimal.valueOf(22);
        System.out.println(bigDecimal.getClass());

        Map map = new HashMap();
        map.put("basicPrice", bigDecimal);


        String content = "{\n" +
                "    \"top\":{\n" +
                "        \"center\":{\n" +
                "            \"viewAttr\":{\n" +
                "                \"title\":\"别瞎改二一\"\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"extraMap\":{\n" +
                "\n" +
                "    },\n" +
                "    \"bottom\":[\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"firstButtonType\":\"primary\",\n" +
                "                \"firstButtonTitle\":\"提交订单\",\n" +
                "                \"type\":\"button\"\n" +
                "            },\n" +
                "            \"acti ons\":[\n" +
                "                {\n" +
                "                    \"next-step\":{\n" +
                "                        \"next-step\":{\n" +
                "                            \"type\":\"link\",\n" +
                "                            \"url\":\"writeOffScanCommonTemplate\"\n" +
                "                        },\n" +
                "                        \"type\":\"submit\",\n" +
                "                        \"url\":\"app/pushRepair/submitProductOrder\"\n" +
                "                    },\n" +
                "                    \"type\":\"submit\",\n" +
                "                    \"url\":\"app/pushRepair/submitProductOrder\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"content\":[\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"textList\":[\n" +
                "                    \"产品信息\"\n" +
                "                ],\n" +
                "                \"titleColor\":\"0xff060606\",\n" +
                "                \"titleFontWeight\":\"bold\",\n" +
                "                \"type\":\"label\"\n" +
                "            },\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"textList\":[\n" +
                "                    \"产品名称\",\n" +
                "                    \"别瞎改二一\"\n" +
                "                ],\n" +
                "                \"titleColor\":\"0xff666768\",\n" +
                "                \"type\":\"label\",\n" +
                "                \"bottomBorderVisible\":\"false\"\n" +
                "            },\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"textList\":[\n" +
                "                    \"合同价格\",\n" +
                "                    \"￥#basicPrice#\"\n" +
                "                ],\n" +
                "                \"titleColor\":\"0xff666768\",\n" +
                "                \"type\":\"label\",\n" +
                "                \"bottomBorderVisible\":\"false\"\n" +
                "            },\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"textList\":[\n" +
                "                    \"服务日期\",\n" +
                "                    \"#startDate# 至 #endDate#\"\n" +
                "                ],\n" +
                "                \"titleColor\":\"0xff666768\",\n" +
                "                \"type\":\"label\",\n" +
                "                \"bottomBorderVisible\":\"true\"\n" +
                "            },\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"textList\":[\n" +
                "                    \"车辆信息\"\n" +
                "                ],\n" +
                "                \"titleColor\":\"0xff060606\",\n" +
                "                \"titleFontWeight\":\"bold\",\n" +
                "                \"type\":\"label\"\n" +
                "            },\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"isRequired\":\"Y\",\n" +
                "                \"keyboardType\":\"vin\",\n" +
                "                \"type\":\"text_field\",\n" +
                "                \"title\":\"车架号/VIN码\"\n" +
                "            },\n" +
                "            \"params\":[\n" +
                "                {\n" +
                "\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"#vin#\",\n" +
                "                    \"key\":\"vehicle.vin\"\n" +
                "                }\n" +

                "                \"type\":\"time_picker\",\n" +
                "                \"title\":\"交强险起期\"\n" +
                "            },\n" +
                "            \"params\":[\n" +
                "                {\n" +
                "\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"#ctpStartDate#\",\n" +
                "                    \"key\":\"autoInsurance.ctpStartDate\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"textList\":[\n" +
                "                    \"客户信息\"\n" +
                "                ],\n" +
                "                \"titleColor\":\"0xff060606\",\n" +
                "                \"titleFontWeight\":\"bold\",\n" +
                "                \"type\":\"label\"\n" +
                "            },\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"isRequired\":\"Y\",\n" +
                "                \"type\":\"text_field\",\n" +
                "                \"title\":\"客户姓名\"\n" +
                "            },\n" +
                "            \"params\":[\n" +
                "                {\n" +
                "\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"#insuredName#\",\n" +
                "                    \"key\":\"insured.insuredName\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"isRequired\":\"Y\",\n" +
                "                \"type\":\"text_field\",\n" +
                "                \"keyboardType\":\"phone\",\n" +
                "                \"title\":\"手机号\"\n" +
                "            },\n" +
                "            \"params\":[\n" +
                "                {\n" +
                "\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"#mobileNo#\",\n" +
                "                    \"key\":\"insured.mobileNo\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"isRequired\":\"Y\",\n" +
                "                \"options\":[\n" +
                "                    {\n" +
                "                        \"code\":\"ID_CARD\",\n" +
                "                        \"title\":\"身份证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"code\":\"PASSPORT\",\n" +
                "                        \"title\":\"护照\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"code\":\"MILITARY\",\n" +
                "                        \"title\":\"军官证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"code\":\"ORG_CODE\",\n" +
                "                        \"title\":\"组织机构代码\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"code\":\"BUSINESS_LICENSE\",\n" +
                "                        \"title\":\"营业执照\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"code\":\"UNIFORM_SOCIAL_CREDIT\",\n" +
                "                        \"title\":\"统一社会信用代码\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"code\":\"HK_MAC_PASS\",\n" +
                "                        \"title\":\"港澳居民来往内地通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"code\":\"MTP\",\n" +
                "                        \"title\":\"台湾居民来往大陆通行证\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"type\":\"title_content_item\",\n" +
                "                \"title\":\"证件类型\",\n" +
                "                \"hintText\":\"请选择证件类型\",\n" +
                "                \"content\":\"#usage#\"\n" +
                "            },\n" +
                "            \"params\":[\n" +
                "                {\n" +
                "\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"#certificateType#\",\n" +
                "                    \"key\":\"insured.certificateType\"\n" +
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
                "                \"isRequired\":\"Y\",\n" +
                "                \"type\":\"text_field\",\n" +
                "                \"keyboardType\":\"id_card\",\n" +
                "                \"title\":\"证件号码\"\n" +
                "            },\n" +
                "            \"params\":[\n" +
                "                {\n" +
                "\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"#certificateNo#\",\n" +
                "                    \"key\":\"insured.certificateNo\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"type\":\"text_field\",\n" +
                "                \"keyboardType\":\"char\",\n" +
                "                \"title\":\"电子邮箱\"\n" +
                "            },\n" +
                "            \"params\":[\n" +
                "                {\n" +
                "\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\":\"#email#\",\n" +
                "                    \"key\":\"insured.email\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"actions\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"viewAttr\":{\n" +
                "                \"type\":\"divider\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        String pattern = "#.*?#";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);

        while (m.find()) {
            int startIndex = m.start();
            int endIndex = m.end();
            String originKeyword = m.group();
            String replaceKeyword = originKeyword.replace("#", "");
            Object replacement = map.get(replaceKeyword);

            // 分情况处理替换内容
            String replacementStr;
            if (replacement instanceof String) {
                // 非空字符串，需要去掉前后""，否则替换后的内容会变成""XXX""
                replacementStr = (String) replacement;
//                if (!replacementStr.equals("")) {
//                    int a = replacementStr.length();
//                    replacementStr = replacementStr.substring(1, replacementStr.length()-1);
//                }

            } else {
                // Map 和 List直接替换，需要删除原来的""
                replacementStr = JSON.toJSONString(replacement);
                originKeyword = '"' + originKeyword + '"';
            }

            if (replaceKeyword.equals("basicPrice")) {
                // 作为整体替换
                content = content.replace(originKeyword, replacementStr);
                System.out.println("content = " + content);
            } else {
                content = content.replace(originKeyword, replacementStr);
            }
        }



        String data = "{\n" +
                "\t\"extraMap\": {\n" +
                "\t\t\"dealerCode\": \"#dealerCode#\",\n" +
                "\t\t\"orderNo\": \"#orderNo#\"\n" +
                "\t},\n" +
                "\t\"top\": \"hhhh\"\n" +
                "}";

        JSONObject jsonObject = JSON.parseObject(data);

        if (jsonObject.containsKey("extraMap")) {
            Map<String, Object> map2 = (Map<String, Object>) jsonObject.get("extraMap");
            if (map2 != null) {
                System.out.println("size == " + map2.size());

                System.out.println("map = " + map2);
            }
        }






    }





    @Test
    public void testListContain() {
        List<String> s1 = Stream.of("a", "d", "c", "b", "e").collect(Collectors.toList());
        List<String> s2 = new ArrayList<>();//Stream.of("e", "c", "a").collect(Collectors.toList());

        boolean flag = s1.containsAll(s2);
        System.out.println("flag >>> " + flag);
    }



}
