package com.example.demo.params_check;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParamsTest {

    private final static Map<String, AbstractParamsCheck> PARAMS_CHECK_MAP = new HashMap<>();

    static {
        PARAMS_CHECK_MAP.put("checkMobileValue", new MobileParamsCheck());
    }


    @Test
    public void checkParams() {
        String arguments = "{\n" +
                "\t\"validatorMap\": {\n" +
                "\t\t\"licenseNo\": \"required\",\n" +
                "\t\t\"claimNo\": \"required\",\n" +
                "\t\t\"claimantName\": \"required\",\n" +
                "\t\t\"claimantMobile\": \"required|checkMobileValue\"\n" +
                "\t},\n" +
                "\t\"licenseNo\": \"冀D0\",\n" +
                "\t\"claimNo\": \"\",\n" +
                "\t\"claimantName\": \"裕民\",\n" +
                "\t\"claimantMobile\": \"137222255\"\n" +
                "}";

        Map map = JSONObject.parseObject(arguments);
        Map<String, String> validatorMap = (Map) map.get("validatorMap");

        System.out.println("----------- checkParams ------- size = " + map.size()
                + " , validatorMap size = " + validatorMap.size());


        Set<Map.Entry<String, String>> set = validatorMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            String key = entry.getKey();
            String value = entry.getValue();

            System.out.println("key = " + key + " , value = " + value);

            String[] values = value.split("\\|");

            List<String> list = Stream.of(values).collect(Collectors.toList());
            System.out.println("list = " + list);

            list.forEach(e -> {
                if ("required".equals(e)) {
                    if (StringUtils.isBlank((CharSequence) map.get(key))) {
                        System.out.println("参数必传 " + key);
                    }
                } else if ("checkMobileValue".equals(e)) {
                    String result = PARAMS_CHECK_MAP.get("checkMobileValue").doCheck((String) map.get(key));
                    if (StringUtils.isNotBlank(result)) {
                        System.out.println(" result = " + result);
                    }
                }
            });
        }
    }


    @Test
    public void imageAiCheck() {
        String needCheckImageType = "VIN,LOSS";

        ImageMO imageMO1 = new ImageMO();
        imageMO1.imageName = "车架号";
        imageMO1.imageType = "VIN";
        imageMO1.url = "xxx1";
        imageMO1.aiCheck = "Y";

        ImageMO imageMO2 = new ImageMO();
        imageMO2.imageName = "其他";
        imageMO2.imageType = "OTHER";
        imageMO2.url = "xxx2";
        imageMO2.aiCheck = "N";

        ImageMO imageMO3 = new ImageMO();
        imageMO3.imageName = "车损照";
        imageMO3.imageType = "LOSS";
        imageMO3.url = "xxx3";
        imageMO3.aiCheck = "N";

        List<ImageMO> imageMOList = Arrays.asList(imageMO1, imageMO2, imageMO3);

        String[] imageTypeList = needCheckImageType.split(",");

        boolean flag = aiCheck(imageTypeList, imageMOList);
       if (flag) {
           System.out.println("必检图片通过！！！");
       } else {
           System.out.println("必检图片失败！！！");
       }
    }

    private boolean aiCheck(String[] imageTypeList, List<ImageMO> imageMOList) {
        for (String imageType : imageTypeList) {
            for (ImageMO imageMO : imageMOList) {
                System.out.println("imageType  >>> " + imageType + " ,  imageMO = " + imageMO);
                if (imageMO.imageType.equals(imageType)) {
                    if ("N".equals(imageMO.aiCheck)) {
                        System.out.println("失败的信息：imageType  >>> " + imageType + " ,  imageMO = " + imageMO);
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Data
    static class ImageMO {
        private String imageType;
        private String url;
        private String imageName;
        private String aiCheck;
    }
}
