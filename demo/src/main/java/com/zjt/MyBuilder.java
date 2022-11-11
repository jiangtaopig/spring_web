package com.zjt;

import org.springframework.stereotype.Component;

@Component
public class MyBuilder extends MyAbstractBuilder{



    @Override
    public String getBusinessType() {
        return "WriteOff";
    }
}
