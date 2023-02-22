package com.example.demo;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DepartmentEnum {
    MARKET_DEPT("MARKET_DEPT", "市场部"),

    FINANCE_DEPT("FINANCE_DEPT", "财务部"),

    SERVICE_DEPT("SERVICE_DEPT", "客服部"),

    USED_CAR_DEPT("USED_CAR_DEPT", "二手车部"),

    OTHER("OTHER", "其他");

    /**
     * 枚举码
     */
    private String code;
    /**
     * 枚举描述
     */
    private String desc;

    DepartmentEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDepartmentName(String departmentType) {
        if (StringUtils.isBlank(departmentType)) {
            return null;
        } else {
            List<DepartmentEnum> departmentTypeList = (List) Arrays.stream(values()).filter((filterType) -> {
                return filterType.name().equals(departmentType);
            }).collect(Collectors.toList());

            return departmentTypeList.get(0).getDesc();
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
