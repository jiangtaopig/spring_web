package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.Test;

import java.io.Serializable;
import java.util.*;

public class TestJson1 {

    private String entryStr = "";

    @Data
    private static class CodeEntry implements Serializable {
        private static final long serialVersionUID = -1581028750103263999L;

        private long entryId;
        private String category;
        private String code;
        private String name;
        private String property1;
        private int sort;
        private List<CodeEntry> codeEntryList;
    }

    private String handlerSpecialDefinedCoupon(String jsonContent) {
        String deductCountStr = "{\n" +
                "\t\"params\": [{\n" +
                "\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"value\": \"\",\n" +
                "\t\t\t\"key\": \"couponSelected.#couponNo#.verificationCashAmount\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"viewAttr\": {\n" +
                "\t\t\"hintText\": \"1次为1元，请填写1元的倍数进行核销\",\n" +
                "\t\t\"keyboardType\": \"number\",\n" +
                "\t\t\"type\": \"text_field\",\n" +
                "\t\t\"title\": \"扣减次数\"\n" +
                "\t}\n" +
                "}";

        JSONObject deductCountJson = JSONObject.parseObject(deductCountStr);
        JSONObject templateJson = JSONObject.parseObject(jsonContent);
        if (templateJson != null && templateJson.containsKey("content")) {
            JSONArray jsonArray = templateJson.getJSONArray("content");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject item = (JSONObject) jsonArray.get(i);
                JSONObject attr = item.getJSONObject("viewAttr");
                if ("扣减次数".equals(attr.getString("title"))) {
                    jsonArray.set(i, deductCountJson);
                }
            }
        }
        jsonContent = JSONObject.toJSONString(templateJson);
        return jsonContent;
    }

    @Test
    public void testMain2() {

    }

    @Test
    public void test5() {
        Map<String, Object> map = new HashMap<>();

        map.put("name", "pig");
        map.put("age", 23);

        String s = JSONObject.toJSONString(map);
        System.out.println("s = " + s);

        String ss = "zhujt,aa,bb,";
        String[] sss = ss.split(",");
        Arrays.stream(sss).forEach(ssss -> {
            System.out.println(ssss);
        });
    }

    @Data
    private static class CouponMo implements Serializable {

        private static final long serialVersionUID = 7755548638802627705L;
        private String code; // 卡券code
        private String name; // 卡券名称
        private List<CouponMo> children;
    }

    @Test
    public void test6() {
        String s = "{\n" +
                "\t\"top\": {\n" +
                "\t\t\"center\": {\n" +
                "\t\t\t\"viewAttr\": {\n" +
                "\t\t\t\t\"title\": \"扫码核销\"\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"extraMap\": {\n" +
                "\t\t\"currentStep\": \"STEP1\",\n" +
                "\t\t\"templateNo\": \"SGM_MAINTENANCE_002\",\n" +
                "\t\t\"accountBankCode\": \"1\",\n" +
                "\t\t\"vouchersName\": \"维修补助服务券（激活）\",\n" +
                "\t\t\"dealerCode\": \"BK20211\",\n" +
                "\t\t\"accountName\": \"甄士隐\",\n" +
                "\t\t\"accountNo\": \"62222111111111111\",\n" +
                "\t\t\"writeCode\": \"VC2300000111-1\",\n" +
                "\t\t\"settlementId\": 25027,\n" +
                "\t\t\"businessType\": \"WRITE_OFF\",\n" +
                "\t\t\"subBranch\": \"\",\n" +
                "\t\t\"productCategory\": \"SGM_MAINTENANCE\"\n" +
                "\t},\n" +
                "\t\"bottom\": [{\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"firstButtonType\": \"primary\",\n" +
                "\t\t\t\"firstButtonTitle\": \"下一步\",\n" +
                "\t\t\t\"type\": \"button\"\n" +
                "\t\t},\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"next-step\": {\n" +
                "\t\t\t\t\"type\": \"link\",\n" +
                "\t\t\t\t\"url\": \"writeOffScanCommonTemplate\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"type\": \"submit\",\n" +
                "\t\t\t\"url\": \"app/writeoff/stageInTemplate\"\n" +
                "\t\t}]\n" +
                "\t}],\n" +
                "\t\"content\": [{\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"type\": \"divider\"\n" +
                "\t\t}\n" +
                "\t}, {\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"textList\": [\"维修补助服务券（激活）\"],\n" +
                "\t\t\t\"titleColor\": \"0xff060606\",\n" +
                "\t\t\t\"titleFontWeight\": \"bold\",\n" +
                "\t\t\t\"type\": \"label\"\n" +
                "\t\t},\n" +
                "\t\t\"actions\": [{}]\n" +
                "\t}, {\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"type\": \"divider\"\n" +
                "\t\t}\n" +
                "\t}, {\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"isRequired\": \"Y\",\n" +
                "\t\t\t\"keyboardType\": \"vin\",\n" +
                "\t\t\t\"type\": \"text_field\",\n" +
                "\t\t\t\"title\": \"报案号\"\n" +
                "\t\t},\n" +
                "\t\t\"params\": [{\n" +
                "\t\t\t\"value\": \"required\",\n" +
                "\t\t\t\"key\": \"validatorMap.claimNo\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"value\": \"YEUFYUDJEHYY\",\n" +
                "\t\t\t\"key\": \"claimNo\"\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"isRequired\": \"Y\",\n" +
                "\t\t\t\"keyboardType\": \"province\",\n" +
                "\t\t\t\"type\": \"text_field\",\n" +
                "\t\t\t\"title\": \"车牌号\"\n" +
                "\t\t},\n" +
                "\t\t\"params\": [{\n" +
                "\t\t\t\"value\": \"required\",\n" +
                "\t\t\t\"key\": \"validatorMap.licenseNo\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"value\": \"黑A123PU\",\n" +
                "\t\t\t\"key\": \"licenseNo\"\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"isRequired\": \"Y\",\n" +
                "\t\t\t\"rightUrl\": \"local:images/icon/calendar.png\",\n" +
                "\t\t\t\"format\": \"ymd\",\n" +
                "\t\t\t\"type\": \"time_picker\",\n" +
                "\t\t\t\"title\": \"出险日期\"\n" +
                "\t\t},\n" +
                "\t\t\"params\": [{}, {\n" +
                "\t\t\t\"value\": 1677809356000,\n" +
                "\t\t\t\"key\": \"lossDate\"\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"isRequired\": \"Y\",\n" +
                "\t\t\t\"type\": \"text_field\",\n" +
                "\t\t\t\"title\": \"报案人\"\n" +
                "\t\t},\n" +
                "\t\t\"params\": [{\n" +
                "\t\t\t\"value\": \"required\",\n" +
                "\t\t\t\"key\": \"validatorMap.claimantName\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"value\": \"有个\",\n" +
                "\t\t\t\"key\": \"claimantName\"\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"isRequired\": \"Y\",\n" +
                "\t\t\t\"keyboardType\": \"phone\",\n" +
                "\t\t\t\"type\": \"text_field\",\n" +
                "\t\t\t\"title\": \"报案人联系电话\"\n" +
                "\t\t},\n" +
                "\t\t\"params\": [{\n" +
                "\t\t\t\"value\": \"required|checkMobileValue\",\n" +
                "\t\t\t\"key\": \"validatorMap.claimantMobile\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"value\": \"15633855446\",\n" +
                "\t\t\t\"key\": \"claimantMobile\"\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"isRequired\": \"Y\",\n" +
                "\t\t\t\"options\": [{\n" +
                "\t\t\t\t\"code\": \"ID_CARD\",\n" +
                "\t\t\t\t\"title\": \"身份证\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"code\": \"PASSPORT\",\n" +
                "\t\t\t\t\"title\": \"护照\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"code\": \"MILITARY\",\n" +
                "\t\t\t\t\"title\": \"军官证\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"code\": \"HK_MAC_PASS\",\n" +
                "\t\t\t\t\"title\": \"港澳居民来往内地通行证\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"code\": \"MTP\",\n" +
                "\t\t\t\t\"title\": \"台湾居民来往大陆通行证\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"optionFlag\": \"ID_CARD\",\n" +
                "\t\t\t\"type\": \"title_content_item\",\n" +
                "\t\t\t\"title\": \"证件类型\",\n" +
                "\t\t\t\"content\": \"请选择证件类型\"\n" +
                "\t\t},\n" +
                "\t\t\"params\": [{}, {\n" +
                "\t\t\t\"value\": \"PASSPORT\",\n" +
                "\t\t\t\"key\": \"certType\"\n" +
                "\t\t}],\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"titleName\": \"title\",\n" +
                "\t\t\t\"type\": \"showPicker\"\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"isRequired\": \"Y\",\n" +
                "\t\t\t\"type\": \"text_field\",\n" +
                "\t\t\t\"title\": \"证件号码\"\n" +
                "\t\t},\n" +
                "\t\t\"params\": [{\n" +
                "\t\t\t\"value\": \"required\",\n" +
                "\t\t\t\"key\": \"validatorMap.certNo\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"value\": \"72637272728\",\n" +
                "\t\t\t\"key\": \"certNo\"\n" +
                "\t\t}],\n" +
                "\t\t\"actions\": null\n" +
                "\t}, {\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"startTimePlaceholder\": \"请选择出生日期\",\n" +
                "\t\t\t\"expandStatus\": \"false\",\n" +
                "\t\t\t\"expandFlag\": \"ID_CARD\",\n" +
                "\t\t\t\"rightUrl\": \"local:images/icon/calendar.png\",\n" +
                "\t\t\t\"format\": \"ymd\",\n" +
                "\t\t\t\"type\": \"time_picker\",\n" +
                "\t\t\t\"title\": \"出生日期\"\n" +
                "\t\t},\n" +
                "\t\t\"params\": [{}, {\n" +
                "\t\t\t\"value\": 1677772800000,\n" +
                "\t\t\t\"key\": \"birthDate\"\n" +
                "\t\t}],\n" +
                "\t\t\"actions\": null\n" +
                "\t}, {\n" +
                "\t\t\"viewAttr\": {\n" +
                "\t\t\t\"expandStatus\": \"false\",\n" +
                "\t\t\t\"expandFlag\": \"ID_CARD\",\n" +
                "\t\t\t\"options\": [{\n" +
                "\t\t\t\t\"code\": \"MALE\",\n" +
                "\t\t\t\t\"title\": \"男\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"code\": \"FEMALE\",\n" +
                "\t\t\t\t\"title\": \"女\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"type\": \"title_content_item\",\n" +
                "\t\t\t\"title\": \"性别\",\n" +
                "\t\t\t\"content\": \"请选择性别\"\n" +
                "\t\t},\n" +
                "\t\t\"params\": [{}, {\n" +
                "\t\t\t\"value\": \"MALE\",\n" +
                "\t\t\t\"key\": \"gender\"\n" +
                "\t\t}],\n" +
                "\t\t\"actions\": [{\n" +
                "\t\t\t\"titleName\": \"title\",\n" +
                "\t\t\t\"type\": \"showPicker\"\n" +
                "\t\t}]\n" +
                "\t}]\n" +
                "}";

        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONArray jsonArray = jsonObject.getJSONArray("content");
        System.out.println("size >>>" + jsonArray.size());
        boolean target = false;


        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonItem = jsonArray.getJSONObject(i);
            if (jsonItem.containsKey("viewAttr")) {
                JSONObject viewAttrJson = jsonItem.getJSONObject("viewAttr");

                if (viewAttrJson.containsKey("optionFlag") && "ID_CARD".equals(viewAttrJson.getString("optionFlag"))) {
                    System.out.println("-------------");
                    if (jsonItem.containsKey("params")) {
                        JSONArray params = jsonItem.getJSONArray("params");
                        System.out.println("params size = " + params.size());
                        for (int j = 0; j < params.size(); j++) {
                            JSONObject paramJson = params.getJSONObject(j);
                            if (paramJson.getString("value") != null && !"ID_CARD".equals(paramJson.getString("value"))) {
                                System.out.println("xxxxxxxxxxx");
                                target = true;
                                break;
                            }
                        }
                    }
                }
                System.out.println(target);
                if (target && viewAttrJson.containsKey("expandFlag") && "ID_CARD".equals(viewAttrJson.getString("expandFlag"))) {
                    System.out.println(viewAttrJson.getString("expandStatus"));
                    viewAttrJson.put("expandStatus", "true");
                }
            }
        }
        System.out.println(jsonObject);


        Iterator<Object> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            if (jsonObject1.containsKey("viewAttr")) {
                JSONObject jsonObject2 = jsonObject1.getJSONObject("viewAttr");

                List<String> strings = jsonObject2.getObject("textList", List.class);
                System.out.println("textList >>>" + strings);

                if ("性别".equals(jsonObject2.getString("title"))) {

                    iterator.remove();
                }
            }
        }
        System.out.println(jsonObject);
     }



     @Test
     public void test7() {
        String s = "{\n" +
                "\t\"extraMap\": {},\n" +
                "\t\"content\": [{\n" +
                "\t\t\t\"viewAttr\": {\n" +
                "\t\t\t\t\"expandStatus\": \"true\",\n" +
                "\t\t\t\t\"deleteWidgetKey\": \"ID_CARD\",\n" +
                "\t\t\t\t\"deleteKey\": \"身份证\",\n" +
                "\t\t\t\t\"textList\": [\n" +
                "\t\t\t\t\t\"证件类型\",\n" +
                "\t\t\t\t\t\"#certType#\"\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"expandFlag\": \"display1\",\n" +
                "\t\t\t\t\"titleColor\": \"0xff666768\",\n" +
                "\t\t\t\t\"type\": \"label\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"viewAttr\": {\n" +
                "\t\t\t\t\"expandStatus\": \"true\",\n" +
                "\t\t\t\t\"textList\": [\n" +
                "\t\t\t\t\t\"证件号码\",\n" +
                "\t\t\t\t\t\"#certNo#\"\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"expandFlag\": \"display1\",\n" +
                "\t\t\t\t\"titleColor\": \"0xff666768\",\n" +
                "\t\t\t\t\"type\": \"label\",\n" +
                "\t\t\t\t\"hiddenInfo\": \"idCardNo\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"viewAttr\": {\n" +
                "\t\t\t\t\"expandStatus\": \"true\",\n" +
                "\t\t\t\t\"textList\": [\n" +
                "\t\t\t\t\t\"出生日期\",\n" +
                "\t\t\t\t\t\"#birthDate#\"\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"expandFlag\": \"display1\",\n" +
                "\t\t\t\t\"titleColor\": \"0xff666768\",\n" +
                "\t\t\t\t\"subWidgetKey\": \"ID_CARD\",\n" +
                "\t\t\t\t\"type\": \"label\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"viewAttr\": {\n" +
                "\t\t\t\t\"expandStatus\": \"true\",\n" +
                "\t\t\t\t\"textList\": [\n" +
                "\t\t\t\t\t\"性别\",\n" +
                "\t\t\t\t\t\"#gender#\"\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"expandFlag\": \"display1\",\n" +
                "\t\t\t\t\"titleColor\": \"0xff666768\",\n" +
                "\t\t\t\t\"subWidgetKey\": \"ID_CARD\",\n" +
                "\t\t\t\t\"type\": \"label\"\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

         JSONObject jsonObject = JSONObject.parseObject(s);
         JSONArray jsonArray = jsonObject.getJSONArray("content");
         System.out.println("size >>>" + jsonArray.size());

         Iterator<Object> iterator = jsonArray.iterator();
         while (iterator.hasNext()) {
             JSONObject jsonObject1 = (JSONObject) iterator.next();
             if (jsonObject1.containsKey("viewAttr")) {
                 JSONObject jsonObject2 = jsonObject1.getJSONObject("viewAttr");

                 List<String> strings = jsonObject2.getObject("textList", List.class);
                 System.out.println("textList >>>" + strings);

                 if (strings.contains("性别")) {
                     System.out.println("---------ssssssss");
                 }

                 if ("性别".equals(jsonObject2.getString("title"))) {

                     iterator.remove();
                 }
             }
         }
         System.out.println(jsonObject);
     }


     @Test
     public void test8() {
        String s = "{\"templateNo\":\"NON_AUTO_018\",\"accountBankCode\":\"1\",\"dealerCode\":\"BK20211\",\"accountName\":\"甄士隐\",\"writeCode\":\"VC2300000452-1\",\"settlementId\":\"25290\",\"productCategory\":\"NON_AUTO\",\"currentStep\":\"STEP2\",\"vouchersName\":\"人车无忧MAX1权益服务券\",\"accountNo\":\"62222111111111111\",\"businessType\":\"WRITE_OFF\",\"subBranch\":\"\",\"productNo\":\"SA_2022_015_MAX1\",\"uploadImageInfos\":{\"resources1\":[{\"imageUrl\":\"\",\"imageGroup\":\"DOCUMENT\",\"imageType\":\"SCOOTER_SERVICE_CONFIRM\",\"required\":\"true\"},{\"imageUrl\":\"\",\"imageGroup\":\"DOCUMENT\",\"imageType\":\"LOSS_ORDER\",\"required\":\"true\"},{\"imageUrl\":\"\",\"imageGroup\":\"DOCUMENT\",\"imageType\":\"BIZ_POLICY\",\"required\":\"true\"},{\"imageUrl\":\"\",\"imageGroup\":\"DOCUMENT\",\"imageType\":\"REPAIR_LIST\",\"required\":\"false\"},{\"imageUrl\":\"\",\"imageGroup\":\"DOCUMENT\",\"imageType\":\"MAINTENANCE_INVOICE\",\"required\":\"false\"},{\"imageUrl\":\"\",\"imageGroup\":\"DOCUMENT\",\"imageType\":\"LICENSE\",\"required\":\"false\"},{\"imageUrl\":\"\",\"imageGroup\":\"DOCUMENT\",\"imageType\":\"QUALITY_CERT\",\"required\":\"false\"}],\"resources2\":[{\"imageUrl\":\"\",\"imageGroup\":\"DOCUMENT\",\"imageType\":\"OTHERS\",\"required\":\"false\"}],\"resources3\":[{\"imageUrl\":\"\",\"imageGroup\":\"DAMAGE_LOSS\",\"imageType\":\"DAMAGE_LOSS\",\"required\":\"true\"}],\"resources4\":[{\"imageUrl\":\"\",\"imageGroup\":\"DAMAGE_LOSS\",\"imageType\":\"OTHERS\",\"required\":\"false\"}]}}\n";
        Map<String, Object> map = JSON.parseObject(s, Map.class);
         System.out.println("size = " + map.size());
     }







}
