package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.Test;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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

    @Test
    public void testMain() {

        List<CodeEntry> codeEntryList = JSONArray.parseArray(StrConstant.jsonArrayStr, CodeEntry.class);
        System.out.println("size = " + codeEntryList.size());

        List<CodeEntry> codeEntryList1 = codeEntryList.stream().filter(codeEntry -> {
            return codeEntry.property1.equals("SGM");
        }).collect(Collectors.toList());

        System.out.println("codeEntryList1 = " + codeEntryList1);

        Optional<CodeEntry> codeEntryOptional = codeEntryList.stream().filter(codeEntry -> {
            return codeEntry.name.contains("免费基础保养");
        }).findFirst();

        if (codeEntryOptional.isPresent()) {
            CodeEntry codeEntry = codeEntryOptional.get();
            System.out.println(codeEntry);
        } else {
            System.out.println("------ 未找到 -----------");
        }

        String mm = "a8fdc205a9f19cc1c7507a60c4f01b13d11d7fd0";
        System.out.println("mm length = " + mm.length());

    }


    @Test
    public void testMain2() {

        String aa = "com.insaic.base.exception.BusinessException: 报表暂最多支持导出0万条数据！";

        aa = aa.substring(aa.indexOf(":")+1);

        System.out.println("======aa ======" + aa);

        String jsonArrayStr = "[{\n" + " \"entryId\": 509832,\n" + " \"category\": \"ECS_SETTLE_APPROVAL_VOUCHERS_CATEGORY\",\n" + " \"code\": \"DEDUCTIBLE_RETURN\",\n" + " \"name\": \"返厂无忧\",\n" + " \"updateTime\": 1616483772000,\n" + " \"sort\": 1\n" + "}, {\n" + " \"entryId\": 509835,\n" + " \"category\": \"ECS_SETTLE_APPROVAL_VOUCHERS_CATEGORY\",\n" + " \"code\": \"OTHERS\",\n" + " \"name\": \"其他\",\n" + " \"updateTime\": 1616483772000,\n" + " \"sort\": 4\n" + "}, {\n" + " \"entryId\": 509833,\n" + " \"category\": \"ECS_SETTLE_APPROVAL_VOUCHERS_CATEGORY\",\n" + " \"code\": \"SGM\",\n" + " \"name\": \"SGM产品\",\n" + " \"updateTime\": 1616483772000,\n" + " \"sort\": 2\n" + "}, {\n" + " \"entryId\": 509834,\n" + " \"category\": \"ECS_SETTLE_APPROVAL_VOUCHERS_CATEGORY\",\n" + " \"code\": \"TYRE\",\n" + " \"name\": \"轮胎\",\n" + " \"updateTime\": 1616483772000,\n" + " \"sort\": 3\n" + "}]";

        List<CodeEntry> codeEntryList = JSONArray.parseArray(jsonArrayStr, CodeEntry.class);
        System.out.println("size = " + codeEntryList.size());
//        List<CodeEntry> codeEntryList2 = codeEntryList.stream()
//                .sorted(Comparator.comparing(CodeEntry::getSort)).
//                collect(Collectors.toList());

        Collections.sort(codeEntryList, new Comparator<CodeEntry>() {
            @Override
            public int compare(CodeEntry o1, CodeEntry o2) {
                return o1.getSort() - o2.getSort();
            }
        });

        System.out.println("codeEntryList2 = " + codeEntryList);


        List<CodeEntry> codeEntryList1 = JSONArray.parseArray(StrConstant.jsonArrayStr, CodeEntry.class);

        List<List<CodeEntry>> list = new ArrayList<>(codeEntryList.size());

        List<CouponMo> couponList = new ArrayList<>();

        codeEntryList.stream().forEach(codeEntry -> {
            CouponMo couponMo = new CouponMo();
            couponMo.code = codeEntry.code;
            couponMo.name = codeEntry.name;

           List<CouponMo> couponMos = codeEntryList1.stream()
                    .filter(codeEntry1 -> {
                        return codeEntry1.property1.equals(codeEntry.code);
                    }).map(codeEntry1 -> {
                        CouponMo couponMo1 = new CouponMo();
                        couponMo1.code = codeEntry1.code;
                        couponMo1.name = codeEntry1.name;
                        return couponMo1;
                    }).collect(Collectors.toList());
            couponMo.setChildren(couponMos);
            couponList.add(couponMo);
        });

        String couponJson = JSONObject.toJSONString(couponList);

        System.out.println("couponJson =   " + couponJson);



        codeEntryList.stream().forEach(codeEntry -> {
            List<CodeEntry> tmp = codeEntryList1.stream()
                    .filter(codeEntry1 -> {
                        return codeEntry1.property1.equals(codeEntry.code);
                    })
                    .sorted(Comparator.comparing(CodeEntry::getSort))
                    .collect(Collectors.toList());
            codeEntry.setCodeEntryList(tmp);
            list.add(tmp);
        });

        System.out.println("list = " + list);
    }

    @Data
    private static class CouponMo implements Serializable {

        private static final long serialVersionUID = 7755548638802627705L;
        private String code; // 卡券code
        private String name; // 卡券名称
        private List<CouponMo> children;
    }
}
