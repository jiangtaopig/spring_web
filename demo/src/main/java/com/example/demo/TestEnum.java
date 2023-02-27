package com.example.demo;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestEnum {
    public static void main(String[] args) {
        String name = DepartmentEnum.getDepartmentName("SERVICE_DEPT");
        System.out.println("name = " + name);

        String a = "34\r\n\t123\t\r";
        String b = a.trim();
        System.out.println("a "+a+"___");
        System.out.println("b = " +b+"xxx");

        AA aa = new AA();
        List<String> strings = Stream.of("122", "233").collect(Collectors.toList());
        aa.setTemplateNoList(strings);

        String json = JSONObject.toJSONString(aa);

        System.out.println("json >>> : "+ json);

    }

    static class AA {
        private List<String> templateNoList;

        public List<String> getTemplateNoList() {
            return templateNoList;
        }

        public void setTemplateNoList(List<String> templateNoList) {
            this.templateNoList = templateNoList;
        }
    }
}
