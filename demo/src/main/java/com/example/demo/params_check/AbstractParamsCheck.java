package com.example.demo.params_check;

import java.io.Serializable;

public abstract class AbstractParamsCheck implements Serializable {

    private static final long serialVersionUID = 4028738859730950110L;

    public abstract String doCheck(String params);
}
