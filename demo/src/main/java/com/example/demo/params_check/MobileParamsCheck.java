package com.example.demo.params_check;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public class MobileParamsCheck extends AbstractParamsCheck implements Serializable {

    private static final long serialVersionUID = -3801542443305474436L;

    @Override
    public String doCheck(String params) {
        if (StringUtils.isEmpty(params))
            return "手机号码为空！";
        if (params.length() != 11) {
            return "手机号码不是11位！";
        }
        return "";
    }
}
